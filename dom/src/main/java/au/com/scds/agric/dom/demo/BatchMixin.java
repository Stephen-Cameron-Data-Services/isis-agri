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
