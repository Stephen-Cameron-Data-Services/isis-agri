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
import au.com.scds.agric.dom.demo.FormulationMenu40;
import au.com.scds.agric.dom.demo.FormulationMethodMixin;
import au.com.scds.agric.dom.demo.FormulationMixin;
import au.com.scds.agric.dom.demo.IngredientMenu50;
import au.com.scds.agric.dom.demo.PersonMenu210;
import au.com.scds.agric.dom.demo.ProductLineMenu20;
import au.com.scds.agric.dom.demo.SiUnitRepository;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.FormulationStep;
import au.com.scds.agric.dom.demo.data.Formulations;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.SiUnit;
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

			for (Formulation _f : _formulations.getFormulation()) {
				formulation = wrap(formulationMenu).createFormulation(_f.getName());
				formulation.setDescription(_f.getDescription());
				FormulationMethod _m = _f.getMethod();
				FormulationMethod formulationMethod = wrap(formulationMenu).createFormulationMethod(_m.getName());
				formulationMethod.setDescription(_m.getDescription());
				formulation.setMethod(formulationMethod);
				FormulationMethodMixin fmx = new FormulationMethodMixin(formulationMethod);
				for (FormulationStep _s : _m.getSteps()) {
					wrap(fmx).addStep(_s.getDescription(), _s.getOrder());
				}
				FormulationMixin mx = new FormulationMixin(formulation);
				for (FormulationComponent _c : _f.getComponents()) {
					Ingredient _i = _c.getIngredient();
					Ingredient ingredient = wrap(ingredientMenu).createIngredient(_i.getName(), _i.getDescription());
					// ingredient supply covered in batches fixture
					SiUnit u = wrap(unitRepo).createSiUnit(_c.getUnit().getName());
					wrap(mx).addComponentIngredient(ingredient, _c.getQuantity(), u);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Formulation getFormulation() {
		return this.formulation;
	}

	@javax.inject.Inject
	private FormulationMenu40 formulationMenu;
	@javax.inject.Inject
	private ProductLineMenu20 productMenu;
	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private IngredientMenu50 ingredientMenu;
	@javax.inject.Inject
	private PersonMenu210 personMenu;
	@javax.inject.Inject
	private SiUnitRepository unitRepo;
}
