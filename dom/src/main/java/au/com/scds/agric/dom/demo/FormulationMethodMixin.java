package au.com.scds.agric.dom.demo;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.Ingredient;

@Mixin
public class FormulationMethodMixin {

	private final FormulationMethod method;

	public FormulationMethodMixin(FormulationMethod method) {
		this.method = method;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public FormulationMethod addStep(String description, Integer order) {
		formulationRepo.createMethodStep(this.method, description, order);
		return this.method;
	}
	

	@Inject
	IngredientRepository ingredientRepo;
	
	@Inject
	FormulationRepository formulationRepo;
}
