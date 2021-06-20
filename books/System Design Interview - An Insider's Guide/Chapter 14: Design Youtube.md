# Design YouTube

![img](assets/14-1.png)

- Total number of monthly active users: 2 billion.
- Number of videos watched per day: 5 billion.
- 73% of US adults use YouTube.
- 50 million creators on YouTube.
- YouTube’s Ad revenue was $15.1 billion for the full year 2019, up 36% from 2018.
- YouTube is responsible for 37% of all mobile internet traffic.
- YouTube is available in 80 different languages.

# Step 1: Understand the problem and establish design scope

## 1.1 Questions and answers

- Candidate: What features are important?
- Interviewer: Ability to upload a video and watch a video.
- Candidate: What features are important?
- Interviewer: Ability to upload a video and watch a video.
- Candidate: What clients do we need to support?
- Interviewer: Mobile apps, web browsers, and smart TV.
- Candidate: How many daily active users do we have?
- Interviewer: 5 million
- Candidate: What is the average daily time spent on the product?
- Interviewer: 30 minutes.
- Candidate: Do we need to support international users?
- Interviewer: Yes, a large percentage of users are international users.
- Candidate: What are the supported video resolutions?
- Interviewer: The system accepts most of the video resolutions and formats.
- Candidate: Is encryption required?
- Interviewer: Yes
- Candidate: Any file size requirement for videos?
- Interviewer: Our platform focuses on small and medium-sized videos. The maximum allowed video size is 1GB.
- Candidate: Can we leverage some of the existing cloud infrastructures provided by Amazon, Google, or Microsoft?
- Interviewer: That is a great question. Building everything from scratch is unrealistic for most companies, it is recommended to leverage some of the existing cloud services.

## 1.2 Requirements

- Upload videos
- Smooth video streaming
- Change video quality
- Low infrastructure cost
- Availability, scalability, reliability
- Mobile apps, web browser, smart TV

## 1.3 Back of the envelope estimation

- 5 million DAU
- Users watch 5 videos per day
- 10% users upload 1 video per day
- Average video size is 300 MB
- Daily storage: 5 million \* 10% \* 200 MB = 150 TB
- CDN cost
  - When CDN serves a video, you are charged by CDN
  - Amazon CDN CloudFront $0.02/GB
  - 5 million \* 5 videos \* 0.3 GM \* 0.02 = $150000 / day

![img](assets/14-2.png)

> on-demand pricing for data transfer to the Internet (per GB)

# Step 2: Propose high-level design and get buy-in

![img](assets/14-3.png)

## 2.1 Client

- Watch YouTube on computer, mobile phone, smartTV

## 2.2 CDN

- **Videos are stored in CDN**
- When play a video, it is **streamed from the CDN**

## 2.3 API servers

- Everything else except video streaming goes through API servers
- Feed recommendation
- Generate video upload URL
- Update metadata db, cache, user sign up...

## 2.4 Video uploading flow

![img](assets/14-4.png)

### 2.4.1 Components

- User: a user watches YouTube
- Load balancer: distributes request to API servers
- API servers
- Metadata DB (video)
  - Video metadata are stored in DB
  - It is sharded and replicated
- Metadata cache
  - Video metadata and user object are cached
- Original storage
  - A blob storage system
  - Store original videos
  - A Binary Large Object (BLOB) is a collection of binary data stored as a single entity in a database management system
- Transcoding servers
  - Video encoding
  - Convert a video format fo other formats (MPEG, HLS...) to provide the best video streams for different devices and bandwidth capabilities
- Transcoded storage
  - Blob storage that stores transcoded video files
- CDN
  - Video are cached in CDN
- Completion queue
  - Message queue
  - Stores information about video transcoding completion events
- Completion handler
  - A list of workers
  - Pull event from completion queue
  - Update metadata cache and db

### 2.4.2 Flow A: upload the actual video

![img](assets/14-5.png)

1. Videos are uploaded to original storage
2. Transcoding servers fetch videos from original storage and start transcoding
3. When transcoding is complete
   1. Transcoded videos are sent to transcoded storage
      1. Transcoded videos are distributed to CDN
   2. Transcoding completion events are queued in the completion queue
      1. Completion handler (workers) pull event data from the queue
      2. Completion handler updates metadata db and cache
4. API servers inform client that video is successfully uploaded and is ready for streaming

### 2.4.3 Flow B: update the metadata

![img](assets/14-6.png)

- While a video is uploaded to original storage, client in parallel sends request to update video metadata
- Request contains video metadata like file name, size, format...
- API servers update the metadata cache and db

## 2.5 Video streaming flow

### 2.5.1 Download and streaming

- Download: whole video is copied to device
- Streaming
  - Device continuously receives video streams from remote source videos
  - Load a little bit of data at a time
  - Watch videos immediately and continuously

### 2.5.2 Streaming protocol

- Standard way to control data transfer for video streaming
- Popular streaming protocols:
  - MPEG-DASH: moving picture experts group - dynamic adaptive streaming over HTTP
  - Apple HLS: HTTP Live Streaming
  - Microsoft smooth streaming
  - Adobe HTTP Dynamic Streaming (HDS)
- Videos are streamed from CDN
- Server closest to you will deliver the video
- Little latency

![IMG](assets/14-7.png)

# Step 3: Design deep dive

## 3.1 Video transcoding

### 3.1.1 Why video transcoding is important

- Raw video consumes large storage
- **Devices and browsers only support certain types of video formats.** Video need to be encoded to different formats for compatibility reasons
- Deliver high resolution video to high network bandwidth users. Deliver low resolution video to low network bandwidth users
- Network conditions can change, like mobile. Switching video quality feature is necessary.

### 3.1.2 Encoding formats contains two parts

