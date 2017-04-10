package au.com.scds.agric.dom.demo;

import java.util.Calendar;
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
@DomainServiceLayout(named = "Sample", menuOrder = "92")
public class BatchSampleMenu92 {

	@Action()
	@MemberOrder(sequence = "1")
	public Sample createBatchSample(Batch batch) {
		return sampleRepo.createSample(batch);
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public List<Sample> listAll() {
		return sampleRepo.listAllBatchSamples();
	}

	@Action()
	@MemberOrder(sequence = "3")
	public Sample findById(final String id) {
		return sampleRepo.findById(id);
	}

	@javax.inject.Inject
	SampleRepository sampleRepo;


}
