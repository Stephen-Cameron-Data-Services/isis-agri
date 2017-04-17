package au.com.scds.agric.fixture.dom.batch;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.FormulationMenu40;
import au.com.scds.agric.dom.demo.IngredientMenu50;
import au.com.scds.agric.dom.demo.IngredientMixins;
import au.com.scds.agric.dom.demo.PersonMenu210;
import au.com.scds.agric.dom.demo.ProductMixins;
import au.com.scds.agric.dom.demo.ProductLineMenu20;
import au.com.scds.agric.dom.demo.SiUnitRepository;
import au.com.scds.agric.dom.demo.SupplierMenu220;
import au.com.scds.agric.dom.demo.SuppliesMenu60;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.Batches;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.SiUnit;
import au.com.scds.agric.dom.demo.data.Specification;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientManufacturer;
import au.com.scds.agric.dom.demo.data.IngredientSupplier;
import au.com.scds.agric.dom.demo.data.IngredientSupply;

public class BatchesCreate extends FixtureScript {

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
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Batches _batches = (Batches) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (Batch _batch : _batches.getBatch()) {
				ProductLine line = wrap(productLineMenu).createProductLine(_batch.getProductLine().getName(), null);
				Batch batch = wrap(batchMenu).createBatch(line);
				// properties
				Person _person1 = _batch.getCreatedBy();
				Person _person2 = _batch.getCompletedBy();
				Person creator = wrap(personMenu).createPerson(_person1.getFirstName(), _person1.getLastName());
				Person completor = wrap(personMenu).createPerson(_person2.getFirstName(), _person2.getLastName());
				wrap(batch).setCreatedBy(creator);
				wrap(batch).setCompletedBy(completor);
				wrap(batch).setCreatedOn(_batch.getCreatedOn());
				wrap(batch).setScheduledFor(_batch.getScheduledFor());
				wrap(batch).setCompletedOn(_batch.getCompletedOn());
				
				// specification
				Specification spec = wrap(formulationMenu).createSpecification(_batch.getSpecification().getName());
				wrap(batch).setSpecification(spec);
				IngredientMixins.Batch_addComponent bmx = mixin(IngredientMixins.Batch_addComponent.class, batch);
				// batch components
				for (BatchComponent _c : _batch.getBatchComponents()) {
					Ingredient ingredient = wrap(ingredientMenu).createIngredient(_c.getIngredient().getName(),
							_c.getIngredient().getDescription());
					IngredientSupply supply = null;
					if (!_c.getIngredient().getSupplies().isEmpty()) {
						for (IngredientSupply _s : _c.getIngredient().getSupplies()) {
							IngredientSupplier supplier = null;
							IngredientManufacturer manufacturer = null;
							if (_s.getManufacturer() != null) {
								wrap(supplierMenu).createIngredientManufacturer(_s.getManufacturer().getName());
							}
							if (_s.getSupplier() != null) {
								wrap(supplierMenu).createIngredientSupplier(_s.getSupplier().getName());
							}
							SiUnit unit = unitRepo.createSiUnit(_s.getUnit().getName());
							supply = wrap(suppliesMenu).createIngredientSupply(ingredient, supplier,
									_s.getQuantityInitial(), unit);
							wrap(supply).setQuantityRemaining(_s.getQuantityInitial());
							ingredient.getSupplies().add(supply);
						}
					}
					//add component to batch
					wrap(bmx).$$(ingredient, supply, _c.getQuantity(), _c.getUnit());
				}
				// product items
				ProductItem _productItem = _batch.getProductItems().get(0);
				ProductMixins.Batch_addProductItem bmapi = mixin(ProductMixins.Batch_addProductItem.class, batch);
				//add prodcut item to batch
				wrap(bmapi).$$(_productItem.getSerialNumber());
				ec.addResult(this, batch);
				this.batch = batch;

				/**
				 * <tns:batch> <tns:created-on>2001-12-31T12:00:00
				 * </tns:created-on>
				 * <tns:created-by> <tns:first-name>tns:first-name
				 * </tns:first-name>
				 * <tns:last-name>tns:last-name</tns:last-name>
				 * </tns:created-by>
				 * <tns:scheduled-for>2001-12-31T12:00:00</tns:scheduled-for>
				 * <tns:completed-on>2001-12-31T12:00:00</tns:completed-on>
				 * <tns:completed-by> <tns:first-name>tns:first-name
				 * </tns:first-name>
				 * <tns:last-name>tns:last-name</tns:last-name>
				 * </tns:completed-by>
				 * <tns:specification> <tns:name>tns:name</tns:name>
				 * </tns:specification>
				 * <tns:batch-component> <tns:ingredient> <tns:name>tns:name
				 * </tns:name>
				 * <tns:description>tns:description</tns:description>
				 * <tns:supply> <tns:manufacturer> <tns:name>tns:name</tns:name>
				 * </tns:manufacturer>
				 * <tns:supplier> <tns:name>tns:name</tns:name> </tns:supplier>
				 * <tns:quantity-initial>0.0</tns:quantity-initial>
				 * <tns:quantity-remaining>tns:quantity-remaining
				 * </tns:quantity-remaining> </tns:supply> </tns:ingredient>
				 * <tns:quantity>0.0</tns:quantity>
				 * <tns:unit> <tns:name>tns:name</tns:name> </tns:unit>
				 * </tns:batch-component>
				 * <tns:product-item> <tns:serial-number>tns:serial-number
				 * </tns:serial-number> </tns:product-item> </tns:batch>
				 */
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Batch getBatch() {
		return this.batch;
	}

	@javax.inject.Inject
	private ProductLineMenu20 productLineMenu;
	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private PersonMenu210 personMenu;
	@javax.inject.Inject
	private FormulationMenu40 formulationMenu;
	@javax.inject.Inject
	private IngredientMenu50 ingredientMenu;
	@javax.inject.Inject
	private SupplierMenu220 supplierMenu;
	@javax.inject.Inject
	private SuppliesMenu60 suppliesMenu;
	@javax.inject.Inject
	private SiUnitRepository unitRepo;
}
