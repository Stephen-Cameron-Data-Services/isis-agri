package au.com.scds.agric.dom.demo;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import au.com.scds.agric.dom.demo.data.Ingredient;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Ingredient.class)
public class IngredientRepository {

	public Ingredient createIngredient(String name) {
		final Ingredient ingredient = new Ingredient();
		ingredient.setName(name);
		serviceRegistry.injectServicesInto(ingredient);
		repositoryService.persistAndFlush(ingredient);
		return ingredient;
	}
	
	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;

}
