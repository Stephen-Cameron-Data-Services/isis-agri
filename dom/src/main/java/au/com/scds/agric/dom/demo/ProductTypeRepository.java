package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = ProductType.class)
public class ProductTypeRepository {

	public ProductType createProductType(final String name) {
		final ProductType productType = new ProductType();
		productType.setName(name);
		serviceRegistry.injectServicesInto(productType);
		repositoryService.persistAndFlush(productType);
		return productType;
	}
	
	public List<ProductType> listAll() {
		return repositoryService.allInstances(ProductType.class);
	}

	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;


}
