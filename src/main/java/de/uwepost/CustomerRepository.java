package de.uwepost;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    
    List<Customer> findTop3ByLastNameOrderByCash(String lastName);
    
    
    @Async
	Future<Customer> findByFirstName(String firstName); 
}
