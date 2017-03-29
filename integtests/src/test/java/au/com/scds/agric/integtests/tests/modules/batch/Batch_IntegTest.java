package au.com.scds.agric.integtests.tests.modules.batch;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.fixture.dom.batch.BatchesCreate;
import au.com.scds.agric.fixture.dom.producer.ProducerCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Batch_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;
	@Inject
	BatchMenu30 batchMenu;
	
	Batch batch;

	@Before
	public void setUp() throws Exception {
		// given
		BatchesCreate fs = new BatchesCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		batch = wrap(batchMenu).listAll().get(0);
	}

	public static class BatchClient extends Batch_IntegTest {

		@Test
		public void accessible() throws Exception {

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
			assertThat(batch.getProductItems().size()).isEqualTo(1);
			assertThat(batch.getProductItems().get(0).getSerialNumber()).isEqualTo("tns:serial-number");	
		}
	}
}