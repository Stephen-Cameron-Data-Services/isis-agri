package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = ProductItem.class)
public class ProductRepository {

	public List<ProductItem> listAll() {
		return repositoryService.allInstances(ProductItem.class);
	}

	public ProductItem createItem(String serialNumber) {
		final ProductItem productItem = new ProductItem();
		productItem.setSerialNumber(serialNumber);
		serviceRegistry.injectServicesInto(productItem);
		repositoryService.persistAndFlush(productItem);
		return productItem;
	}
	
	public ProductLine createProductLine(String name, ProductType type) {
		final ProductLine productLine = new ProductLine();
		productLine.setName(name);
		productLine.setProductType(type);
		serviceRegistry.injectServicesInto(productLine);
		repositoryService.persistAndFlush(productLine);
		return productLine;
	}
	
	public ProductType createProductType(String name) {
		final ProductType ProductType = new ProductType();
		ProductType.setName(name);
		serviceRegistry.injectServicesInto(ProductType);
		repositoryService.persistAndFlush(ProductType);
		return ProductType;
	}

	public List<ProductType> listAllProductTypes() {
		return repositoryService.allInstances(ProductType.class);
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;


}
