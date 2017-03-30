package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductPack;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = ProductItem.class)
public class ProductPackRepository {

	public ProductPack createProductPack(ProductLine productLine) {
		final ProductPack productPack = new ProductPack();
		productPack.setProductLine(productLine);
		serviceRegistry.injectServicesInto(productPack);
		repositoryService.persistAndFlush(productPack);
		return productPack;
	}
	
	public List<ProductItem> listAllProductItems() {
		return repositoryService.allInstances(ProductItem.class);
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;


}
