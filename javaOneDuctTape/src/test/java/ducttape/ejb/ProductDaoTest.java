package ducttape.ejb;

import java.util.List;

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
	   List<Product> productsList = productdao.listProducts();
	   assertThat(productsList.size(),is(4));
   }
}
