package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.SiUnit;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = SiUnit.class)
public class SiUnitRepository {

	public SiUnit createSiUnit(String unitName) {
		if (unitName == null)
			return null;
		final SiUnit SiUnit = new SiUnit();
		SiUnit.setName(unitName);
		serviceRegistry.injectServicesInto(SiUnit);
		repositoryService.persistAndFlush(SiUnit);
		return SiUnit;
	}
	
	public List<SiUnit> listAll() {
		return repositoryService.allInstances(SiUnit.class);
	}
	
	public SiUnit findById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(SiUnit.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}