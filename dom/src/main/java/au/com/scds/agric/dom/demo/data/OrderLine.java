package au.com.scds.agric.dom.demo.data;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;


/**
 * <p>Java class for OrderLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderLine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="product-line" type="{http://www.example.org/AgricProducerSchema}ProductLine"/>
 *         &lt;element name="added-on" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="added-by" type="{http://www.example.org/AgricProducerSchema}Person"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderLine", propOrder = {
    "productLine",
    "addedOn",
    "addedBy"
})
@DomainObject(objectType="OrderLine")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class OrderLine {
	
	@XmlTransient
	//want to disconnect
	@Column(allowsNull="true")
	protected Order order;
    @XmlElement(name = "product-line", required = true)
    @Column(allowsNull="false")
    protected ProductLine productLine;
	@XmlElement(name = "added-on", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    @Column(allowsNull="true")
    protected Date addedOn;
    @XmlElement(name = "added-by", required = true)
    @Column(allowsNull="true")
    protected Person addedBy;
    
    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    /**
     * Gets the value of the productLine property.
     * 
     * @return
     *     possible object is
     *     {@link ProductLine }
     *     
     */
    public ProductLine getProductLine() {
        return productLine;
    }

    /**
     * Sets the value of the productLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductLine }
     *     
     */
    public void setProductLine(ProductLine value) {
        this.productLine = value;
    }

    /**
     * Gets the value of the addedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAddedOn() {
        return addedOn;
    }

    /**
     * Sets the value of the addedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddedOn(Date value) {
        this.addedOn = value;
    }

    /**
     * Gets the value of the addedBy property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getAddedBy() {
        return addedBy;
    }

    /**
     * Sets the value of the addedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setAddedBy(Person value) {
        this.addedBy = value;
    }

}
