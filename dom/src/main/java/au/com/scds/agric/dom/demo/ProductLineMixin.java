package au.com.scds.agric.dom.demo;

import org.apache.isis.applib.annotation.Mixin;
import au.com.scds.agric.dom.demo.data.ProductLine;

@Mixin
public class ProductLineMixin {

	private final ProductLine productLine;

	public ProductLineMixin(ProductLine productLine) {
		this.productLine = productLine;
	}
}
