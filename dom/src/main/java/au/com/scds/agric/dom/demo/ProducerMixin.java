package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

@Mixin
public class ProducerMixin {

	private final Producer producer;

	public ProducerMixin(Producer producer) {
		this.producer = producer;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public ProductLine addProductLine(String name, ProductType type) {
		ProductLine productLine = productLineRepo.createProductLine(this.producer, name, type);
		this.producer.getProductLines().add(productLine);
		return productLine;
	}

	public List<ProductType> choices1AddProductLine() {
		return productTypeRepo.listAll();
	}

	@Inject
	ProductLineRepository productLineRepo;
	@Inject
	ProductTypeRepository productTypeRepo;
}
