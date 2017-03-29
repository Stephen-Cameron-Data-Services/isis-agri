package au.com.scds.agric.integtests.tests.modules.client;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.ClientMenu100;
import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.fixture.dom.client.ClientsCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
		Client _client = wrap(clientMenu).listAll().get(0);
	}

	public static class ClientClient extends Client_IntegTest {

		@Test
		public void accessible() throws Exception {

			Calendar cal = Calendar.getInstance();
			cal.set(2001,11,31,12,0,0);
			cal.set(Calendar.MILLISECOND,0);	
		}
	}
}