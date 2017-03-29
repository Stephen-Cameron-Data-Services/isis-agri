//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.29 at 06:25:06 PM AEDT 
//


package au.com.scds.agric.fixture.xml.generated;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * An Order Line that has been scheduled for filling as
 * 				part of a specific Batch
 * 			
 * 
 * <p>Java class for ScheduledOrderLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScheduledOrderLine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/AgricProducerSchema}NewOrderLine">
 *       &lt;sequence>
 *         &lt;element name="scheduled-on" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="scheduled-by" type="{http://www.example.org/AgricProducerSchema}Person"/>
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
@XmlType(name = "ScheduledOrderLine", propOrder = {
    "scheduledOn",
    "scheduledBy",
    "scheduledBatch"
})
@XmlSeeAlso({
    CompletedOrderLine.class
})
public class ScheduledOrderLine
    extends NewOrderLine
{

    @XmlElement(name = "scheduled-on", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date scheduledOn;
    @XmlElement(name = "scheduled-by", required = true)
    protected Person scheduledBy;
    @XmlElement(name = "scheduled-batch", required = true)
    protected String scheduledBatch;

    /**
     * Gets the value of the scheduledOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getScheduledOn() {
        return scheduledOn;
    }

    /**
     * Sets the value of the scheduledOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduledOn(Date value) {
        this.scheduledOn = value;
    }

    /**
     * Gets the value of the scheduledBy property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getScheduledBy() {
        return scheduledBy;
    }

    /**
     * Sets the value of the scheduledBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setScheduledBy(Person value) {
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
