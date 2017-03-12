package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientSupplier;
import au.com.scds.agric.dom.demo.data.IngredientSupply;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
public class SupplierRepository {

	public IngredientSupplier createIngredientSupplier(String name) {
		final IngredientSupplier supplier = new IngredientSupplier();
		supplier.setName(name);
		serviceRegistry.injectServicesInto(supplier);
		repositoryService.persistAndFlush(supplier);
		return supplier;
	}
	
	public List<IngredientSupplier> listAll() {
		return repositoryService.allInstances(IngredientSupplier.class);
	}

	public List<IngredientSupplier> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(IngredientSupplier.class, "findById", "id", id));
	}
	
	public IngredientSupply createIngredientSupply(Ingredient ingredient, IngredientSupplier supplier) {
		final IngredientSupply supply = new IngredientSupply();
		supply.setSupplier(supplier);
		serviceRegistry.injectServicesInto(supply);
		repositoryService.persistAndFlush(supply);
		return supply;
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
