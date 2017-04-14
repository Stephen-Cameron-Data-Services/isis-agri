package au.com.scds.agric.integtests.tests.modules.sample;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.SaleMenu100;
import au.com.scds.agric.dom.demo.SampleMixin;
import au.com.scds.agric.dom.demo.BatchSampleMenu82;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Samples;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.fixture.dom.producer.ProducerCreate;
import au.com.scds.agric.fixture.dom.sales.SalesCreate;
import au.com.scds.agric.fixture.dom.sample.SamplesCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sample_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;
	@Inject
	BatchSampleMenu82 batchSampleMenu;

	Sample sample;

	@Before
	public void setUp() throws Exception {
		// given
		SamplesCreate fs = new SamplesCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		sample = wrap(batchSampleMenu).listAll().get(0);
	}

	public static class SampleClient extends Sample_IntegTest {

		@Test
		public void accessible() throws Exception {
			assertThat(sample).isNotNull();
			assertThat(sample.getSampled()).isNotNull();
			assertThat(sample.getTests().isEmpty()).isFalse();
			assertThat(sample.getTests().size()).isEqualTo(3);
			SampleMixin sampleMixin = new SampleMixin(sample);
			assertThat(sampleMixin.getResults().isEmpty()).isFalse();
			assertThat(sampleMixin.getResults().size()).isEqualTo(4);
		}
	}
}