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
import au.com.scds.agric.dom.demo.data.NewOrderLine;

@Mixin
public class OrderMixin {

	private final Order order;

	public OrderMixin(Order order) {
		this.order = order;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Order addLineOfProductLine(ProductLine productLine) {
		//TODO guard against adding same twice
		NewOrderLine orderLine = orderRepo.createNewOrderLine(this.order, productLine);
		this.order.getNewOrderLines().add(orderLine);
		return this.order;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public OrderLine findLineOfProductLine(ProductLine productLine) {
		for(OrderLine line : this.order.getNewOrderLines()){
			if(line.getProductLine().equals(productLine)){
				return line;
			}
		}
		for(OrderLine line : this.order.getScheduledOrderLines()){
			if(line.getProductLine().equals(productLine)){
				return line;
			}
		}
		for(OrderLine line : this.order.getCompletedOrderLines()){
			if(line.getProductLine().equals(productLine)){
				return line;
			}
		}
		return null;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public ScheduledOrderLine scheduleOrderLine(NewOrderLine newOrderLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public CompletedOrderLine completeOrderLine(ScheduledOrderLine newOrderLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Inject
	OrderRepository orderRepo;
}
