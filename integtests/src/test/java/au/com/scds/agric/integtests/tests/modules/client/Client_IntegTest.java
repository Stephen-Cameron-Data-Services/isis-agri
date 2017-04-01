
package au.com.scds.agric.integtests.tests.modules.client;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.ClientMenu100;
import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.CompletedOrderLine;
import au.com.scds.agric.dom.demo.data.Order;
import au.com.scds.agric.dom.demo.data.OrderLine;
import au.com.scds.agric.dom.demo.data.OrderLineBatch;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductPack;
import au.com.scds.agric.dom.demo.data.ScheduledOrderLine;
import au.com.scds.agric.fixture.dom.client.ClientsCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

public class Client_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;
	@Inject
	ClientMenu100 clientMenu;
	
	Client client;

	@Before
	public void setUp() throws Exception {
		// given
		ClientsCreate fs = new ClientsCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		client = wrap(clientMenu).listAll().get(0);
	}

	public static class ClientClient extends Client_IntegTest {

		@Test
		public void accessible() throws Exception {
			
			assertThat(client).isNotNull();
			assertThat(client.getOrders().isEmpty()).isFalse();
			assertThat(client.getName()).isEqualTo("tns:name");
			Order order = client.getOrders().get(0);
			
			assertThat(order).isNotNull();
			assertThat(order.getTaken()).isNotNull();
			assertThat(order.getTaken()).isEqualTo("2001-12-31T12:00:01");
			assertThat(order.getTakenBy()).isNotNull();
			assertThat(order.getTakenBy().getFirstName()).isEqualTo("tns:first-name1");
			assertThat(order.getTakenBy().getLastName()).isEqualTo("tns:last-name1");
			
			assertThat(order.getOrderLines().isEmpty()).isFalse();
			assertThat(order.getOrderLines().size()).isEqualTo(1);
			OrderLine line = order.getOrderLines().get(0);
			assertThat(line.getProductLine()).isNotNull();
			assertThat(line.getProductLine().getName()).isEqualTo("tns:name2");
			assertThat(line.getProductLine().getProductType()).isNotNull();
			assertThat(line.getProductLine().getProductType().getName()).isEqualTo("tns:name2");
			assertThat(line.getAddedOn()).isNotNull();
			assertThat(line.getAddedOn()).isEqualTo("2001-12-31T12:00:02");
			assertThat(line.getAddedBy()).isNotNull();
			assertThat(line.getAddedBy().getFirstName()).isEqualTo("tns:first-name2");
			assertThat(line.getAddedBy().getLastName()).isEqualTo("tns:last-name2");
			
			assertThat(order.getScheduledOrderLines().isEmpty()).isFalse();
			assertThat(order.getScheduledOrderLines().size()).isEqualTo(1);
			ScheduledOrderLine line2 = order.getScheduledOrderLines().get(0);
			assertThat(line2.getProductLine()).isNotNull();
			assertThat(line2.getProductLine().getName()).isEqualTo("tns:name3");
			assertThat(line2.getProductLine().getProductType()).isNotNull();
			assertThat(line2.getProductLine().getProductType().getName()).isEqualTo("tns:name3");
			assertThat(line2.getAddedOn()).isNotNull();
			assertThat(line2.getAddedOn()).isEqualTo("2001-12-31T12:00:03");
			assertThat(line2.getAddedBy()).isNotNull();
			assertThat(line2.getAddedBy().getFirstName()).isEqualTo("tns:first-name3");
			assertThat(line2.getAddedBy().getLastName()).isEqualTo("tns:last-name3");
			assertThat(line2.getScheduledOn()).isNotNull();
			assertThat(line2.getScheduledOn()).isEqualTo("2001-12-31T12:00:03");
			assertThat(line2.getScheduledOn()).isNotNull();
			assertThat(line2.getScheduledBy().getFirstName()).isEqualTo("tns:first-name3");
			assertThat(line2.getScheduledBy().getLastName()).isEqualTo("tns:last-name3");			
			assertThat(line2.getScheduledBatchPortions().isEmpty()).isFalse();
			OrderLineBatch lineBatch = line2.getScheduledBatchPortions().get(0);
			assertThat(lineBatch).isNotNull();
			assertThat(lineBatch.getBatch()).isNotNull();
			assertThat(lineBatch.getPercentageOfBatch()).isEqualTo(100.0F);
			//check other side of relationship M-N
			assertThat(lineBatch.getBatch().getBatchOrderLines().isEmpty()).isFalse();
			assertThat(lineBatch.getBatch().getBatchOrderLines().get(0).getScheduledOrderLine()).isEqualTo(line2);
			
			assertThat(order.getCompletedOrderLines().isEmpty()).isFalse();
			assertThat(order.getCompletedOrderLines().size()).isEqualTo(1);
			CompletedOrderLine line3 = order.getCompletedOrderLines().get(0);
			assertThat(line3.getProductLine()).isNotNull();
			assertThat(line3.getProductLine().getName()).isEqualTo("tns:name4");
			assertThat(line3.getProductLine().getProductType()).isNotNull();
			assertThat(line3.getProductLine().getProductType().getName()).isEqualTo("tns:name4");
			assertThat(line3.getAddedOn()).isNotNull();
			assertThat(line3.getAddedOn()).isEqualTo("2001-12-31T12:00:04");
			assertThat(line3.getAddedBy()).isNotNull();
			assertThat(line3.getAddedBy().getFirstName()).isEqualTo("tns:first-name4");
			assertThat(line3.getAddedBy().getLastName()).isEqualTo("tns:last-name4");
			assertThat(line3.getCompletedOn()).isNotNull();
			assertThat(line3.getCompletedOn()).isEqualTo("2001-12-31T12:00:04");
			assertThat(line3.getCompletedOn()).isNotNull();
			assertThat(line3.getCompletedBy().getFirstName()).isEqualTo("tns:first-name4");
			assertThat(line3.getCompletedBy().getLastName()).isEqualTo("tns:last-name4");
			assertThat(line3.getProductPacks().isEmpty()).isFalse();
			ProductPack pack = line3.getProductPacks().get(0);
			assertThat(pack).isNotNull();
			assertThat(pack.getProductLine()).isNotNull();
			assertThat(pack.getProductItems().isEmpty()).isFalse();
			ProductItem item = pack.getProductItems().get(0);
			assertThat(item).isNotNull();
			assertThat(item.getSerialNumber()).isEqualTo("tns:serial-number4");
		}
	}
	/*
	<?xml version="1.0" encoding="UTF-8"?>
	<tns:clients xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
	  <tns:client>
	    <tns:name>tns:name</tns:name>
	    <tns:order>
	      <tns:taken>2001-12-31T12:00:00</tns:taken>
	      <tns:taken-by>
	        <tns:first-name>tns:first-name</tns:first-name>
	        <tns:last-name>tns:last-name</tns:last-name>
	      </tns:taken-by>
	      <tns:order-line>
	        <tns:product-line>
	          <tns:name>tns:name</tns:name>
	          <tns:product-type>
	            <tns:name>tns:name</tns:name>
	          </tns:product-type>
	        </tns:product-line>
	        <tns:added-on>2001-12-31T12:00:00</tns:added-on>
	        <tns:added-by>
	          <tns:first-name>tns:first-name</tns:first-name>
	          <tns:last-name>tns:last-name</tns:last-name>
	        </tns:added-by>
	      </tns:order-line>
	      <tns:scheduled-order-line>
	        <tns:order-line>
	          <tns:product-line>
	          <tns:name>tns:name</tns:name>
	            <tns:product-type>
	              <tns:name>tns:name</tns:name>
	            </tns:product-type>
	          </tns:product-line>
	          <tns:added-on>2001-12-31T12:00:00</tns:added-on>
	          <tns:added-by>
	            <tns:first-name>tns:first-name</tns:first-name>
	            <tns:last-name>tns:last-name</tns:last-name>
	          </tns:added-by>
	        </tns:order-line>
	        <tns:scheduled-on>2001-12-31T12:00:00</tns:scheduled-on>
	        <tns:scheduled-by>
	          <tns:first-name>tns:first-name</tns:first-name>
	          <tns:last-name>tns:last-name</tns:last-name>
	        </tns:scheduled-by>
	        <tns:orderline-batch-portion>
	          <tns:batch>
	            <tns:created-on>2001-12-31T12:00:00</tns:created-on>
	            <tns:created-by>
	              <tns:first-name>tns:first-name</tns:first-name>
	              <tns:last-name>tns:last-name</tns:last-name>
	            </tns:created-by>
	          </tns:batch>
	          <tns:percentage-of-batch>0.0</tns:percentage-of-batch>
	        </tns:orderline-batch-portion>
	      </tns:scheduled-order-line>
	      <tns:completed-order-line>
	        <tns:order-line>tns:order-line</tns:order-line>
	        <tns:completed>2001-12-31T12:00:00</tns:completed>
	        <tns:completed-by>
	          <tns:first-name>tns:first-name</tns:first-name>
	          <tns:last-name>tns:last-name</tns:last-name>
	        </tns:completed-by>
	        <tns:product-pack>
	          <tns:product-line>
	            <tns:name>tns:name</tns:name>
	          </tns:product-line>
	          <tns:product-item>
	            <tns:serial-number>tns:serial-number</tns:serial-number>
	            <tns:product-line>
	              <tns:name>tns:name</tns:name>
	            </tns:product-line>
	          </tns:product-item>
	        </tns:product-pack>
	      </tns:completed-order-line>
	    </tns:order>
	  </tns:client>
	</tns:clients>
	*/
}