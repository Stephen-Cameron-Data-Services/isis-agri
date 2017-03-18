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
package au.com.scds.integtests.tests.modules.producer;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.fixture.dom.producer.ProducerCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Producer_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;
	@Inject
	ProducerMenu10 producerMenu;
	
	Producer producer;

	@Before
	public void setUp() throws Exception {
		// given
		ProducerCreate fs = new ProducerCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		producer = wrap(producerMenu).listAll().get(0);
	}

	public static class ProducerClient extends Producer_IntegTest {

		@Test
		public void accessible() throws Exception {
			assertThat(producer).isNotNull();
			assertThat(producer.getName()).isEqualTo("tns:name");
			assertThat(producer.getProductLines().size()).isEqualTo(1);
			ProductLine productLine = producer.getProductLines().get(0);
			assertThat(productLine.getName()).isEqualTo("tns:name");
			assertThat(productLine.getBatches().size()).isEqualTo(1);
			Batch batch = productLine.getBatches().get(0);
			Calendar cal = Calendar.getInstance();
			cal.set(2001,11,31,12,0,0);
			cal.set(Calendar.MILLISECOND,0);
			assertThat(batch.getCreatedOn()).isEqualTo(cal.getTime());
			assertThat(batch.getScheduledFor()).isEqualTo(cal.getTime());
			assertThat(batch.getCompletedOn()).isEqualTo(cal.getTime());
			assertThat(batch.getCreatedBy()).isNotNull();
			assertThat(batch.getCreatedBy().getFirstName()).isEqualTo("tns:first-name");
			assertThat(batch.getCreatedBy().getLastName()).isEqualTo("tns:last-name");
			assertThat(batch.getCompletedBy()).isNotNull();
			assertThat(batch.getCompletedBy().getFirstName()).isEqualTo("tns:first-name");
			assertThat(batch.getCompletedBy().getLastName()).isEqualTo("tns:last-name");
			assertThat(batch.getFormulation()).isNotNull();
			assertThat(batch.getFormulation().getName()).isEqualTo("tns:name");
			assertThat(batch.getFormulation().getDescription()).isEqualTo("tns:description");
			assertThat(batch.getFormulation().getMethod()).isNotNull();
			assertThat(batch.getFormulation().getMethod().getDescription()).isEqualTo("tns:description");
			assertThat(batch.getFormulation().getMethod().getSteps().size()).isEqualTo(1);
			assertThat(batch.getFormulation().getMethod().getSteps().get(0).getDescription()).isEqualTo("tns:description");
			assertThat(batch.getFormulation().getMethod().getSteps().get(0).getOrder()).isEqualTo(0);
			assertThat(batch.getFormulation().getComponents().size()).isEqualTo(1);
			FormulationComponent component = batch.getFormulation().getComponents().get(0);
			assertThat(component.getIngredient().getName()).isEqualTo("tns:name");
			assertThat(component.getIngredient().getDescription()).isEqualTo("tns:description");
			assertThat(component.getIngredient().getSpecification()).isEqualTo("tns:specification");
			assertThat(component.getQuantity()).isEqualTo(1);
			assertThat(component.getUnit()).isEqualTo("tns:unit");
			assertThat(batch.getProductItems().size()).isEqualTo(1);
			assertThat(batch.getProductItems().get(0).getSerialNumber()).isEqualTo("tns:serial-number");	
		}
	}
}