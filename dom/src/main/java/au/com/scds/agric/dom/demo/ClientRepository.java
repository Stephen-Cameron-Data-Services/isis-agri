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

import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.Order;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Client.class)
public class ClientRepository {

	public Client createClient(String name) {
		final Client client = new Client();
		client.setName(name);
		serviceRegistry.injectServicesInto(client);
		repositoryService.persistAndFlush(client);
		return client;
	}
	
	public List<Client> listAll() {
		return repositoryService.allInstances(Client.class);
	}

	public List<Client> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Client.class, "findById", "id", id));
	}
	
	public Order createOrder(Client client) {
		final Order order = new Order();
		order.setClient(client);
		serviceRegistry.injectServicesInto(order);
		repositoryService.persistAndFlush(order);
		client.getOrders().add(order);
		return order;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;


}
