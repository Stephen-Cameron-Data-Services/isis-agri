package au.com.scds.agric.integtests.tests.modules.sale;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.SaleMenu100;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.fixture.dom.producer.ProducerCreate;
import au.com.scds.agric.fixture.dom.sales.SalesCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sale_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;
	@Inject
	SaleMenu100 saleMenu;
	
	Sale Sale;

	@Before
	public void setUp() throws Exception {
		// given
		SalesCreate fs = new SalesCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		Sale = wrap(saleMenu).listAllSales().get(0);
	}

	public static class SaleClient extends Sale_IntegTest {

		@Test
		public void accessible() throws Exception {

			Calendar cal = Calendar.getInstance();
			cal.set(2001,11,31,12,0,0);
			cal.set(Calendar.MILLISECOND,0);	
		}
	}
}