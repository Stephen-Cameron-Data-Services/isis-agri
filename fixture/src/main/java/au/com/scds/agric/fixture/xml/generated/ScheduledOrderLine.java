//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.10 at 08:01:00 PM AEST 
//


package au.com.scds.agric.fixture.xml.generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="order-line" type="{http://www.example.org/AgricProducerSchema}OrderLine"/>
 *         &lt;element name="scheduled-on" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="scheduled-by" type="{http://www.example.org/AgricProducerSchema}Person" minOccurs="0"/>
 *         &lt;element name="orderline-batch-portion" type="{http://www.example.org/AgricProducerSchema}OrderLineBatch" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduledOrderLine", propOrder = {
    "orderLine",
    "scheduledOn",
    "scheduledBy",
    "orderlineBatchPortion"
})
public class ScheduledOrderLine {

    @XmlElement(name = "order-line", required = true)
    protected OrderLine orderLine;
    @XmlElement(name = "scheduled-on", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date scheduledOn;
    @XmlElement(name = "scheduled-by")
    protected Person scheduledBy;
    @XmlElement(name = "orderline-batch-portion")
    protected List<OrderLineBatch> orderlineBatchPortion;

    /**
     * Gets the value of the orderLine property.
     * 
     * @return
     *     possible object is
     *     {@link OrderLine }
     *     
     */
    public OrderLine getOrderLine() {
        return orderLine;
    }

    /**
     * Sets the value of the orderLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderLine }
     *     
     */
    public void setOrderLine(OrderLine value) {
        this.orderLine = value;
    }

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
     * Gets the value of the orderlineBatchPortion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderlineBatchPortion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderlineBatchPortion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderLineBatch }
     * 
     * 
     */
    public List<OrderLineBatch> getOrderlineBatchPortion() {
        if (orderlineBatchPortion == null) {
            orderlineBatchPortion = new ArrayList<OrderLineBatch>();
        }
        return this.orderlineBatchPortion;
    }

}
