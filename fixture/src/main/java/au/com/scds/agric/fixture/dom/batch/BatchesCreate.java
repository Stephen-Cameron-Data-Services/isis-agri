package au.com.scds.agric.fixture.dom.batch;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.BatchMixin;
import au.com.scds.agric.dom.demo.FormulationMenu80;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.Batches;
import au.com.scds.agric.dom.demo.data.ProductItem;

public class BatchesCreate extends FixtureScript{

	public BatchesCreate() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Batch batch = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/batches.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Batches _batches = ((JAXBElement<Batches>) jaxbUnmarshaller.unmarshal(is)).getValue();
			for(Batch _batch : _batches.getBatch() )
			{
				Batch batch = wrap(batchMenu).createBatch();
				Person _person1 = _batch.getCreatedBy();
				Person _person2 = _batch.getCompletedBy();
				Person creator = wrap(personMenu).create(_person1.getFirstName(), _person1.getLastName());
				Person completor = wrap(personMenu).create(_person2.getFirstName(), _person2.getLastName());
				wrap(batch).setCreatedBy(creator);
				wrap(batch).setCompletedBy(completor);
				wrap(batch).setCreatedOn(_batch.getCreatedOn());
				wrap(batch).setScheduledFor(_batch.getScheduledFor());
				wrap(batch).setCompletedOn(_batch.getCompletedOn());
				ProductItem _productItem = batch.getProductItems().get(0);
				BatchMixin bmx = new BatchMixin(batch);
				wrap(bmx).createProductItem(_productItem.getSerialNumber());
				ec.addResult(this, batch);
				this.batch = batch;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Batch getBatch() {
		return this.batch;
	}

	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private PersonMenu91 personMenu;
	@javax.inject.Inject
	private FormulationMenu80 formulationMenu;
}
