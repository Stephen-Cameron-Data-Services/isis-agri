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
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientManufacturer;
import au.com.scds.agric.dom.demo.data.IngredientSupplier;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.SiUnit;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = IngredientSupply.class)
public class SupplierRepository {

	public IngredientSupply createIngredientSupply(Ingredient ingredient, IngredientSupplier supplier, float initialQuantity, SiUnit unit) {
		final IngredientSupply supply = new IngredientSupply();
		supply.setSupplier(supplier);
		supply.setQuantityInitial(initialQuantity);
		supply.setQuantityRemaining(initialQuantity);
		supply.setUnit(unit);
		serviceRegistry.injectServicesInto(supply);
		repositoryService.persistAndFlush(supply);
		return supply;
	}

	public List<IngredientSupply> listAllIngredientSupply() {
		return repositoryService.allInstances(IngredientSupply.class);
	}

	public IngredientSupply findIngredientSupplyById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(IngredientSupply.class, "findById", "id", id));
	}

	public IngredientSupplier createIngredientSupplier(String name) {
		final IngredientSupplier supplier = new IngredientSupplier();
		supplier.setName(name);
		serviceRegistry.injectServicesInto(supplier);
		repositoryService.persistAndFlush(supplier);
		return supplier;
	}

	public List<IngredientSupplier> listAllIngredientSupplier() {
		return repositoryService.allInstances(IngredientSupplier.class);
	}

	public IngredientSupplier findIngredientSupplierById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(IngredientSupplier.class, "findById", "id", id));
	}

	public IngredientManufacturer createIngredientManufacturer(String name) {
		final IngredientManufacturer Manufacturer = new IngredientManufacturer();
		Manufacturer.setName(name);
		serviceRegistry.injectServicesInto(Manufacturer);
		repositoryService.persistAndFlush(Manufacturer);
		return Manufacturer;
	}

	public List<IngredientManufacturer> listAllIngredientManufacturer() {
		return repositoryService.allInstances(IngredientManufacturer.class);
	}

	public IngredientManufacturer findIngredientManufacturerById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(IngredientManufacturer.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
