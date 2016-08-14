//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.13 at 01:37:40 PM AEST 
//


package au.com.scds.agric.fixture.xml.generated2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FScheduledOrderLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FScheduledOrderLine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/AgricProducerSchema}FNewOrderLine">
 *       &lt;sequence>
 *         &lt;element name="scheduled-on" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="scheduled-by" type="{http://www.example.org/AgricProducerSchema}FPerson"/>
 *         &lt;element name="scheduled-batch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FScheduledOrderLine", propOrder = {
    "scheduledOn",
    "scheduledBy",
    "scheduledBatch"
})
@XmlSeeAlso({
    FCompletedOrderLine.class
})
public class FScheduledOrderLine
    extends FNewOrderLine
{

    @XmlElement(name = "scheduled-on", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduledOn;
    @XmlElement(name = "scheduled-by", required = true)
    protected FPerson scheduledBy;
    @XmlElement(name = "scheduled-batch", required = true)
    protected String scheduledBatch;

    /**
     * Gets the value of the scheduledOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledOn() {
        return scheduledOn;
    }

    /**
     * Sets the value of the scheduledOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledOn(XMLGregorianCalendar value) {
        this.scheduledOn = value;
    }

    /**
     * Gets the value of the scheduledBy property.
     * 
     * @return
     *     possible object is
     *     {@link FPerson }
     *     
     */
    public FPerson getScheduledBy() {
        return scheduledBy;
    }

    /**
     * Sets the value of the scheduledBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link FPerson }
     *     
     */
    public void setScheduledBy(FPerson value) {
        this.scheduledBy = value;
    }

    /**
     * Gets the value of the scheduledBatch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduledBatch() {
        return scheduledBatch;
    }

    /**
     * Sets the value of the scheduledBatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduledBatch(String value) {
        this.scheduledBatch = value;
    }

}
