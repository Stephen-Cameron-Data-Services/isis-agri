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
import au.com.scds.agric.dom.demo.data.SiUnit;

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
	public FormulationMethod createMethod(String name) {
		FormulationMethod method = formulationRepo.createFormulationMethod(name);
		this.formulation.setMethod(method);
		return method;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Formulation addComponentIngredient(Ingredient ingredient, Float quantity, SiUnit unit) {
		FormulationComponent component = formulationRepo.createFormulationComponent(this.formulation, ingredient, quantity, unit);
		this.formulation.getComponents().add(component);
		return this.formulation;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Ingredient createComponentIngredient(String ingredientName, String ingredientDescription, Float quantity, SiUnit  unit) {
		Ingredient ingredient = ingredientRepo.createIngredient(ingredientName, ingredientDescription);
		addComponentIngredient(ingredient, quantity, unit);
		return ingredient;
	}
	
	@Inject
	IngredientRepository ingredientRepo;
	
	@Inject
	FormulationRepository formulationRepo;
}
