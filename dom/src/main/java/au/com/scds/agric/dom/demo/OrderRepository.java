package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.NewOrderLine;
import au.com.scds.agric.dom.demo.data.Order;
import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.ProductLine;

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
	
	public NewOrderLine createNewOrderLine(Order order, ProductLine productLine) {
		if(order == null || productLine == null)
			return null;
		final NewOrderLine line = new NewOrderLine();
		line.setOrder(order);
		line.setProductLine(productLine);
		serviceRegistry.injectServicesInto(line);
		repositoryService.persistAndFlush(line);
		return line;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;
}
