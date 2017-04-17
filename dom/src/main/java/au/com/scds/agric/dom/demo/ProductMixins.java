package au.com.scds.agric.dom.demo;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ProductItem;

public class ProductMixins {

	@Mixin
	public static class Batch_addProductItem {

		private final Batch batch;

		public Batch_addProductItem(Batch batch) {
			this.batch = batch;
		}

		@Action()
		@ActionLayout(contributed = Contributed.AS_ACTION)
		public void $$(String serialNumber) {
			ProductItem item = productItemRepo.createProductItem(this.batch.getProductLine(), serialNumber);
			item.setBatch(this.batch);
			this.batch.getProductItems().add(item);
			return;
		}

		@Inject
		ProductItemRepository productItemRepo;
	}
}
