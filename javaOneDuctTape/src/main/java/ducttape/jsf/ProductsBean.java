package ducttape.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ducttape.common.ConstantsEnum;
import ducttape.ejb.ProductDao;
import ducttape.entities.Product;


//FYI. Below class is an CDI bean
@Named
@RequestScoped
public class ProductsBean {
	
	public ProductsBean() {
		//do nothing
	}

	private List<Product> products = new ArrayList<Product>();

	@Inject
	ProductDao productDao;

	@Enumerated(EnumType.ORDINAL)
	private ConstantsEnum order;// = ConstantsEnum.ASC;
	
	@Produces
	public ConstantsEnum getEnumInitValue() {
		if(order == null) {
			order =  ConstantsEnum.ASC;
		}
		return order;
		//return ConstantsEnum.ASC;
	}
	
	@Produces
	public ConstantsEnum getOrder() {
//		return (order = ConstantsEnum.DESC);
		return order;
	}

	@Inject
	public void setOrder(ConstantsEnum order) {
		this.order = order;
	}

	public List<Product> getProducts() {
		if(products==null || products.size() < 1) {
			/*products = new ArrayList<Product>();
			products.add(new Product("Product One", 10));
			products.add(new Product("Product Two", 20));*/
//			products = productDao.listProducts();
			products = productDao.listProducts(null,order );
		}
		return products;
	}

}