- Container
  - A basket contains video file, audio, metadata
  - Can tell the container format by file extension, like .avi, .mov, .mp4
- Codec
  - Compression and decompression algorithms
  - Reduce video size while preserving video quality
  - H.264, VP9, HEVC

## 3.2 Directed acyclic graph model (DAG)

### 3.2.1 Requirements

- Transcoding is time-consuming
- Content creators have different requirements, like watermarks
- Different video processing pipeline
- Maintain high parallelism
- Define tasks to execute

### 3.2.2 DAG (facebook)

![IMG](assets/14-8.png)

- Define tasks in stages so they can be executed sequentially or parallelly
- Video tasks
  - Inspection
    - Videos have good quality
    - Not malformed
  - Video encodings
    - Videos are converted to support different resolutions, codec...
  - Thumbnail
    - Can be uploaded by a user or automatically generated
  - Watermark
    - An image overlay contains identifying info about the video

![img](assets/14-9.png)

## 3.3 Video transcoding architecture

![img](assets/14-10.png)

### 3.3.1 Preprocessor

![img](assets/14-11.png)

- Video splitting
  - Video stream is split into smaller **group of pictures (GOP)** alignment
  - GOP is a chunk of frames arranged in a specific order
  - Each chunk is an independently playable unit
  - Each chunk is a few seconds in length
- Some old devices or browsers do not support video splitting. Preprocessor split video by GOP alignment for them.
- DAG generation
  - Processor generates DAG based on configuration files client programmers write
- Cache data
  - Preprocessor is a cache for segmented videos
  - Preprocessor stores GOPs and metadata in temporary storage
  - If encoding fails, system can use persisted data for retry

![img](assets/14-12.png)
![img](assets/14-13.png)

> Simplified DAG representation, has 2 nodes and 1 edge. The DAG is generated from the two configurations files.

### 3.3.2 DAG scheduler

![img](assets/14-14.png)

![img](assets/14-15.png)

- DAG scheduler splits a DAG graph into stages of tasks and put them in the task queue in the resource manager.
- Split original video into 2 stages
  - Stage 1: video, audio, metadata
  - Stage 2: video -> video encoding, thumbnail. audio -> audio encoding

### 3.3.3 Resource manager

![img](assets/14-16.png)
![img](assets/14-17.png)

#### 3.3.3.1 Functions and Components

- Resource allocation
- Contains 3 queues and a task scheduler
- Task queue
  - Priority queue
  - Contains tasks to be executed
- Worker queue
  - Priority queue
  - Worker utilization info
- Running queue
  - Currently running tasks info
  - Workers info
- Task scheduler
  - Pick optimal task/worker
  - Instruct the chosen task worker to execute the job

#### 3.3.3.2 Workflow

1. Task scheduler gets the highest priority task from the task queue
2. Task scheduler gets the optimal task worker to run the task from the worker queue
3. Task scheduler instructs the chosen task worker to run the task
4. Task scheduler binds the task/worker info and puts it in the running queue
5. Task scheduler removes the job from the running queue once the job is done

### 3.3.4 Task workers

![img](assets/14-18.png)
![img](assets/14-19.png)

- Task workers run the tasks defined in the DAG.
- Different task workers run different tasks

### 3.3.5 Temporary storage

![img](assets/14-20.png)

- Multiple storage systems are used
- Metadata
  - Frequently accessed
  - Small size
  - Cache in memory
- Video/audio
  - Blob storage
- Data is freed up once the processing is complete

### 3.3.6 Encoding video

![img](assets/14-21.png)

- Final output of the encoding pipeline
- Example: funny_720p.mp4

## 3.4 System optimizations

### 3.4.1 Speed optimization: parallelize video uploading

![img](assets/14-22.png)

- Upload a video as a whole unit is not efficient
- Split a video into smaller chunks by GOP alignment
- Fast resumable uploads from previous failure
- Split can be implemented by client to improve upload speed

![img](assets/14-23.png)

### 3.4.2 Speed optimization: place upload center close to users

![img](assets/14-24.png)

- Multiple upload centers
- Use CDN as upload centers

### 3.4.3 Speed optimization: parallelism everywhere

![img](assets/14-25.png)

- Current design
  - Output depends on the input of the previous step
  - Dependency makes parallelism difficult
- Build loosely coupled system
- Enable high parallelism
- **Message queue** is a great tech to decouple
  - Before message queue: encoding module must wait until the download module is done
  - After message queue: encoding module can execute jobs in message queue in parallel, no more waiting

![img](assets/14-26.png)

### 3.4.4 Safety optimization: pre-signed upload URL

![img](assets/14-27.png)

1. Clients request pre-signed URL
2. API servers return pre-signed URL
3. Clients use pre-signed URL uploads video

### 3.4.5 Safety optimization: protect videos

- Digital rights management (DRM) systems
  - Apple FairPlay
  - Google Widevine
  - Microsoft PlayReady
- AES encryption
  - Encrypt a video
  - Configure an authorization policy
  - Only authorized users can watch an encrypted video
- Visual watermarking
  - Image overlay on top of the video

### 3.4.6 Cost-saving optimization

![img](assets/14-28.png)

- CDN is expensive
- Popular videos from CDN
- Less popular videos are from high capacity storage video servers
- Less popular less encoded video versions
- Some videos are only popular in certain regions. no need to distribute them to other regions
- Build own CDN, like Netflix

## 3.5 Error handling

- Recoverable error
  - Example: video segment fails to transcode
  - Solution: retry; return error code
- Non recoverable error
  - Example: malformed video format
  - Solution: stop running and return error code

# Step 4: Wrap up

- Scale the API tier
- Scale the db
- Live streaming: record and broadcasted in real time
- video takedowns: remove illegal videos
