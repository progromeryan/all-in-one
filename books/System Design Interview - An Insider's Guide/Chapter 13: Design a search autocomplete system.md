# Design a search autocomplete system

![img](assets/13-1.png)

# Step 1: Understand the problem and establish design scope

## 1.1 Questions and answers

- Candidate: Is the matching only supported at the beginning of a search query or in the middle as well?
- Interviewer: Only at the beginning of a search query.
- Candidate: How many autocomplete suggestions should the system return?
- Interviewer: 5
- Candidate: How does the system know which 5 suggestions to return?
- Interviewer: This is determined by popularity, decided by the historical query frequency.
- Candidate: Does the system support spell check?
- Interviewer: No, spell check or autocorrect is not supported.
- Candidate: Are search queries in English?
- Interviewer: Yes. If time allows at the end, we can discuss multi-language support.
- Candidate: Do we allow capitalization and special characters?
- Interviewer: No, we assume all search queries have lowercase alphabetic characters.
- Candidate: How many users use the product?
- Interviewer: 10 million DAU.

## 1.2 Requirements

- Fast response time: less than 100 milliseconds
- Relevant
- Sorted by popularity or other ranking models
- Scalable: handle high traffic volume
- Highly available

## 1.3 Back of the envelope estimation

- 10 million daily active users
- One person 10 searches per day
- 20 bytes of data per query string
  - Use ASCII character encoding. 1 char = 1 byte
  - Assume a query contains 4 words, each word contains 5 char
  - 4 \* 5 bytes per query
- Every character entered into the search box, a client sends a request to the backend for autocomplete suggestions
- On average, 20 requests are sent for a search query, like work dinner
  - search?q=d
  - search?q=di
  - search?q=din
  - search?q=dinn
  - search?q=dinne
  - search?q=dinner
- ~ 24000 query per seconds = 10 million users _ 10 queries / day _ 20 characters / 24 hours / 3600 seconds
- Peak QPS: 2 \* QPS ~= 48000
- Assume 20% of daily queries are new
  - 10 million _ 10 queries/day _ 20 byte per query \* 20% = 0.4 GB.
  - We have 0.4 GB new data per day

# Step 2: Propose high-level design and get buy-in

## 2.1 Two components

- Data gathering service
  - Gather user input queries
  - Aggregate queries in real-time
- Query service
  - Given a search query or prefix, return 5 most frequently searched terms

## 2.2 Data gathering service

![img](assets/13-2.png)

> frequency table

## 2.3 Query service

![img](assets/13-3.png)

> frequency table two fields

- query: query string
- frequency: the number of times a query has been searched

![img](assets/13-4.png)

- When "tw" in search box, five keywords should be popped up
- Query like below
- Good for small data set

```sql
SELECT * FROM frequency_table
WHERE query LIKE `prefix%`
ORDER BY frequency DESC
LIMIT 5
```

# Step 3: Design deep dive

## 3.1 Tire data structure

![img](assets/13-5.png)

- Relational DB is not efficient
- Tire (prefix tree, "try", word is from re**trie**val, indicates it is designed for string retrieval operations) data structure is used to overcome the problem
  - Trie is a tree-like data structure
  - Root represents an empty string
  - Each node stores a character
  - Each node has 26 children
  - Each tree node represents a sing word or a prefix string
- To support sorting by frequency, we save frequency info into nodes

![img](assets/13-6.png)

> Terms

- p: length of a prefix
- n: total number of nodes in a trie
- c: number of children of a given node

> Steps to get top k most searched queries

1. Find the prefix, O(p)
2. Traverse the subtree for the prefix node to get all valid children. A child is valid if it can form a valid query string O(c)
3. Sort children and get top k. O(clogc)

> Example
> When search "tr" in search box and get top 2

![img](assets/13-7.png)

1. Find prefix node "tr"
2. Traverse subtree to get all valid children.
   1. [tree, 10]
   2. [true, 35]
   3. [try, 29]
3. Sort children and get top 2
   1. [true, 35]
   2. [try, 29]

> It is a workable solution, but not efficient.
> How to improve?

- Limit the max length of a prefix
  - User rarely types a long search query
  - Reduce p to 50
  - O(0) -> O(50) -> O(1)
