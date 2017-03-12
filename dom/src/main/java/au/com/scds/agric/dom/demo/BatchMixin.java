package au.com.scds.agric.dom.demo;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ProductItem;

@Mixin
public class BatchMixin {

	private final Batch batch;

	public BatchMixin(Batch batch) {
		this.batch = batch;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Batch createProductItem(String serialNumber){
		ProductItem item = productItemRepo.createProductItem(serialNumber);
		item.setBatch(this.batch);
		this.batch.getProductItems().add(item);
		return this.batch;
	}
	
	@javax.inject.Inject
	ProductItemRepository productItemRepo;
}
