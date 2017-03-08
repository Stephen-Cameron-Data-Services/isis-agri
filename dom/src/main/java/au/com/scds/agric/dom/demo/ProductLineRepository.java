package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = ProductLine.class)
public class ProductLineRepository {

	public ProductLine createProductLine(final String name, final ProductType type) {
		final ProductLine productLine = new ProductLine();
		productLine.setName(name);
		productLine.setProductType(type);
		serviceRegistry.injectServicesInto(productLine);
		repositoryService.persistAndFlush(productLine);
		return productLine;
	}
	
	public List<ProductLine> listAll() {
		return repositoryService.allInstances(ProductLine.class);
	}

	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;


}
