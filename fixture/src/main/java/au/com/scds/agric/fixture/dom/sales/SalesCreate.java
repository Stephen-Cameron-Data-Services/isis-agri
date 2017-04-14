package au.com.scds.agric.fixture.dom.sales;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.Sales;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

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
