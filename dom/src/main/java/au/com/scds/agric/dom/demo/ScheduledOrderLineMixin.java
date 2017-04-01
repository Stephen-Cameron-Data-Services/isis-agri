package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.OrderLineBatch;
import au.com.scds.agric.dom.demo.data.ScheduledOrderLine;

@Mixin
public class ScheduledOrderLineMixin {
	
	private final ScheduledOrderLine scheduled;

	public ScheduledOrderLineMixin(ScheduledOrderLine scheduled) {
		this.scheduled = scheduled;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public OrderLineBatch createOrderLineBatch(Batch batch, Float percentageOfBatch) {
		OrderLineBatch olb = orderRepo.createOrderLineBatch(this.scheduled, batch, percentageOfBatch);
		scheduled.getScheduledBatchPortions().add(olb);
		batch.getBatchOrderLines().add(olb);
		return olb;
	}
	
	public List<Batch> choices0CreateOrderLineBatch(){
		return batchRepo.listAvailableBatchesOfProductLine(this.scheduled.getProductLine());
	}
	
	@Inject 
	OrderRepository orderRepo;
	@Inject 
	BatchRepository batchRepo;

}
