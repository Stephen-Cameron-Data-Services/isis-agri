package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.Sale;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Sale.class)
public class SaleRepository {

	public Sale createSale() {
		final Sale sample = new Sale();
		serviceRegistry.injectServicesInto(sample);
		repositoryService.persistAndFlush(sample);
		return sample;
	}
	
	public List<Sale> listAll() {
		return repositoryService.allInstances(Sale.class);
	}
	
	public Sale findById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(Sale.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
