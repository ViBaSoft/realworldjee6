package ducttape.ejb;

import java.util.List;

import ducttape.common.ConstantsEnum;
import ducttape.ejb.ProductDao;
import ducttape.entities.Product;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(Arquillian.class)
public class ProductDaoTest
{

   @Inject
   private ProductDao productdao;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClasses(ProductDao.class,TestDataGenerator.class)
            .addPackage(Product.class.getPackage())
            .addPackage(ConstantsEnum.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void testIsDeployed()
   {
      Assert.assertNotNull(productdao);
//	   Assert.assertNull(productdao);
   }
   
   @Test
   public void testListProducts() {
//	   List<Product> productsList = productdao.listProducts();
	   List<Product> productsList = productdao.listProducts(null,null);
	   assertThat(productsList.size(),is(4));
   }
   
   @Test
   public void testListProductsSortAsc() {
	   List<Product> productsList = productdao.listProducts(null,ConstantsEnum.ASC); //sort Ascending
	   assertThat(productsList.size(),is(4));
	   assertThat(productsList.get(0).getPrice(),is(10d));
   }
   
   @Test
   public void testListProductsSortDesc() {
	   List<Product> productsList = productdao.listProducts(null,ConstantsEnum.DESC); //sort Ascending
	   assertThat(productsList.size(),is(4));
	   assertThat(productsList.get(0).getPrice(),is(50d));
   }
}
