package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ProductLine;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Batch", menuOrder = "30")
public class BatchMenu30 {

	@Action()
	@MemberOrder(sequence = "1")
	public Batch create(ProductLine productLine) {
		return batchRepo.createBatch(productLine);
	}
	
	public List<ProductLine> choices0Create(){
		return productLineRepo.listAll();
	}

	@Inject
	BatchRepository batchRepo;
	
	@Inject
	ProductLineRepository productLineRepo;
}
