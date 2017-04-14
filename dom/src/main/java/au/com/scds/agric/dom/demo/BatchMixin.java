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

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.SiUnit;

@Mixin
public class BatchMixin {

	private final Batch batch;

	public BatchMixin(Batch batch) {
		this.batch = batch;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Batch createProductItem(String serialNumber) {
		ProductItem item = productItemRepo.createProductItem(this.batch.getProductLine(), serialNumber);
		item.setBatch(this.batch);
		this.batch.getProductItems().add(item);
		return this.batch;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public BatchComponent addComponent(Ingredient ingredient, float quantity, SiUnit unit) {
		BatchComponent component = batchRepo.createComponent(this.batch, ingredient, quantity, unit);
		this.batch.getBatchComponents().add(component);
		return component;
	}

	@javax.inject.Inject
	BatchRepository batchRepo;
	@javax.inject.Inject
	ProductItemRepository productItemRepo;
}
