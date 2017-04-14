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

import javax.inject.Inject;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import au.com.scds.agric.dom.demo.data.Order;
import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ScheduledOrderLine;
import au.com.scds.agric.dom.demo.data.CompletedOrderLine;

@Mixin
public class OrderMixin {

	private final Order order;

	public OrderMixin(Order order) {
		this.order = order;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Order createOrderLine(ProductLine productLine) {
		// TODO guard against adding same twice
		OrderLine orderLine = orderRepo.createOrderLine(this.order, productLine);
		this.order.getOrderLines().add(orderLine);
		return this.order;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public OrderLine findOrderLine(ProductLine productLine) {
		for (OrderLine line : this.order.getOrderLines()) {
			if (line.getProductLine().equals(productLine)) {
				return line;
			}
		}
		for (ScheduledOrderLine line : this.order.getScheduledOrderLines()) {
			if (line.getOrderLine().getProductLine().equals(productLine)) {
				return line.getOrderLine();
			}
		}
		for (CompletedOrderLine line : this.order.getCompletedOrderLines()) {
			if (line.getOrderLine().getProductLine().equals(productLine)) {
				return line.getOrderLine();
			}
		}
		return null;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public ScheduledOrderLine scheduleOrderLine(OrderLine orderLine) {
		if (orderLine == null || !(orderLine.getOrder().equals(this.order))) {
			return null;
		}
		ScheduledOrderLine scheduled = orderRepo.createScheduledOrderLine(orderLine);
		if (scheduled != null) {
			// remove order-line from new list
			order.getOrderLines().remove(orderLine);
			// add (the now wrapped) order-line to scheduled
			order.getScheduledOrderLines().add(scheduled);
		}
		return scheduled;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public CompletedOrderLine completeOrderLine(ScheduledOrderLine scheduled) {
		if (scheduled == null || !(scheduled.getOrder().equals(this.order))) {
			return null;
		}
		CompletedOrderLine completed = orderRepo.createCompletedOrderLine(scheduled);
		if (completed != null) {
			// remove the wrapped order-line from scheduled list
			order.getScheduledOrderLines().remove(scheduled);
			// add the wrapped order-line to completed list
			order.getCompletedOrderLines().add(completed);
			// destroy the scheduled as cannot coexist with matching completed
			orderRepo.destroyScheduledOrderLine(scheduled);
		}
		return completed;
	}

	@Inject
	OrderRepository orderRepo;
}
