package de.uwepost;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories
public class CustomerService {

	@Autowired
	public CustomerRepository repository;
	
	private Map<Long,ReentrantLock> locks = new HashMap<>();
	
	
	public void addCash(long id, int cash) {
		
		try {
			// get or create lock for this id
			synchronized(locks) {
				if(!locks.containsKey(id))
					locks.put(id, new ReentrantLock());
				locks.get(id).lock();
			}
			Customer c = repository.findOne(id);
			c.setCash(c.getCash()+cash);
			repository.save(c);
		} finally {
			// unlock
			locks.get(id).unlock();
		}
	}

}
