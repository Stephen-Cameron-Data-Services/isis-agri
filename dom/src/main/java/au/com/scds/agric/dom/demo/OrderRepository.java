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

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.OrderLineBatch;
import au.com.scds.agric.dom.demo.data.Batch;
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
	
	public OrderLineBatch createOrderLineBatch(ScheduledOrderLine scheduled, Batch batch, Float value) {
		if(scheduled == null)
			return null;
		final OrderLineBatch orderLineBatch = new OrderLineBatch();
		orderLineBatch.setScheduledOrderLine(scheduled);
		orderLineBatch.setBatch(batch);
		orderLineBatch.setPercentageOfBatch(value);
		serviceRegistry.injectServicesInto(orderLineBatch);
		repositoryService.persistAndFlush(orderLineBatch);
		return orderLineBatch;
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;





}
