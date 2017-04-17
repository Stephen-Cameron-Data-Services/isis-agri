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

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientManufacturer;
import au.com.scds.agric.dom.demo.data.IngredientSupplier;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
import au.com.scds.agric.dom.demo.data.IngredientSupplier;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.SiUnit;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Supplies", menuOrder = "60")
public class SuppliesMenu60 {

	@Action()
	@MemberOrder(sequence = "1")
	public IngredientSupply createIngredientSupply(Ingredient ingredient, @Parameter(optionality=Optionality.OPTIONAL) IngredientSupplier supplier, float initialQuantity, SiUnit unit) {
		return supplierRepo.createIngredientSupply(ingredient, supplier, initialQuantity, unit);
	}
	
	public List<Ingredient> choices0CreateIngredientSupply() {
		return ingredientRepo.listAll();
	}

	public List<IngredientSupplier> choices1CreateIngredientSupply() {
		return supplierRepo.listAllIngredientSupplier();
	}

	@Action()
	@MemberOrder(sequence = "2")
	public List<IngredientSupply> listAllIngredientSupply() {
		return supplierRepo.listAllIngredientSupply();
	}

	@Action()
	@MemberOrder(sequence = "3")
	public IngredientSupply findIngredientSupplyById(final String id) {
		return supplierRepo.findIngredientSupplyById(id);
	}

	
	@javax.inject.Inject
	SupplierRepository supplierRepo;
	@javax.inject.Inject
	IngredientRepository ingredientRepo;

}
