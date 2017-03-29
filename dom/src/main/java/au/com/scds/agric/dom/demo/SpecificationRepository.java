package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import au.com.scds.agric.dom.demo.data.Specification;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.IngredientSupply;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Specification.class)
public class SpecificationRepository {

	public Specification createSpecification(String name) {
		final Specification specification = new Specification();
		specification.setName(name);
		serviceRegistry.injectServicesInto(specification);
		repositoryService.persistAndFlush(specification);
		return specification;
	}
	
	public List<Specification> listAll() {
		return repositoryService.allInstances(Specification.class);
	}
	
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;


}
