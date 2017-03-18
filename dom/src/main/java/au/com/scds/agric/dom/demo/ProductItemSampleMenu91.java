package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.Sample;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Sample", menuOrder = "91")
public class ProductItemSampleMenu91 {

	@Action()
	@MemberOrder(sequence = "1")
	public Sample createProductItemSample(ProductItem item) {
		return sampleRepo.createSample(item);
	}

	@javax.inject.Inject
	SampleRepository sampleRepo;
}
