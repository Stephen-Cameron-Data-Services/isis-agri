package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.CompletedOrderLine;
import au.com.scds.agric.dom.demo.data.Order;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ScheduledOrderLine;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Order.class)
public class OrderRepository {

	public List<Order> listAll() {
		return repositoryService.allInstances(Order.class);
	}

	public List<Order> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Order.class, "findById", "id", id));
	}

	public Order createOrder(/*String id*/) {
		final Order order = new Order();
		//order.setId(id);
		serviceRegistry.injectServicesInto(order);
		repositoryService.persistAndFlush(order);
		return order;
	}
	
	public OrderLine createOrderLine(Order order, ProductLine productLine) {
		if(order == null || productLine == null)
			return null;
		final OrderLine line = new OrderLine();
		line.setOrder(order);
		line.setProductLine(productLine);
		serviceRegistry.injectServicesInto(line);
		repositoryService.persistAndFlush(line);
		return line;
	}
	
	public ScheduledOrderLine createScheduledOrderLine(OrderLine orderLine) {
		if(orderLine == null)
			return null;
		final ScheduledOrderLine scheduled = new ScheduledOrderLine();
		scheduled.setOrder(orderLine.getOrder());
		scheduled.setOrderLine(orderLine);
		serviceRegistry.injectServicesInto(scheduled);
		//now wrapped so unlink
		orderLine.setOrder(null);
		repositoryService.persistAndFlush(scheduled);
		return scheduled;
	}
	
	public void destroyScheduledOrderLine(ScheduledOrderLine scheduled) {
		if(scheduled == null)
			return;
		repositoryService.removeAndFlush(scheduled);
	}

	public CompletedOrderLine createCompletedOrderLine(ScheduledOrderLine scheduled) {
		if(scheduled == null)
			return null;
		final CompletedOrderLine completed = new CompletedOrderLine();
		completed.setOrder(scheduled.getOrder());
		completed.setOrderLine(scheduled.getOrderLine());
		serviceRegistry.injectServicesInto(scheduled);
		repositoryService.persistAndFlush(scheduled);
		return completed;
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;




}
