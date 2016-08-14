package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Manufacturer.class)
public class Manufacturers {

	public List<Manufacturer> listAll() {
		return repositoryService.allInstances(Manufacturer.class);
	}

	public List<Manufacturer> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Manufacturer.class, "findById", "id", id));
	}

	public Manufacturer createManufacturer(String name) {
		final Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(name);
		serviceRegistry.injectServicesInto(manufacturer);
		repositoryService.persistAndFlush(manufacturer);
		return manufacturer;
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
