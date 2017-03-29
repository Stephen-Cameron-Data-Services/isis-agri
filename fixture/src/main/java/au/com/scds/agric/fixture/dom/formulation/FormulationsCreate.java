package au.com.scds.agric.fixture.dom.formulation;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.FormulationMenu80;
import au.com.scds.agric.dom.demo.FormulationMethodMixin;
import au.com.scds.agric.dom.demo.FormulationMixin;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.ProductMenu20;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.Formulations;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Formulation;

public class FormulationsCreate extends FixtureScript {

	public FormulationsCreate() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Formulation formulation = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/formulations.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Formulations _formulations = (Formulations) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));

			for (Formulation _formulation : _formulations.getFormulation()) {
				Formulation formulation = wrap(formulationMenu).createFormulation(_formulation.getName());
			}

			/*
			 * wrap(formulation).setDescription(_formulation.getDescription());
			 * FormulationMethod _formulationMethod = _formulation.getMethod();
			 * FormulationMixin fmx = new FormulationMixin(formulation);
			 * FormulationMethod formulationMethod = wrap(fmx).createMethod();
			 * wrap(formulationMethod).setDescription(_formulationMethod.
			 * getDescription()); FormulationComponent fmc =
			 * _formulation.getComponents().get(0); Ingredient ingredient =
			 * wrap(fmx).createComponentIngredient(fmc.getIngredient().getName()
			 * , fmc.getIngredient().getDescription(), fmc.getQuantity(),
			 * fmc.getUnit());
			 * wrap(ingredient).setSpecification(fmc.getIngredient().
			 * getSpecification()); FormulationMethodMixin fmmx = new
			 * FormulationMethodMixin(formulationMethod);
			 * wrap(fmmx).addStep(_formulationMethod.getSteps().get(0).
			 * getDescription(),
			 * _formulationMethod.getSteps().get(0).getOrder());
			 */
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 *
	 *
	 * <tns:formulations xmlns:tns="http://www.example.org/AgricProducerSchema"
	 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation=
	 * "http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd "
	 * > <tns:formulation> <tns:name>tns:name</tns:name>
	 * <tns:description>tns:description</tns:description>
	 * <tns:method> <tns:description>tns:description</tns:description>
	 * <tns:step> <tns:description>tns:description</tns:description>
	 * <tns:order>0</tns:order> </tns:step> </tns:method>
	 * <tns:component> <tns:ingredient> <tns:name>tns:name</tns:name>
	 * <tns:description>tns:description</tns:description>
	 * <tns:supply> <tns:manufacturer> <tns:name>tns:name</tns:name>
	 * </tns:manufacturer>
	 * <tns:supplier> <tns:name>tns:name</tns:name> </tns:supplier>
	 * <tns:quantity-initial>0.0</tns:quantity-initial>
	 * <tns:quantity-remaining>0.0</tns:quantity-remaining>
	 * <tns:unit> <tns:name>tns:name</tns:name> </tns:unit> </tns:supply>
	 * </tns:ingredient> <tns:quantity>0.0</tns:quantity>
	 * <tns:unit>tns:unit</tns:unit> </tns:component> </tns:formulation>
	 * </tns:formulations>
	 * 
	 */

	public Formulation getFormulation() {
		return this.formulation;
	}

	@javax.inject.Inject
	private FormulationMenu80 formulationMenu;
	@javax.inject.Inject
	private ProductMenu20 productMenu;
	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private PersonMenu91 personMenu;
}
