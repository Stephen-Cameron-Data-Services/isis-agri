package au.com.scds.agric.fixture.dom.client;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import au.com.scds.agric.dom.demo.ClientMenu100;
import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.Clients;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Order;

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
			Clients _clients = (Clients) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));

			for(Client _c : _clients.getClient()){
				Client client = wrap(clientMenu).createClient(_c.getName());
				if(!_c.getOrders().isEmpty()){
					Order _o = _c.getOrders().get(0);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * <?xml version="1.0" encoding="UTF-8"?>
<tns:clients xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
  <tns:client>
    <tns:name>tns:name</tns:name>
    <tns:order>
      <tns:taken>2001-12-31T12:00:00</tns:taken>
      <tns:taken-by>
        <tns:first-name>tns:first-name</tns:first-name>
        <tns:last-name>tns:last-name</tns:last-name>
      </tns:taken-by>
      <tns:order-line xsi:type="tns:NewOrderLine">
        <tns:product-pack>
          <tns:product-item>
            <tns:serial-number>tns:serial-number</tns:serial-number>
          </tns:product-item>
          <tns:delivery>
            <tns:date>2001-12-31T12:00:00</tns:date>
          </tns:delivery>
        </tns:product-pack>
        <tns:added-on>2001-12-31T12:00:00</tns:added-on>
        <tns:added-by>
          <tns:first-name>tns:first-name</tns:first-name>
          <tns:last-name>tns:last-name</tns:last-name>
        </tns:added-by>
      </tns:order-line>
      <tns:scheduled-order-line xsi:type="tns:ScheduledOrderLine">
        <tns:product-pack>
          <tns:product-item>
            <tns:serial-number>tns:serial-number</tns:serial-number>
          </tns:product-item>
          <tns:delivery>
            <tns:date>2001-12-31T12:00:00</tns:date>
          </tns:delivery>
        </tns:product-pack>
        <tns:added-on>2001-12-31T12:00:00</tns:added-on>
        <tns:added-by>
          <tns:first-name>tns:first-name</tns:first-name>
          <tns:last-name>tns:last-name</tns:last-name>
        </tns:added-by>
        <tns:scheduled-on>2001-12-31T12:00:00</tns:scheduled-on>
        <tns:scheduled-by>
          <tns:first-name>tns:first-name</tns:first-name>
          <tns:last-name>tns:last-name</tns:last-name>
        </tns:scheduled-by>
        <tns:scheduled-batch>tns:scheduled-batch</tns:scheduled-batch>
      </tns:scheduled-order-line>
      <tns:completed-order-line>
        <tns:product-pack>
          <tns:product-item>
            <tns:serial-number>tns:serial-number</tns:serial-number>
          </tns:product-item>
          <tns:delivery>
            <tns:date>2001-12-31T12:00:00</tns:date>
          </tns:delivery>
        </tns:product-pack>
        <tns:added-on>2001-12-31T12:00:00</tns:added-on>
        <tns:added-by>
          <tns:first-name>tns:first-name</tns:first-name>
          <tns:last-name>tns:last-name</tns:last-name>
        </tns:added-by>
        <tns:scheduled-on>2001-12-31T12:00:00</tns:scheduled-on>
        <tns:scheduled-by>
          <tns:first-name>tns:first-name</tns:first-name>
          <tns:last-name>tns:last-name</tns:last-name>
        </tns:scheduled-by>
        <tns:scheduled-batch>tns:scheduled-batch</tns:scheduled-batch>
        <tns:completed>2001-12-31T12:00:00</tns:completed>
        <tns:completed-by>
          <tns:first-name>tns:first-name</tns:first-name>
          <tns:last-name>tns:last-name</tns:last-name>
        </tns:completed-by>
      </tns:completed-order-line>
    </tns:order>
  </tns:client>
</tns:clients>

	 */

	public Client getClient() {
		return this.client;
	}

	@javax.inject.Inject
	private ClientMenu100 clientMenu;
}
