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
