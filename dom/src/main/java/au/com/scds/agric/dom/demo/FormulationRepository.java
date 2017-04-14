/*
MIT License

Copyright (c) 2017 Alexander Stephen Cameron (http://stephencamerondataservices.com.au/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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

	public Formulation createFormulation(String name) {
		final Formulation formulation = new Formulation();
		formulation.setName(name);
		serviceRegistry.injectServicesInto(formulation);
		repositoryService.persistAndFlush(formulation);
		return formulation;
	}
	
	public List<Formulation> listAll() {
		return repositoryService.allInstances(Formulation.class);
	}

	public List<Formulation> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Formulation.class, "findById", "id", id));
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
			step.setOrder(1);
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
