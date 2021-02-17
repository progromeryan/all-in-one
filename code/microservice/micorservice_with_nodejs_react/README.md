# What is MicroService?

- One service implements one feature

# Big problem for MicroService

- Data management between services
- Each service has its own database
- Service will never reach into another service database

# Why database-per-service

- we want each service to run independently of other services
- database schema/structure might change unexpectedly
- some services might function more efficiently with different types of DB's (sql vs nosql)

# Communication Strategies between Services

## 1. Sync: Services communicate with each other using direct requests

- easy to understand (good)
- service D does not need a database (good)
- introduce a dependency between services (bad)
- if any inter-service request fails, the overall request fails (bad)
- entire request is only as fast as the slowest request (bad)
- can easily introduce webs of dependencies (bad)

## 2. Async: Services communicate with each other using events

### 两种

1. Event Bus: each service connects to the event bus
2. Crazy: 新的service仅创建自己需要的db, 同时创建Event bus, 如果其他的service更新了他们的db, 并且这些db和新service的db有关, 那么需要进行同步更新

### 优劣

- service d has zero dependencies on other services (good)
- service d will be extremely fast (good)
- data duplication, paying for extra storage (very cheap to save data, not bad)
- harder to understand (bad)

