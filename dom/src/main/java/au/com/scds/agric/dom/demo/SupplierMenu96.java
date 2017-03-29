package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
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
@DomainServiceLayout(named = "Supplier", menuOrder = "96")
public class SupplierMenu96 {

	@Action()
	@MemberOrder(sequence = "1")
	public IngredientSupply createIngredientSupply(Ingredient ingredient, IngredientSupplier supplier, float initialQuantity, SiUnit unit) {
		return supplierRepo.createIngredientSupply(ingredient, supplier, initialQuantity, unit);
	}
	
	public List<Ingredient> choices0CreateIngredientSupply() {
		return ingredientRepo.listAll();
	}

	public List<IngredientSupplier> choices1CreateIngredientSupply() {
		return listAllIngredientSupplier();
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

	@Action()
	@MemberOrder(sequence = "4")
	public IngredientSupplier createIngredientSupplier(String name) {
		return supplierRepo.createIngredientSupplier(name);
	}

	@Action()
	@MemberOrder(sequence = "5")
	public List<IngredientSupplier> listAllIngredientSupplier() {
		return supplierRepo.listAllIngredientSupplier();
	}
	
	
	@Action()
	@MemberOrder(sequence = "7")
	public IngredientManufacturer createIngredientManufacturer(String name) {
		return supplierRepo.createIngredientManufacturer(name);
	}

	@Action()
	@MemberOrder(sequence = "8")
	public List<IngredientManufacturer> listAllIngredientManufacturer() {
		return supplierRepo.listAllIngredientManufacturer();
	}

	
	@javax.inject.Inject
	SupplierRepository supplierRepo;
	@javax.inject.Inject
	IngredientRepository ingredientRepo;
}
