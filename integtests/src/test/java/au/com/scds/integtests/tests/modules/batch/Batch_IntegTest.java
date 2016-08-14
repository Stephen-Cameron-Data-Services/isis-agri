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
package au.com.scds.integtests.tests.modules.batch;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import au.com.scds.agric.dom.demo.Batch;
import au.com.scds.agric.dom.demo.Ingredient;
import au.com.scds.agric.fixture.dom.batches.RecreateBatches;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.GregorianCalendar;

public class Batch_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;

	Batch batch;

	@Before
	public void setUp() throws Exception {
		// given
		RecreateBatches fs = new RecreateBatches();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		batch = fs.getBatches().get(0);
	}

	public static class BatchClient extends Batch_IntegTest {

		@Test
		public void accessible() throws Exception {
			assertThat(batch).isNotNull();
			assertThat(batch.getCreatedOn()).isEqualTo(new GregorianCalendar(2016, 6, 20, 10, 0, 0).getTime());
			assertThat(batch.getScheduledFor()).isEqualTo(new GregorianCalendar(2016, 6, 25, 12, 0, 0).getTime());
			assertThat(batch.getCompletedOn()).isEqualTo(new GregorianCalendar(2016, 6, 26, 9, 0, 0).getTime());
			assertThat(batch.getCreatedBy()).isNotNull();
			assertThat(batch.getCreatedBy().getFirstName()).isEqualTo("Deborah");
			assertThat(batch.getCreatedBy().getLastName()).isEqualTo("White");
			assertThat(batch.getCompletedBy()).isNotNull();
			assertThat(batch.getCompletedBy().getFirstName()).isEqualTo("John");
			assertThat(batch.getCompletedBy().getLastName()).isEqualTo("Frost");
			assertThat(batch.getFormulation()).isNotNull();
			assertThat(batch.getFormulation().getName()).isEqualTo("Double Cream Brie 2");
			assertThat(batch.getFormulation().getDescription()).isEqualTo("An improvement on the last formulation.");
			assertThat(batch.getFormulation().getComponents().get(0).getIngredient().getName()).isEqualTo("Full Cream Milk");
			assertThat(batch.getFormulation().getComponents().get(0).getQuantity()).isEqualTo(1000);
			assertThat(batch.getFormulation().getComponents().get(0).getUnit()).isEqualTo("litres");
			assertThat(batch.getFormulation().getComponents().get(1).getIngredient().getName()).isEqualTo("Cream");
			assertThat(batch.getFormulation().getComponents().get(1).getQuantity()).isEqualTo(100);
			assertThat(batch.getFormulation().getComponents().get(1).getUnit()).isEqualTo("litres");
			assertThat(batch.getFormulation().getComponents().get(2).getIngredient().getName()).isEqualTo("Rennet");
			assertThat(batch.getFormulation().getComponents().get(2).getQuantity()).isEqualTo(100);
			assertThat(batch.getFormulation().getComponents().get(2).getUnit()).isEqualTo("grams");
			assertThat(batch.getFormulation().getComponents().get(3).getIngredient().getName()).isEqualTo("Salt");
			assertThat(batch.getFormulation().getComponents().get(3).getQuantity()).isEqualTo(10);
			assertThat(batch.getFormulation().getComponents().get(3).getUnit()).isEqualTo("kilograms");
			assertThat(batch.getFormulation().getComponents().get(4).getIngredient().getName()).isEqualTo("Penicillium camemberti culture");
			assertThat(batch.getFormulation().getComponents().get(4).getQuantity()).isEqualTo(100);
			assertThat(batch.getFormulation().getComponents().get(4).getUnit()).isEqualTo("grams");
			assertThat(batch.getComponents().get(0).getIngredient().getName()).isEqualTo("Full Cream Milk");
			assertThat(batch.getComponents().get(0).getSupply().getSupplier().getName()).isEqualTo("Van Diemen's Land Company");
			assertThat(batch.getComponents().get(1).getIngredient().getName()).isEqualTo("Cream");
			assertThat(batch.getComponents().get(1).getSupply().getSupplier().getName()).isEqualTo("Van Diemen's Land Company");
			assertThat(batch.getSamples().get(0)).isNotNull();
		}
	}
}