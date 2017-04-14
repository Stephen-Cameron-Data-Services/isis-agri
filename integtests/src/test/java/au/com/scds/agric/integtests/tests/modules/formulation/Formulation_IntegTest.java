package au.com.scds.agric.integtests.tests.modules.formulation;

import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import au.com.scds.agric.dom.demo.FormulationMenu40;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.FormulationStep;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
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
	FormulationMenu40 formulationMenu;
	
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
			assertThat(formulation.getName()).isEqualTo("tns:name");
			assertThat(formulation.getMethod()).isNotNull();
			FormulationMethod method = formulation.getMethod();
			assertThat(method.getName()).isEqualTo("tns:name");
			assertThat(method.getDescription()).isEqualTo("tns:description");
			for(FormulationStep step : method.getSteps()){
				assertThat(step).isNotNull();
				assertThat(step.getDescription()).isEqualTo("tns:description");
				assertThat(step.getOrder()).isEqualTo(1);
			}
			for(FormulationComponent component : formulation.getComponents()){
				Ingredient ingredient = component.getIngredient();
				assertThat(ingredient).isNotNull();
				assertThat(ingredient.getName()).isEqualTo("tns:name");
				assertThat(ingredient.getDescription()).isEqualTo("tns:description");
				for(IngredientSupply supply : ingredient.getSupplies()){
					assertThat(supply.getSupplier()).isNotNull();
					assertThat(supply.getSupplier().getName()).isEqualTo("tns:name");
					assertThat(supply.getManufacturer()).isNotNull();
					assertThat(supply.getQuantityInitial()).isEqualTo(0.0F);
					assertThat(supply.getQuantityRemaining()).isEqualTo(0.0F);
					assertThat(supply.getUnit()).isNotNull();
					assertThat(supply.getUnit().getName()).isEqualTo("tns:name");
				}
				assertThat(component.getQuantity()).isEqualTo(0.0F);
				assertThat(component.getUnit()).isNotNull();
				assertThat(component.getUnit().getName()).isEqualTo("tns:name");
			}	
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
			<tns:unit>
				<tns:name>tns:name</tns:name>
			</tns:unit>
		</tns:component>
	</tns:formulation>
</tns:formulations>

		 */
	}
}