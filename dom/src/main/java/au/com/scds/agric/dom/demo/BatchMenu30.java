package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ProductLine;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Batch", menuOrder = "30")
public class BatchMenu30 {
	
	@Action()
	@MemberOrder(sequence = "1")
	public Batch createBatch() {
		return batchRepo.createBatch();
	}

	@Action()
	@MemberOrder(sequence = "2")
	public Batch createBatchForProductLine(ProductLine productLine) {
		return batchRepo.createBatch(productLine);
	}
	
	public List<ProductLine> choices0CreateBatchForProductLine(){
		return productLineRepo.listAllProductLine();
	}
	
	@Action()
	@MemberOrder(sequence = "3")
	public List<Batch> listAll() {
		return batchRepo.listAll();
	}

	@Action()
	@MemberOrder(sequence = "4")
	public Batch findById(final String id) {
		return batchRepo.findById(id);
	}

	@Inject
	BatchRepository batchRepo;
	
	@Inject
	ProductLineRepository productLineRepo;
}
