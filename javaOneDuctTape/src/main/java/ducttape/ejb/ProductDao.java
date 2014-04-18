package ducttape.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ducttape.common.ConstantsEnum;
import ducttape.entities.Product;

@Stateless
public class ProductDao {

	@PersistenceContext(unitName="forge-default")
	EntityManager em;

	public List<Product> listProducts(String filter, ConstantsEnum sortBy) {
//		return em.createQuery("select p from Product p",Product.class).getResultList();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> product = cq.from(Product.class);
		cq.select(product);
		
		if(sortBy != null) {
			switch(sortBy){
				case ASC:
					cq.orderBy(cb.asc(product.get("price")));
					break;
				case DESC:
					cq.orderBy(cb.desc(product.get("price")));
					break;
				default:
					//do nothing
			}
		}
			/*if(asc.equals(arg0)) {
				cq.orderBy(cb.asc(product.get("price")));
			} else {
	//			cq.orderBy(cb.desc(product.get("price")));
			}*/
		return em.createQuery(cq).getResultList();
		
	}
	
	public List<Product> listProducts() {
//		return em.createQuery("select p from Product p",Product.class).getResultList();
		/*CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> product = cq.from(Product.class);
		cq.select(product);
		return em.createQuery(cq).getResultList();*/
		return listProducts(null,null);
		
	}
}
