package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.Producer;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Producer.class)
public class ProducerRepository {

	public Producer createProducer(final String name) {
		final Producer producer = new Producer();
		producer.setName(name);
		serviceRegistry.injectServicesInto(producer);
		repositoryService.persistAndFlush(producer);
		return producer;
	}
	
	public List<Producer> listAll() {
		return repositoryService.allInstances(Producer.class);
	}

	public Producer findById(String id) {
		return repositoryService.firstMatch(new QueryDefault<>(Producer.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
