package au.com.scds.agric.fixture.dom.client;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.ClientMenu100;
import au.com.scds.agric.dom.demo.OrderMixin;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.ProductItemRepository;
import au.com.scds.agric.dom.demo.ProductLineRepository;
import au.com.scds.agric.dom.demo.ProductMenu20;
import au.com.scds.agric.dom.demo.ProductPackRepository;
import au.com.scds.agric.dom.demo.ScheduledOrderLineMixin;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.Clients;
import au.com.scds.agric.dom.demo.data.CompletedOrderLine;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Order;
import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.OrderLineBatch;
import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.ScheduledOrderLine;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductPack;

public class ClientsCreate extends FixtureScript {

	public ClientsCreate() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Client client = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/clients.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Clients _clients = (Clients) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));

			for (Client _c : _clients.getClient()) {
				client = wrap(clientMenu).createClient(_c.getName());
				if (!_c.getOrders().isEmpty()) {
					Order _o = _c.getOrders().get(0);
					Order order = wrap(clientMenu).createOrder(client);
					Person taker = wrap(personMenu).createPerson(_o.getTakenBy().getFirstName(),
							_o.getTakenBy().getLastName());
					order.setTakenBy(taker);
					order.setTaken(_o.getTaken());
					OrderMixin orderMixin = new OrderMixin(order);
					for (OrderLine _line : _o.getOrderLines()) {
						ProductLine _pLine = _line.getProductLine();
						ProductType _pType = _pLine.getProductType();
						ProductType productType = wrap(productMenu).createProductType(_pType.getName());
						ProductLine productLine = wrap(productMenu).createProductLine(_pLine.getName(), productType);
						wrap(orderMixin).createOrderLine(productLine);
						OrderLine newOrderLine = (OrderLine) wrap(orderMixin).findOrderLine(productLine);
						Person adder = wrap(personMenu).createPerson(_line.getAddedBy().getFirstName(),
								_line.getAddedBy().getLastName());
						newOrderLine.setAddedBy(adder);
						newOrderLine.setAddedOn(_line.getAddedOn());
					}
					for (ScheduledOrderLine _line : _o.getScheduledOrderLines()) {
						OrderLine _wrapped = _line.getOrderLine();
						ProductLine _pLine = _wrapped.getProductLine();
						ProductType _pType = _pLine.getProductType();
						ProductType productType = wrap(productMenu).createProductType(_pType.getName());
						ProductLine productLine = wrap(productMenu).createProductLine(_pLine.getName(), productType);
						// create an orderline
						wrap(orderMixin).createOrderLine(productLine);
						OrderLine newOrderLine = (OrderLine) wrap(orderMixin).findOrderLine(productLine);
						Person adder = wrap(personMenu).createPerson(_wrapped.getAddedBy().getFirstName(),
								_wrapped.getAddedBy().getLastName());
						newOrderLine.setAddedBy(adder);
						newOrderLine.setAddedOn(_wrapped.getAddedOn());
						// schedule the orderline
						ScheduledOrderLine scheduled = wrap(orderMixin).scheduleOrderLine(newOrderLine);
						wrap(scheduled).setScheduledOn(_line.getScheduledOn());
						wrap(scheduled).setScheduledBy(wrap(personMenu).createPerson(
								_line.getScheduledBy().getFirstName(), _line.getScheduledBy().getLastName()));
						// append batch portions to scheduled
						ScheduledOrderLineMixin scheduledMixin = new ScheduledOrderLineMixin(scheduled);
						for (OrderLineBatch _portion : _line.getScheduledBatchPortions()) {
							Batch batch = wrap(batchMenu).createBatchForProductLine(productLine);
							OrderLineBatch portion = wrap(scheduledMixin).createOrderLineBatch(batch, new Float(100.0));
						}
					}
					for (CompletedOrderLine _line : _o.getCompletedOrderLines()) {
						OrderLine _wrapped = _line.getOrderLine();
						ProductLine _pLine = _wrapped.getProductLine();
						ProductType _pType = _pLine.getProductType();
						ProductType productType = wrap(productMenu).createProductType(_pType.getName());
						ProductLine productLine = wrap(productMenu).createProductLine(_pLine.getName(), productType);
						wrap(orderMixin).createOrderLine(productLine);
						OrderLine newOrderLine = (OrderLine) wrap(orderMixin).findOrderLine(productLine);
						Person adder = wrap(personMenu).createPerson(_wrapped.getAddedBy().getFirstName(),
								_wrapped.getAddedBy().getLastName());
						newOrderLine.setAddedBy(adder);
						newOrderLine.setAddedOn(_wrapped.getAddedOn());
						ScheduledOrderLine scheduled = wrap(orderMixin).scheduleOrderLine(newOrderLine);
						CompletedOrderLine completed = wrap(orderMixin).completeOrderLine(scheduled);
						completed.setCompletedOn(_line.getCompletedOn());
						completed.setCompletedBy(wrap(personMenu).createPerson(_line.getCompletedBy().getFirstName(),
								_line.getCompletedBy().getLastName()));
						for (ProductPack _pack : _line.getProductPacks()) {
							ProductPack pack = wrap(productPackRepo).createProductPack(productLine);
							completed.getProductPacks().add(pack);
							for (ProductItem _item : _pack.getProductItems()) {
								ProductItem item = wrap(productItemRepo).createProductItem(productLine,
										_item.getSerialNumber());
								pack.getProductItems().add(item);
							}
						}
					}
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * <?xml version="1.0" encoding="UTF-8"?> <tns:clients
	 * xmlns:tns="http://www.example.org/AgricProducerSchema"
	 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=
	 * "http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
	 * <tns:client> <tns:name>tns:name</tns:name> <tns:order>
	 * <tns:taken>2001-12-31T12:00:00</tns:taken> <tns:taken-by>
	 * <tns:first-name>tns:first-name</tns:first-name>
	 * <tns:last-name>tns:last-name</tns:last-name> </tns:taken-by>
	 * <tns:order-line> <tns:product-line> <tns:name>tns:name</tns:name>
	 * <tns:product-type> <tns:name>tns:name</tns:name> </tns:product-type>
	 * </tns:product-line> <tns:added-on>2001-12-31T12:00:00</tns:added-on>
	 * <tns:added-by> <tns:first-name>tns:first-name</tns:first-name>
	 * <tns:last-name>tns:last-name</tns:last-name> </tns:added-by>
	 * </tns:order-line> <tns:scheduled-order-line> <tns:order-line>
	 * <tns:product-line> <tns:name>tns:name</tns:name> <tns:product-type>
	 * <tns:name>tns:name</tns:name> </tns:product-type> </tns:product-line>
	 * <tns:added-on>2001-12-31T12:00:00</tns:added-on> <tns:added-by>
	 * <tns:first-name>tns:first-name</tns:first-name>
	 * <tns:last-name>tns:last-name</tns:last-name> </tns:added-by>
	 * </tns:order-line>
	 * <tns:scheduled-on>2001-12-31T12:00:00</tns:scheduled-on>
	 * <tns:scheduled-by> <tns:first-name>tns:first-name</tns:first-name>
	 * <tns:last-name>tns:last-name</tns:last-name> </tns:scheduled-by>
	 * <tns:orderline-batch-portion> <tns:batch>
	 * <tns:created-on>2001-12-31T12:00:00</tns:created-on> <tns:created-by>
	 * <tns:first-name>tns:first-name</tns:first-name>
	 * <tns:last-name>tns:last-name</tns:last-name> </tns:created-by>
	 * </tns:batch> <tns:percentage-of-batch>0.0</tns:percentage-of-batch>
	 * </tns:orderline-batch-portion> </tns:scheduled-order-line>
	 * <tns:completed-order-line>
	 * <tns:order-line>tns:order-line</tns:order-line>
	 * <tns:completed>2001-12-31T12:00:00</tns:completed> <tns:completed-by>
	 * <tns:first-name>tns:first-name</tns:first-name>
	 * <tns:last-name>tns:last-name</tns:last-name> </tns:completed-by>
	 * <tns:product-pack> <tns:product-line> <tns:name>tns:name</tns:name>
	 * </tns:product-line> <tns:product-item>
	 * <tns:serial-number>tns:serial-number</tns:serial-number>
	 * <tns:product-line> <tns:name>tns:name</tns:name> </tns:product-line>
	 * </tns:product-item> </tns:product-pack> </tns:completed-order-line>
	 * </tns:order> </tns:client> </tns:clients>
	 * 
	 * 
	 */

	public Client getClient() {
		return this.client;
	}

	@javax.inject.Inject
	private PersonMenu91 personMenu;
	@javax.inject.Inject
	private ClientMenu100 clientMenu;
	@javax.inject.Inject
	private ProductMenu20 productMenu;
	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private ProductLineRepository productLineRepo;
	@javax.inject.Inject
	private ProductItemRepository productItemRepo;
	@javax.inject.Inject
	private ProductPackRepository productPackRepo;
	@javax.inject.Inject
	private ProductPackRepository orderRepo;

}
