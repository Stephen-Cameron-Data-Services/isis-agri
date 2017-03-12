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

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.fixture.dom.producer.ProducerCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.GregorianCalendar;

public class Producer_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;

	Producer producer;

	@Before
	public void setUp() throws Exception {
		// given
		ProducerCreate fs = new ProducerCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		producer = fs.getProducer();
	}

	public static class ProducerClient extends Producer_IntegTest {

		@Test
		public void accessible() throws Exception {
			assertThat(producer).isNotNull();
			//assertThat(batch.getCreatedOn()).isEqualTo(new GregorianCalendar(2016, 6, 20, 10, 0, 0).getTime());
			//assertThat(batch.getScheduledFor()).isEqualTo(new GregorianCalendar(2016, 6, 25, 12, 0, 0).getTime());
			//assertThat(batch.getCompletedOn()).isEqualTo(new GregorianCalendar(2016, 6, 26, 9, 0, 0).getTime());
			//assertThat(batch.getCreatedBy()).isNotNull();
			//assertThat(batch.getCreatedBy().getFirstName()).isEqualTo("Deborah");
		}
	}
}