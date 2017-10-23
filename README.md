
This demo shows how to use Spring Data Hibernate/JPA in a multithreaded environment.

In detail it shows:

- use the CrudRepository with its simple functions (they are transactional)

- @MappedSuperclass shows how to make an abstract base class to avoid redundant entity code (for ids etc)

- @EnableJpaAuditing and @LastModifiedDate show JPA auditing

- @Version annotation will activate record versioning and optimistic locking. Instead of overwriting each other, one thread will produce an ObjectOptimisticLockingFailureException. (!!! do NOT use the org.springframework import, but the javax.persistence import!)

- lastly, the CustomerService shows how to lock single objects to avoid the aforementioned exception (makes sense only in a single server environment, or with sticky session)
 

(uncomment in application.properties to use local postgresql server)

CC0, Uwe Post
