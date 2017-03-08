package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.Ingredient;


@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Formulation.class)
public class FormulationRepository {

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
	
	public FormulationComponent createFormulationComponent(Ingredient ingredient,
			Float quantity,
			String units) {
		final FormulationComponent component = new FormulationComponent();
		component.setIngredient(ingredient);
		component.setUnit(units);
		serviceRegistry.injectServicesInto(component);
		repositoryService.persistAndFlush(component);
		return component;
	}
	
	public FormulationMethod createFormulationMethod() {
		final FormulationMethod method = new FormulationMethod();
		serviceRegistry.injectServicesInto(method);
		repositoryService.persistAndFlush(method);
		return method;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;


}

