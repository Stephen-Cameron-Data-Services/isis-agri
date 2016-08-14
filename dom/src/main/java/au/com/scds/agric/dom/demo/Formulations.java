package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Formulation.class)
public class Formulations {

	public List<Formulation> listAll() {
		return repositoryService.allInstances(Formulation.class);
	}

	public List<Formulation> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Formulation.class, "findById", "id", id));
	}

	public Formulation createFormulation(String name) {
		final Formulation formulation = new Formulation();
		formulation.setName(name);
		serviceRegistry.injectServicesInto(formulation);
		repositoryService.persistAndFlush(formulation);
		return formulation;
	}
	
	public Ingredient createIngredient(String name) {
		final Ingredient ingredient = new Ingredient();
		ingredient.setName(name);
		serviceRegistry.injectServicesInto(ingredient);
		repositoryService.persistAndFlush(ingredient);
		return ingredient;
	}
	
	public FormulationComponent createFormulationComponent(Formulation formulation,
			Ingredient ingredient,
			Double quantity,
			String units) {
		final FormulationComponent component = new FormulationComponent();
		component.setParentFormulation(formulation);
		component.setIngredient(ingredient);
		component.setQuantity(quantity);
		component.setUnit(units);
		formulation.addFormulationComponent(component);
		serviceRegistry.injectServicesInto(component);
		repositoryService.persistAndFlush(component);
		return component;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}

