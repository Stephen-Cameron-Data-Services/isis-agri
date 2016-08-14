package au.com.scds.agric.fixture.dom.batches;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import com.google.common.collect.Lists;
import au.com.scds.agric.dom.demo.Batches;
import au.com.scds.agric.dom.demo.Formulation;
import au.com.scds.agric.dom.demo.Formulations;
import au.com.scds.agric.dom.demo.Ingredient;
import au.com.scds.agric.dom.demo.Manufacturers;
import au.com.scds.agric.dom.demo.Person;
import au.com.scds.agric.dom.demo.Batch;
import au.com.scds.agric.dom.demo.BatchComponent;
import au.com.scds.agric.dom.demo.Persons;
import au.com.scds.agric.dom.demo.Products;
import au.com.scds.agric.dom.demo.Sample;
import au.com.scds.agric.dom.demo.Samples;
import au.com.scds.agric.dom.demo.Suppliers;
import au.com.scds.agric.dom.demo.Supply;
import au.com.scds.agric.fixture.xml.generated.BatchComponentFixture;
import au.com.scds.agric.fixture.xml.generated.BatchFixture;
import au.com.scds.agric.fixture.xml.generated.BatchesFixture;
import au.com.scds.agric.fixture.xml.generated.IngredientFixture;
import au.com.scds.agric.fixture.xml.generated.ObjectFactory;
import au.com.scds.agric.fixture.xml.generated.SampleFixture;

public class RecreateBatches extends FixtureScript {

	public RecreateBatches() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private final List<Batch> batches = Lists.newArrayList();

	@Override
	protected void execute(ExecutionContext ec) {

		// domain repositories
		Products productsRepo = new Products();
		Batches batchesRepo = new Batches();
		Formulations formulationsRepo = new Formulations();
		//Samples samplesRepo = new Samples();
		Persons personsRepo = new Persons();
		Manufacturers manufacturersRepo = new Manufacturers();
		Suppliers suppliersRepo = new Suppliers();

		try {
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/batches.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BatchesFixture BatchsFixture = ((JAXBElement<BatchesFixture>) jaxbUnmarshaller.unmarshal(is)).getValue();
			for (BatchFixture f : BatchsFixture.getBatch()) {
				Batch batch = wrap(batchesRepo).createBatch();
				wrap(batch).setCreatedOn(f.getCreatedOn().toGregorianCalendar().getTime());
				Person person = wrap(personsRepo).createPerson(f.getCreatedBy().getFirstname(),f.getCreatedBy().getLastname());
				wrap(person).setFirstName(f.getCreatedBy().getFirstname());
				wrap(person).setLastName(f.getCreatedBy().getLastname());
				wrap(batch).setCreatedBy(person);
				wrap(batch).setScheduledFor(f.getScheduledFor().toGregorianCalendar().getTime());
				wrap(batch).setCompletedOn(f.getCompletedOn().toGregorianCalendar().getTime());
				Person person2 = wrap(personsRepo).createPerson(f.getCompletedBy().getFirstname(),f.getCompletedBy().getLastname());
				wrap(batch).setCompletedBy(person2);
				Formulation formulation = wrap(formulationsRepo).createFormulation(f.getFormulation().getName());
				wrap(formulation).setDescription(f.getFormulation().getDescription());
				wrap(batch).setFormulation(formulation);
				for (IngredientFixture i : f.getFormulation().getIngredient()) {
					Ingredient ingredient = wrap(formulationsRepo).createIngredient(i.getName());
					wrap(formulation).addIngredient(ingredient,i.getQuantity(),i.getUnit());
				}
				for (BatchComponentFixture componentFixture : f.getComponent()) {
					BatchComponent component = wrap(batchesRepo).createComponent();
					wrap(component).setIngredient(wrap(formulationsRepo).createIngredient(componentFixture.getIngredient().getName()));
					Supply supply = wrap(suppliersRepo).createSupply(wrap(component).getIngredient(),wrap(suppliersRepo).createSupplier(componentFixture.getSupplier().getName()));
					wrap(component).setSupply(supply);
					wrap(batch).addComponent(component);
				}
				for (SampleFixture s : f.getSample()) {
					//Sample sample = wrap(samplesRepo).createSample(batch);
					wrap(batch).createSample();
				}
				batches.add(batch);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<Batch> getBatches() {
		return batches;
	}

}
