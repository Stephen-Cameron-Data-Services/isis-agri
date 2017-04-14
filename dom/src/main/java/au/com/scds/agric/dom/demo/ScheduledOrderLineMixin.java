/*
MIT License

Copyright (c) 2017 Alexander Stephen Cameron (http://stephencamerondataservices.com.au/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
