package au.com.scds.agric.fixture.dom.sales;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.ClientMenu90;
import au.com.scds.agric.dom.demo.InvoicesMenu101;
import au.com.scds.agric.dom.demo.ProductItemRepository;
import au.com.scds.agric.dom.demo.ProductLineMenu20;
import au.com.scds.agric.dom.demo.ProductPackRepository;
import au.com.scds.agric.dom.demo.ProductTypeMenu21;
import au.com.scds.agric.dom.demo.ReceiptsMenu102;
import au.com.scds.agric.dom.demo.SaleMenu100;
import au.com.scds.agric.dom.demo.SalesMixins;
import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.Invoice;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductPack;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.Receipt;
import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.SaleLine;
import au.com.scds.agric.dom.demo.data.Sales;
import java.io.InputStream;

import javax.inject.Inject;
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
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Sales _sales = (Sales) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			
			for(Sale _sale : _sales.getSale()){
				Client client = wrap(clientMenu).createClient("Client with Sales");
				sale = wrap(salesMenu).createSale(client);
				SalesMixins.Sale_addSaleLine sale_addSaleLine = mixin(SalesMixins.Sale_addSaleLine.class, sale);
				for(SaleLine _line : _sale.getLines()){
					ProductType productType = wrap(productTypeMenu).createProductType(_line.getProductLine().getProductType().getName());
					ProductLine productLine = wrap(productLineMenu).createProductLine(_line.getProductLine().getName(), productType);
					SaleLine line = wrap(sale_addSaleLine).$$(productLine);
					for(ProductPack _pack : _line.getProductPacks()){
						ProductPack pack = wrap(productPackRepo).createProductPack(productLine);
						for(ProductItem _item : _pack.getProductItems()){
							ProductItem item = wrap(productItemRepo).createProductItem(productLine, _item.getSerialNumber());
							pack.getProductItems().add(item);
						}
						line.getProductPacks().add(pack);
					}
				}
				if(_sale.getInvoice() != null){
					Invoice invoice = wrap(invoicesMenu).createInvoice(client);
					sale.setInvoice(invoice);
				}
				if(_sale.getReceipt() != null){
					Receipt receipt = wrap(receiptsMenu).createReceipt(client);
					sale.setReceipt(receipt);
				}
			}
			ec.addResult(this, sale);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/*
<?xml version="1.0" encoding="UTF-8"?>
<tns:sales xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
  <tns:sale>
    <tns:line>
      <tns:product-line>
          <tns:name>tns:name</tns:name>
          <tns:product-type>
            <tns:name>tns:name</tns:name>
          </tns:product-type>
      </tns:product-line>
      <tns:product-pack>
        <tns:product-line>
          <tns:name>tns:name</tns:name>
          <tns:product-type>
            <tns:name>tns:name</tns:name>
          </tns:product-type>
        </tns:product-line>
        <tns:product-item>
          <tns:serial-number>tns:serial-number</tns:serial-number>
          <tns:product-line>
            <tns:name>tns:name</tns:name>
          </tns:product-line>
        </tns:product-item>
      </tns:product-pack>
    </tns:line>
    <tns:invoice/>
    <tns:receipt/>
  </tns:sale>
</tns:sales>
	 */
	public Sale getSale() {
		return this.sale;
	}
	
	@Inject
	private ProductTypeMenu21 productTypeMenu;	
	@Inject
	private ProductLineMenu20 productLineMenu;
	@Inject
	private ClientMenu90 clientMenu;
	@Inject
	private SaleMenu100 salesMenu;
	@Inject
	private InvoicesMenu101 invoicesMenu;
	@Inject
	private ReceiptsMenu102 receiptsMenu;
	@Inject 
	private ProductPackRepository productPackRepo;
	@Inject 
	private ProductItemRepository productItemRepo;
}
