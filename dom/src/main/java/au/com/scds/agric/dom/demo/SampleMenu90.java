package au.com.scds.agric.dom.demo;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.Sample;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Sample", menuOrder = "90")
public class SampleMenu90 {

	@Action()
	@MemberOrder(sequence = "1")
	public Sample createProductItemSample(ProductItem item) {
		return sampleRepo.createSample(item);
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public Sample createBatchSample(Batch batch) {
		return sampleRepo.createSample(batch);
	}
	
	@Action()
	@MemberOrder(sequence = "3")
	public Sample createBatchComponentSample(BatchComponent component) {
		return sampleRepo.createSample(component);
	}
	
	@Action()
	@MemberOrder(sequence = "4")
	public Sample createIngredientSupplySample(IngredientSupply supply) {
		return sampleRepo.createSample(supply);
	}

	@javax.inject.Inject
	SampleRepository sampleRepo;
}
