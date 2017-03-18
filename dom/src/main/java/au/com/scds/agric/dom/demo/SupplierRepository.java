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
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = IngredientSupply.class)
public class SupplierRepository {

	public IngredientSupplier createIngredientSupplier(String name) {
		final IngredientSupplier supplier = new IngredientSupplier();
		supplier.setName(name);
		serviceRegistry.injectServicesInto(supplier);
		repositoryService.persistAndFlush(supplier);
		return supplier;
	}
	
	public List<IngredientSupplier> listAllIngredientSupplier() {
		return repositoryService.allInstances(IngredientSupplier.class);
	}

	public IngredientSupplier findIngredientSupplierById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(IngredientSupplier.class, "findById", "id", id));
	}
	
	public IngredientSupply createIngredientSupply(Ingredient ingredient, IngredientSupplier supplier) {
		final IngredientSupply supply = new IngredientSupply();
		supply.setSupplier(supplier);
		serviceRegistry.injectServicesInto(supply);
		repositoryService.persistAndFlush(supply);
		return supply;
	}
	
	public List<IngredientSupply> listAllIngredientSupply() {
		return repositoryService.allInstances(IngredientSupply.class);
	}

	public IngredientSupply findIngredientSupplyById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(IngredientSupply.class, "findById", "id", id));
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
