package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.Programmatic;

import au.com.scds.agric.dom.demo.data.Order;
import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.NewOrderLine;

@Mixin
public class OrderMixin {

	private final Order order;

	public OrderMixin(Order order) {
		this.order = order;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Order addLineOfProductType(ProductType productType) {
		//TODO guard against adding same twice
		NewOrderLine orderLine = orderRepo.createNewOrderLine(this.order);
		return this.order;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public Order addLineOfProductLine(ProductLine productLine) {
		//TODO guard against adding same twice
		NewOrderLine orderLine = orderRepo.createNewOrderLine(this.order);
		return this.order;
	}
	
	@Programmatic
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

	@Inject
	OrderRepository orderRepo;



}
