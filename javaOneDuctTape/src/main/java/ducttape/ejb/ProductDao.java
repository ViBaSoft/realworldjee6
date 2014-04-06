package ducttape.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ducttape.entities.Product;

@Stateless
public class ProductDao {

	@PersistenceContext(unitName="forge-default")
	EntityManager em;

	public List<Product> listProducts() {
		return em.createQuery("select p from Product p",Product.class).getResultList();
	}
}
