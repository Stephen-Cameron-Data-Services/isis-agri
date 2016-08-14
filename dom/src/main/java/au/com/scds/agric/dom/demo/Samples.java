package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, repositoryFor = Sample.class)
public class Samples {

	public List<Manufacturer> listAll() {
		return repositoryService.allInstances(Manufacturer.class);
	}

	public List<Manufacturer> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Manufacturer.class, "findById", "id", id));
	}

	public Sample createSample(Sampled sampled) {
		if (sampled == null)
			return null;
		final Sample sample = new Sample();
		sample.setSampled(sampled);
		serviceRegistry.injectServicesInto(sample);
		repositoryService.persistAndFlush(sample);
		return sample;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
