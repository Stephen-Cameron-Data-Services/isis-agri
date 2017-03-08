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
public class FormulationMixin {

	private final Formulation formulation;

	public FormulationMixin(Formulation formulation) {
		this.formulation = formulation;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Formulation addMethod(FormulationMethod method) {
		this.formulation.setMethod(method);
		return this.formulation;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public FormulationMethod createMethod() {
		FormulationMethod method = formulationRepo.createFormulationMethod();
		this.formulation.setMethod(method);
		return method;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Formulation addComponentIngredient(Ingredient ingredient, Float quantity, String units) {
		FormulationComponent component = formulationRepo.createFormulationComponent(ingredient, quantity, units);
		this.formulation.getComponents().add(component);
		return this.formulation;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Ingredient createComponentIngredient(String ingredientName, Float quantity, String units) {
		Ingredient ingredient = ingredientRepo.createIngredient(ingredientName);
		addComponentIngredient(ingredient, quantity, units);
		return ingredient;
	}
	
	@Inject
	IngredientRepository ingredientRepo;
	
	@Inject
	FormulationRepository formulationRepo;
}
