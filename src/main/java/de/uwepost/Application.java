package de.uwepost;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer1 = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer1.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
			
			Customer customer2 = repository.findByLastName("Bauer").get(0);
			log.info("Bauer and Bauer are " + (customer1.equals(customer2)?"equal":"not equal"));
			
			customer1.setCash(100);
			repository.save(customer1);
			
			Customer customer3 = repository.findOne(1L);
			
			Integer cash = customer3.getCash();
			log.info("Jack has " + cash);
			
			ExecutorService pool = Executors.newFixedThreadPool(10);
			
			
			
			for(int i=0; i<100; i++) {
				pool.submit(() -> {
						try {
						customerService.addCash(1, 100);
						} catch(Exception e) {
							log.error("exception",e);
						}
					}
				);
				cash+=100;
			}
			Thread.sleep(5000);
			
			Customer customer4 = repository.findOne(1L);
			log.info("Jack has " + customer4.getCash() + " and should have " + cash);
			
		};
	}

}
