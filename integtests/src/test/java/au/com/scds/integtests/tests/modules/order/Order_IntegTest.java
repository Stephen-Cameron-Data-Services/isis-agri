/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package au.com.scds.integtests.tests.modules.order;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.Client;
import au.com.scds.agric.dom.demo.NewOrderLine;
import au.com.scds.agric.dom.demo.Order;
import au.com.scds.agric.dom.simple.SimpleObject;
import au.com.scds.agric.fixture.dom.orders.RecreateOrders;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.applib.services.wrapper.DisabledException;
import org.apache.isis.applib.services.wrapper.InvalidException;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.core.metamodel.services.jdosupport.Persistable_datanucleusIdLong;
import org.apache.isis.core.metamodel.services.jdosupport.Persistable_datanucleusVersionTimestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class Order_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;

	Order order;

	@Before
	public void setUp() throws Exception {
		// given
		RecreateOrders fs = new RecreateOrders();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();

		order = fs.getOrders().get(0);

	}

	public static class OrderClient extends Order_IntegTest {

		@Test
		public void accessible() throws Exception {
			assertThat(order).isNotNull();
			assertThat(order.getClient()).isNotNull();
			assertThat(order.getClient().getName()).isEqualTo("Woolworths");
			assertThat(order.getTaken()).isNotNull();
			assertThat(order.getTaken()).isEqualTo(new GregorianCalendar(2016,5,22,12,0,0).getTime());
			assertThat(order.getTakenBy()).isNotNull();
			assertThat(order.getTakenBy().getFirstName()).isEqualTo("Sam");
			assertThat(order.getTakenBy().getLastName()).isEqualTo("Smith");
			assertThat(order. getNewOrderLines().isEmpty()).isEqualTo(false);
			assertThat(order.getNewOrderLines().get(0)).isNotNull();
			NewOrderLine orderLine = order.getNewOrderLines().get(0);
			assertThat(orderLine.getProductLine()).isNotNull();
			assertThat(orderLine.getProductLine().getName()).isEqualTo("Whole Chicken");
			assertThat(orderLine.getProductLine().getProductType().getName()).isEqualTo("Chicken Meat");
			assertThat(orderLine.getQuantity()).isEqualTo(100);			
			assertThat(orderLine.getAddedOn()).isEqualTo(new GregorianCalendar(2016,5,23,12,0,0).getTime());	
			assertThat(orderLine.getAddedBy()).isNotNull();
			assertThat(orderLine.getAddedBy().getFirstName()).isEqualTo("John");
			assertThat(orderLine.getAddedBy().getLastName()).isEqualTo("Brown");		

			
			/*			<tns:order>
			    <tns:id>1</tns:id>
			    <tns:client>
			      <tns:name>Woolworths</tns:name>
			    </tns:client>
			    <tns:taken>2001-12-31T12:00:00</tns:taken>
			    <tns:taken-by>
			      <tns:id>1</tns:id>
			      <tns:firstname>Sam</tns:firstname>
			      <tns:lastname>Smith</tns:lastname>
			    </tns:taken-by>
			    <tns:order-line>
			      <tns:id>1</tns:id>
			      <tns:product-line>
			        <tns:name>Whole Chicken</tns:name>
			        <tns:product-type>
			          <tns:name>Chicken Meat</tns:name>
			        </tns:product-type>
			      </tns:product-line>
			      <tns:quantity>100</tns:quantity>
			      <tns:added-on>2001-12-31T12:00:00</tns:added-on>
			      <tns:added-by>
			        <tns:id>2</tns:id>
			        <tns:firstname>John</tns:firstname>
			        <tns:lastname>Brown</tns:lastname>
			      </tns:added-by>
			    </tns:order-line>
			  </tns:order>
		*/
		}
	}
}