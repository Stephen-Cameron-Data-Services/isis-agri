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
import au.com.scds.agric.dom.demo.data.FormulationStep;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.SiUnit;

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

	public FormulationMethod createFormulationMethod(String name) {
		final FormulationMethod method = new FormulationMethod();
		method.setName(name);
		serviceRegistry.injectServicesInto(method);
		repositoryService.persistAndFlush(method);
		return method;
	}

	public FormulationComponent createFormulationComponent(Formulation formulation, Ingredient ingredient,
			Float quantity, SiUnit unit) {
		if (formulation == null || ingredient == null) {
			return null;
		}
		final FormulationComponent component = new FormulationComponent();
		component.setFormulation(formulation);
		component.setIngredient(ingredient);
		component.setQuantity(quantity);
		component.setUnit(unit);
		serviceRegistry.injectServicesInto(component);
		repositoryService.persistAndFlush(component);
		return component;
	}

	public FormulationStep createMethodStep(FormulationMethod method, String description, Integer order) {
		if (method == null || description == null || order == null) {
			return null;
		}
		final FormulationStep step = new FormulationStep();
		step.setFormulationMethod(method);
		step.setDescription(description);
		serviceRegistry.injectServicesInto(step);
		repositoryService.persistAndFlush(step);
		if (method.getSteps().isEmpty() || order > method.getSteps().size()) {
			// add
			method.getSteps().add(step);
			step.setOrder(method.getSteps().size());
		} else {
			// insert
			method.getSteps().add(order - 1, step);
			for (int i = order; i < method.getSteps().size(); i++) {
				method.getSteps().get(i).setOrder(i + 1);
			}
		}
		return step;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
