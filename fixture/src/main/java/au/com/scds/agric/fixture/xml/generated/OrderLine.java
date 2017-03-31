//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.31 at 07:50:58 AM AEDT 
//


package au.com.scds.agric.fixture.xml.generated;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
public class OrderLine {

    @XmlElement(name = "product-line", required = true)
    protected ProductLine productLine;
    @XmlElement(name = "added-on", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date addedOn;
    @XmlElement(name = "added-by", required = true)
    protected Person addedBy;

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
