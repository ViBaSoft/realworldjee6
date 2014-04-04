package ducttape.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ducttape.ejb.ProductDao;
import ducttape.entities.Product;

@Named
@RequestScoped
public class ProductsBean {

	private List<Product> products = new ArrayList<Product>();

	@Inject
	ProductDao productDao;
	
	public List<Product> getProducts() {
		if(products==null) {
			/*products = new ArrayList<Product>();
			products.add(new Product("Product One", 10));
			products.add(new Product("Product Two", 20));*/
			products = productDao.listProducts();
		}
		return products;
	}
	
}
