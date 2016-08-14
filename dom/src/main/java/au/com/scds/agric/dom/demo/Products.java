package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, repositoryFor = ProductItem.class)
public class Products {

	public List<ProductItem> listAll() {
		return repositoryService.allInstances(ProductItem.class);
	}

	public List<ProductItem> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(ProductItem.class, "findById", "id", id));
	}

	public ProductItem create(/*String id*/) {
		final ProductItem ProductItem = new ProductItem();
		//ProductItem.setId(id);
		serviceRegistry.injectServicesInto(ProductItem);
		repositoryService.persistAndFlush(ProductItem);
		return ProductItem;
	}
	
	public ProductLine createProductLine(String name) {
		final ProductLine ProductLine = new ProductLine();
		ProductLine.setName(name);
		serviceRegistry.injectServicesInto(ProductLine);
		repositoryService.persistAndFlush(ProductLine);
		return ProductLine;
	}
	
	public ProductType createProductType(String name) {
		final ProductType ProductType = new ProductType();
		ProductType.setName(name);
		serviceRegistry.injectServicesInto(ProductType);
		repositoryService.persistAndFlush(ProductType);
		return ProductType;
	}


	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
