package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.ProductLine;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Batch.class)
public class BatchRepository {

	public List<Batch> listAll() {
		return repositoryService.allInstances(Batch.class);
	}

	public Batch createBatch(ProductLine productLine) {
		if(productLine == null)
			return null;
		final Batch batch = new Batch();
		batch.setProductLine(productLine);
		serviceRegistry.injectServicesInto(batch);
		repositoryService.persistAndFlush(batch);
		return batch;
	}

	@Programmatic
	public BatchComponent createComponent(Batch batch) {
		if(batch==null)
			return null;
		final BatchComponent component = new BatchComponent();
		serviceRegistry.injectServicesInto(component);
		repositoryService.persistAndFlush(component);
		return component;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
