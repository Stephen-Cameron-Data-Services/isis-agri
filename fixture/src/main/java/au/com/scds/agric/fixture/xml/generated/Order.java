//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.30 at 12:13:57 PM AEDT 
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
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taken" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="taken-by" type="{http://www.example.org/AgricProducerSchema}Person"/>
 *         &lt;element name="order-line" type="{http://www.example.org/AgricProducerSchema}NewOrderLine" maxOccurs="unbounded"/>
 *         &lt;element name="scheduled-order-line" type="{http://www.example.org/AgricProducerSchema}ScheduledOrderLine" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="completed-order-line" type="{http://www.example.org/AgricProducerSchema}CompletedOrderLine" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "taken",
    "takenBy",
    "orderLine",
    "scheduledOrderLine",
    "completedOrderLine"
})
public class Order {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date taken;
    @XmlElement(name = "taken-by", required = true)
    protected Person takenBy;
    @XmlElement(name = "order-line", required = true)
    protected List<NewOrderLine> orderLine;
    @XmlElement(name = "scheduled-order-line")
    protected List<ScheduledOrderLine> scheduledOrderLine;
    @XmlElement(name = "completed-order-line")
    protected List<CompletedOrderLine> completedOrderLine;

    /**
     * Gets the value of the taken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTaken() {
        return taken;
    }

    /**
     * Sets the value of the taken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaken(Date value) {
        this.taken = value;
    }

    /**
     * Gets the value of the takenBy property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getTakenBy() {
        return takenBy;
    }

    /**
     * Sets the value of the takenBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setTakenBy(Person value) {
        this.takenBy = value;
    }

    /**
     * Gets the value of the orderLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewOrderLine }
     * 
     * 
     */
    public List<NewOrderLine> getOrderLine() {
        if (orderLine == null) {
            orderLine = new ArrayList<NewOrderLine>();
        }
        return this.orderLine;
    }

    /**
     * Gets the value of the scheduledOrderLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduledOrderLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduledOrderLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduledOrderLine }
     * 
     * 
     */
    public List<ScheduledOrderLine> getScheduledOrderLine() {
        if (scheduledOrderLine == null) {
            scheduledOrderLine = new ArrayList<ScheduledOrderLine>();
        }
        return this.scheduledOrderLine;
    }

    /**
     * Gets the value of the completedOrderLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the completedOrderLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompletedOrderLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompletedOrderLine }
     * 
     * 
     */
    public List<CompletedOrderLine> getCompletedOrderLine() {
        if (completedOrderLine == null) {
            completedOrderLine = new ArrayList<CompletedOrderLine>();
        }
        return this.completedOrderLine;
    }

}
