package au.com.scds.agric.integtests.tests.modules.formulation;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.FormulationMenu80;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.fixture.dom.formulation.FormulationsCreate;
import au.com.scds.agric.integtests.tests.DomainAppIntegTest;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;
import static org.assertj.core.api.Assertions.assertThat;


public class Formulation_IntegTest extends DomainAppIntegTest {

	@Inject
	FixtureScripts fixtureScripts;
	@Inject
	TransactionService transactionService;
	@Inject
	FormulationMenu80 formulationMenu;
	
	Formulation formulation;

	@Before
	public void setUp() throws Exception {
		// given
		FormulationsCreate fs = new FormulationsCreate();
		fixtureScripts.runFixtureScript(fs, null);
		transactionService.nextTransaction();
		formulation = wrap(formulationMenu).listAllFormulations().get(0);
	}

	public static class formulationClient extends Formulation_IntegTest {

		@Test
		public void accessible() throws Exception {
			assertThat(formulation).isNotNull();
			//assertThat(batch.getFormulation().getName()).isEqualTo("tns:name");
			//assertThat(batch.getFormulation().getDescription()).isEqualTo("tns:description");
			//assertThat(batch.getFormulation().getMethod()).isNotNull();
			//assertThat(batch.getFormulation().getMethod().getDescription()).isEqualTo("tns:description");
			//assertThat(batch.getFormulation().getMethod().getSteps().size()).isEqualTo(1);
			//assertThat(batch.getFormulation().getMethod().getSteps().get(0).getDescription()).isEqualTo("tns:description");
			//assertThat(batch.getFormulation().getMethod().getSteps().get(0).getOrder()).isEqualTo(0);
			//assertThat(batch.getFormulation().getComponents().size()).isEqualTo(1);	
		}
		
		/*
<?xml version="1.0" encoding="UTF-8"?>
<tns:formulations xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
<tns:formulation>
<tns:name>tns:name</tns:name>
<tns:description>tns:description</tns:description>
<tns:method>
  <tns:name>tns:name</tns:name>
  <tns:description>tns:description</tns:description>
  <tns:step>
    <tns:description>tns:description</tns:description>
    <tns:order>0</tns:order>
  </tns:step>
</tns:method>
<tns:component>
  <tns:ingredient>
    <tns:name>tns:name</tns:name>
    <tns:description>tns:description</tns:description>
    <tns:supply>
      <tns:manufacturer>
        <tns:name>tns:name</tns:name>
      </tns:manufacturer>
      <tns:supplier>
        <tns:name>tns:name</tns:name>
      </tns:supplier>
      <tns:quantity-initial>0.0</tns:quantity-initial>
      <tns:quantity-remaining>0.0</tns:quantity-remaining>
      <tns:unit>
        <tns:name>tns:name</tns:name>
      </tns:unit>
    </tns:supply>
  </tns:ingredient>
  <tns:quantity>0.0</tns:quantity>
  <tns:unit>tns:unit</tns:unit>
</tns:component>
</tns:formulation>
</tns:formulations>
		 */
	}
}