- Cache top search queries at each node
  - Store top k most frequent queries for each node
  - Need more space, but fast
  - Top k queries are cached, O(c) -> O(1)

![img](assets/13-8.png)

- Top 5 queries are stored on each node

## 3.2 Data gathering service

> Real-time update trie is not practical for two reasons

- Users enter billion of queries per day. Updating the trie on every query slows down query service
- Top suggestions do not change much once the trie is built, so it is unnecessary
- Twitter needs real-time update, but google search does not

![img](assets/13-9.png)

### 3.2.1 Analytics logs

![img](assets/13-10.png)

- Stores raw data about search quires
- Logs are append-only

### 3.2.2 Aggregators

- Analytics log is large
- Analytics log is not in right format
- Aggregate differently
  - Real-time app, twitter
    - Aggregate data in a shorter time interval
  - Not real-time app
    - Aggregate data less frequently, like once per week

### 3.2.3 Aggregated data

![img](assets/13-11.png)

- Time: start time of a week
- Frequency: sum of the occurrences for the corresponding query in that week

### 3.2.4 Workers

- Performance asynchronous jobs at regular intervals
- Build trie data structure
- Store data to Trie DB

### 3.2.5 Trie cache

- Distributed cache system
- Keep tire in memory
- Take a weekly snapshot of the db

### 3.2.6 Trie DB

- Persistent storage
- Two options
  - Document store - like MongoDB
    - Serialize trie
  - key-value store
    - A trie can be represented in a hash table form
    - Every prefix in the trie is mapped to a value in a hash table
    - Data on each trie node is mapped to a value in a hash table

![img](assets/13-12.png)

> mapping between trie and hash table

## 3.3 Query service

![img](assets/13-13.png)

- Search query is sent to load balancer
- Load balancer routes the request to API servers
- API servers get trie data from Trie cache and build suggestion
- If data is not in cache, fetch from db and add to cache, then build suggestion

> Optimization

- AJAX request: does not refresh the whole web page
- Browser caching
  - Search suggestions do not change much, so can be saved in browser cache, Google uses this method
- Data sampling
  - Do not log every search
  - Only 1 out of every N requests is logged by the system
  - Useful fo large-scale system

![img](assets/13-14.png)

> Google save suggestions into browser cache.

- Private
  - Results are intended for a single user
  - Results are not be cached by a shared cache
- max-age=3600
  - Cache is valid for 3600 seconds, an hour

## 3.4 Trie operations

### 3.4.1 Create

- Workers use aggregated data to update it
- Source data is from analytics Log/DB

### 3.4.2 Update

> Two ways

1. Update tire weekly
   1. Replace old one with new one
2. Update individual trie node directly
   1. Not recommend
   2. Slow
   3. If trie is small, it is ok

![img](assets/13-15.png)

> Update individual node

### 3.4.3 Delete

- Remove hateful, violent... suggestions
- Add filter layer in the front of tire cache

![img](assets/13-16.png)

## 3.5 Scale storage

> When a tire is too large to fit in one server

### 3.5.1 Simple solution

- Two servers
  - server 1 - a - m
  - server 2 - n - z
- Three servers
  - server 1 - a - i
  - server 2 - j - r
  - server 3 - s - z
    ...
- 26 servers ...
  ...
- ...
  - server 1 aa-ag
  - server 2 ah-an
  - ...
- Problem: data imbalance, letter c has much more suggestions than x.
- Solution
  - Analyze historical data distribution pattern and apply smarter sharding logic
  - Create shard map manager
    - Maintain a lookup db
    - Identify where data should be saved
    - If s and u-z have similar number of historical queries, we only create two shards

![img](assets/13-17.png)

# Step 4: Wrap up

- Extend design to support multiple language
  - Store unicode characters in trie nodes
  - **Unicode: an encoding standard covers all the characters for all the writing systems of the world, modern and ancient**
- Top search queries are different from countries
  - Build different tries for different countries
  - Can store tries in CDNs
- Support real-time search queries
  - Reduce working data set by sharding
  - Change ranking model and assign more weight to recent search queries
  - If it is streaming data, we need tools like Apache Hadoop MapReduce...
