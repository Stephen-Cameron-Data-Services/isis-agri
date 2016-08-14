//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.26 at 09:32:07 AM AEST 
//


package au.com.scds.agric.fixture.xml.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.com.scds.agric.fixture.xml.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Batches_QNAME = new QName("http://scds.com.au/acric/fixture/xml/generated", "batches");
    private final static QName _Orders_QNAME = new QName("http://scds.com.au/acric/fixture/xml/generated", "orders");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.com.scds.agric.fixture.xml.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BatchesFixture }
     * 
     */
    public BatchesFixture createBatchesFixture() {
        return new BatchesFixture();
    }

    /**
     * Create an instance of {@link OrdersFixture }
     * 
     */
    public OrdersFixture createOrdersFixture() {
        return new OrdersFixture();
    }

    /**
     * Create an instance of {@link BatchListFixture }
     * 
     */
    public BatchListFixture createBatchListFixture() {
        return new BatchListFixture();
    }

    /**
     * Create an instance of {@link SupplierFixture }
     * 
     */
    public SupplierFixture createSupplierFixture() {
        return new SupplierFixture();
    }

    /**
     * Create an instance of {@link PersonFixture }
     * 
     */
    public PersonFixture createPersonFixture() {
        return new PersonFixture();
    }

    /**
     * Create an instance of {@link OrderFixture }
     * 
     */
    public OrderFixture createOrderFixture() {
        return new OrderFixture();
    }

    /**
     * Create an instance of {@link TestFixture }
     * 
     */
    public TestFixture createTestFixture() {
        return new TestFixture();
    }

    /**
     * Create an instance of {@link ProductLineFixture }
     * 
     */
    public ProductLineFixture createProductLineFixture() {
        return new ProductLineFixture();
    }

    /**
     * Create an instance of {@link SampleFixture }
     * 
     */
    public SampleFixture createSampleFixture() {
        return new SampleFixture();
    }

    /**
     * Create an instance of {@link NewOrderLineFixture }
     * 
     */
    public NewOrderLineFixture createNewOrderLineFixture() {
        return new NewOrderLineFixture();
    }

    /**
     * Create an instance of {@link FormulationFixture }
     * 
     */
    public FormulationFixture createFormulationFixture() {
        return new FormulationFixture();
    }

    /**
     * Create an instance of {@link BatchFixture }
     * 
     */
    public BatchFixture createBatchFixture() {
        return new BatchFixture();
    }

    /**
     * Create an instance of {@link IngredientFixture }
     * 
     */
    public IngredientFixture createIngredientFixture() {
        return new IngredientFixture();
    }

    /**
     * Create an instance of {@link ProductTypeFixture }
     * 
     */
    public ProductTypeFixture createProductTypeFixture() {
        return new ProductTypeFixture();
    }

    /**
     * Create an instance of {@link Party }
     * 
     */
    public Party createParty() {
        return new Party();
    }

    /**
     * Create an instance of {@link OrderLine }
     * 
     */
    public OrderLine createOrderLine() {
        return new OrderLine();
    }

    /**
     * Create an instance of {@link ManufacturerFixture }
     * 
     */
    public ManufacturerFixture createManufacturerFixture() {
        return new ManufacturerFixture();
    }

    /**
     * Create an instance of {@link ClientFixture }
     * 
     */
    public ClientFixture createClientFixture() {
        return new ClientFixture();
    }

    /**
     * Create an instance of {@link BatchComponentFixture }
     * 
     */
    public BatchComponentFixture createBatchComponentFixture() {
        return new BatchComponentFixture();
    }

    /**
     * Create an instance of {@link ProductItem }
     * 
     */
    public ProductItem createProductItem() {
        return new ProductItem();
    }

    /**
     * Create an instance of {@link ScheduledOrderLineFixture }
     * 
     */
    public ScheduledOrderLineFixture createScheduledOrderLineFixture() {
        return new ScheduledOrderLineFixture();
    }

    /**
     * Create an instance of {@link CompletedOrderLineFixture }
     * 
     */
    public CompletedOrderLineFixture createCompletedOrderLineFixture() {
        return new CompletedOrderLineFixture();
    }

    /**
     * Create an instance of {@link DeliveryFixture }
     * 
     */
    public DeliveryFixture createDeliveryFixture() {
        return new DeliveryFixture();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BatchesFixture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scds.com.au/acric/fixture/xml/generated", name = "batches")
    public JAXBElement<BatchesFixture> createBatches(BatchesFixture value) {
        return new JAXBElement<BatchesFixture>(_Batches_QNAME, BatchesFixture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrdersFixture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://scds.com.au/acric/fixture/xml/generated", name = "orders")
    public JAXBElement<OrdersFixture> createOrders(OrdersFixture value) {
        return new JAXBElement<OrdersFixture>(_Orders_QNAME, OrdersFixture.class, null, value);
    }

}
