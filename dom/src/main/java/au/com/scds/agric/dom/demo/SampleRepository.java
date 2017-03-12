package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Sampled;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Sample.class)
public class SampleRepository {

	public Sample createSample(Sampled sampled) {
		if (sampled == null)
			return null;
		final Sample sample = new Sample();
		sample.setSampled(sampled);
		serviceRegistry.injectServicesInto(sample);
		repositoryService.persistAndFlush(sample);
		return sample;
	}
	
	public List<Sample> listAll() {
		return repositoryService.allInstances(Sample.class);
	}
	
	public Sample findById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(Sample.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
