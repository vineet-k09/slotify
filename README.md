# Boot Appplication
## Run the application
```bash
gradlew bootRun
```

## After adding dependencies 
```bash
./gradlew build --refresh-dependencies
```

## Useful variations
Run on a different port
```bash
java -jar build/libs/slotify-0.0.1-SNAPSHOT.jar --server.port=9090
```

Specify a profile
```bash
java -jar build/libs/slotify-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

Override DB config (no file edits)
```bash
java -jar build/libs/slotify-0.0.1-SNAPSHOT.jar \
  --spring.datasource.username=slotify \
  --spring.datasource.password=slotify
```
(Spring Boot treats these like env vars.)


# Migrations
## What adding Flyway migrations gives you
- Versioned SQL
- Predictable schema
- Clean startup failures
- Safe refactors later

## How it works.
1. App starts
2. Flyway checks table: flyway_schema_history
3. Looks at migration files:
```bash
V1__init.sql
V2__add_slots.sql
V3__add_booking_index.sql
```
4. Runs missing ones in order
5. If anything mismatches → app refuses to start

## Postgres: Isolation level: READ_COMMITTED [default READ_COMMITTED]
This is PostgreSQL’s default isolation level.
- Prevents dirty reads
- Allows concurrent reads
- Good baseline for booking systems

## Connection Pool (HikariCP)
### A database connection is 
- TCP connection
- authenticated
- stateful
- expensive during creation
**Can't really create a new one for each request**

### Connection Pool
- Open N connections ~ reuse them 

### HikariCP = Fastest, simplest JDBC connection pool
**Default for SpringBoot**
```bash
Pool: DataSourceConnectionProvider
```
Means: Default pool, no tuning yet.

## Models
Anything under ```src/main/resources``` is put on the classpath
Flyway has a default location:
```bash
classpath:db/migration
```
### Resource Model
### Slot Model 
Slot generation 
- Compute time windows
- Insert slots
- Skip duplicates safely
- Never crash re-runs 

# Execution Flow
```bash
HTTP request
   ↓
Controller method
   ↓
Service method
   ↓
Repository (proxy)
   ↓
Hibernate
   ↓
PostgreSQL
   ↓
back up the stack
```