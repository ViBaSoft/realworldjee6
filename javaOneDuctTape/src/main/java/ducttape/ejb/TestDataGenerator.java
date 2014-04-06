/**
 * TestDataGenerator.java
 * @author FirstName LastName
 * @date Apr 6, 2014
 */
package ducttape.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ducttape.entities.Product;

/**
 * 
 */

@Startup
@Singleton
public class TestDataGenerator {
	
	@PersistenceContext
	EntityManager em;
	
	@PostConstruct
	public void initializeData() {
		em.persist(new Product("Pant", 50));
		em.persist(new Product("Shirt", 40));
		em.persist(new Product("Vest", 20));
		em.persist(new Product("Brief", 10));
	}

}
