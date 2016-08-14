package au.com.scds.agric.fixture.dom.orders;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.google.common.collect.Lists;

import au.com.scds.agric.dom.demo.Client;
import au.com.scds.agric.dom.demo.Clients;
import au.com.scds.agric.dom.demo.NewOrderLine;
import au.com.scds.agric.dom.demo.Order;
import au.com.scds.agric.dom.demo.Orders;
import au.com.scds.agric.dom.demo.Person;
import au.com.scds.agric.dom.demo.Persons;
import au.com.scds.agric.dom.demo.ProductLine;
import au.com.scds.agric.dom.demo.ProductType;
import au.com.scds.agric.dom.demo.Products;
import au.com.scds.agric.dom.simple.SimpleObject;
import au.com.scds.agric.fixture.xml.generated.NewOrderLineFixture;
import au.com.scds.agric.fixture.xml.generated.ObjectFactory;
import au.com.scds.agric.fixture.xml.generated.OrderFixture;
import au.com.scds.agric.fixture.xml.generated.OrdersFixture;

public class RecreateOrders extends FixtureScript {

	public RecreateOrders() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
    private final List<Order> orders = Lists.newArrayList();

	@Override
	protected void execute(ExecutionContext ec) {

		//domain repositories
		Clients clientsRepo = new Clients();
		Orders ordersRepo = new Orders();
		Products productsRepo = new Products();
		Persons personsRepo = new Persons();

		try {
			InputStream is = this.getClass()
					.getResourceAsStream("/au/com/scds/agric/fixture/dom/orders.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			OrdersFixture ordersFixture = ((JAXBElement<OrdersFixture>) jaxbUnmarshaller.unmarshal(is))
					.getValue();
			for(OrderFixture orderFixture : ordersFixture.getOrder()){
				Order order = wrap(ordersRepo).createOrder();
				wrap(order).setTaken(orderFixture.getTaken().toGregorianCalendar().getTime());
				Client client = wrap(clientsRepo).create(orderFixture.getClient().getName());
				wrap(client).addOrder(order);
				Person takenBy = wrap(personsRepo).createPerson(orderFixture.getTakenBy().getFirstname(),orderFixture.getTakenBy().getLastname());
				wrap(order).setTakenBy(takenBy);
				for(NewOrderLineFixture orderLineFixture : orderFixture.getOrderLine()){
					NewOrderLine orderLine  = wrap(ordersRepo).createNewOrderLine();
					ProductLine prodLine = wrap(productsRepo).createProductLine(orderLineFixture.getProductLine().getName());
					ProductType prodType = wrap(productsRepo).createProductType(orderLineFixture.getProductLine().getProductType().getName());
					wrap(prodLine).setProductType(prodType);
					wrap(orderLine).setProductLine(prodLine);
					wrap(orderLine).setQuantity(orderLineFixture.getQuantity());
					Person addedBy = wrap(personsRepo).createPerson(orderLineFixture.getAddedBy().getFirstname(),orderLineFixture.getAddedBy().getLastname());
					wrap(orderLine).setAddedBy(addedBy);
					wrap(orderLine).setAddedOn(orderLineFixture.getAddedOn().toGregorianCalendar().getTime());
					wrap(order).addOrderLine(orderLine);
				}
				orders.add(order);
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public List<Order> getOrders() {
		return orders;
	}

}
