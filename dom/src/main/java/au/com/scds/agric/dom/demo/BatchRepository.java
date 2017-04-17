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

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Batch2;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.SiUnit;
import au.com.scds.agric.dom.simple.SimpleObject;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Batch.class)
public class BatchRepository {

	public Batch createBatch(ProductLine productLine) {
		if (productLine == null)
			return null;
		final Batch batch = new Batch();
		batch.setProductLine(productLine);
		serviceRegistry.injectServicesInto(batch);
		repositoryService.persistAndFlush(batch);
		productLine.getBatches().add(batch);
		return batch;
	}

	public List<Batch> listAll() {
		return repositoryService.allInstances(Batch.class);
	}

	public Batch findById(String id) {
		return repositoryService.firstMatch(new QueryDefault<>(Batch.class, "findById", "id", id));
	}
	
	public BatchComponent createComponent(Batch batch, IngredientSupply supply, Ingredient ingredient, float quantity, SiUnit unit) {
		if (batch == null)
			return null;
		final BatchComponent component = new BatchComponent();
		component.setBatch(batch);
		component.setIngredient(ingredient);
		component.setParentSupply(supply);
		component.setQuantity(quantity);
		component.setUnit(unit);
		serviceRegistry.injectServicesInto(component);
		repositoryService.persistAndFlush(component);
		return component;
	}
	
	public List<Batch> listAvailableBatchesOfProductLine(ProductLine productLine) {
		// TODO Auto-generated method stub
		List<Batch> temp = new ArrayList<>();
		temp.add(createBatch(productLine));
		return temp;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;



}
