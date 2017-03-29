package au.com.scds.agric.fixture.dom.sales;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.BatchMixin;
import au.com.scds.agric.dom.demo.FormulationMenu80;
import au.com.scds.agric.dom.demo.FormulationMethodMixin;
import au.com.scds.agric.dom.demo.FormulationMixin;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.ProducerMixin;
import au.com.scds.agric.dom.demo.ProductMenu20;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.Formulations;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.Sales;
import au.com.scds.agric.dom.simple.SimpleObjectMenu;

import java.io.InputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import com.google.common.collect.Lists;

public class SalesCreate extends FixtureScript {

	public SalesCreate() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Sale sale = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/sales.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Sales _sales = (Sales) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
/**	
<?xml version="1.0" encoding="UTF-8"?>
<tns:sales xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
  <tns:sales>
    <tns:line>
      <tns:product-pack>
        <tns:product-item>
          <tns:serial-number>tns:serial-number</tns:serial-number>
        </tns:product-item>
        <tns:delivery>
          <tns:date>2001-12-31T12:00:00</tns:date>
        </tns:delivery>
      </tns:product-pack>
    </tns:line>
    <tns:invoice/>
    <tns:receipt/>
  </tns:sales>
</tns:sales>
*/
	public Sale getSale() {
		return this.sale;
	}


}
