package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Ingredient;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
public class SupplierRepository {

	/*public List<Supplier> listAll() {
		return repositoryService.allInstances(Supplier.class);
	}

	public List<Supplier> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Supplier.class, "findById", "id", id));
	}

	public Supplier createSupplier(String name) {
		final Supplier supplier = new Supplier();
		supplier.setName(name);
		serviceRegistry.injectServicesInto(supplier);
		repositoryService.persistAndFlush(supplier);
		return supplier;
	}
	
	public Supply createSupply(Ingredient ingredient, Supplier supplier) {
		final Supply supply = new Supply();
		supply.setParentIngredient(ingredient);
		supply.setSupplier(supplier);
		serviceRegistry.injectServicesInto(supply);
		repositoryService.persistAndFlush(supply);
		return supply;
	}*/
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
