package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

public class ProductMixins {
	
	@Mixin
	public static class Producer_addProductLine {

		private final Producer producer;

		public Producer_addProductLine(Producer producer) {
			this.producer = producer;
		}

		@Action()
		@ActionLayout(contributed = Contributed.AS_ACTION)
		public ProductLine $$(String name, ProductType type) {
			ProductLine productLine = productLineRepo.createProductLine(this.producer, name, type);
			this.producer.getProductLines().add(productLine);
			return productLine;
		}

		public List<ProductType> choices1$$() {
			return productTypeRepo.listAllProductTypes();
		}

		@Inject
		ProductLineRepository productLineRepo;
		@Inject
		ProductTypeRepository productTypeRepo;
	}

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
