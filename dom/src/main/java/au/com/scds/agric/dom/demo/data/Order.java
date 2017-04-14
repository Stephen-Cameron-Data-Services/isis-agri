/*
MIT License

Copyright (c) 2017 Alexander Stephen Cameron (http://stephencamerondataservices.com.au/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package au.com.scds.agric.dom.demo.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;


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
    "orderLines",
    "scheduledOrderLines",
    "completedOrderLines"
})
@DomainObject(objectType="Order")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Order {

	@XmlTransient
	@Column(allowsNull="false")
	protected Client client;
	@XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    @Column(allowsNull="true")
    protected Date taken;
    @XmlElement(name = "taken-by", required = true)
    @Column(allowsNull="true")
    protected Person takenBy;
    @XmlElement(name = "order-line", required = true)
    @Persistent(mappedBy="order")
    protected List<OrderLine> orderLines = new ArrayList<>();
    @XmlElement(name = "scheduled-order-line")
    @Persistent(mappedBy="order")
    protected List<ScheduledOrderLine> scheduledOrderLines = new ArrayList<>();
    @XmlElement(name = "completed-order-line")
    @Persistent(mappedBy="order")
    protected List<CompletedOrderLine> completedOrderLines = new ArrayList<>();

    
    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

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
    public List<OrderLine> getOrderLines() {
        return this.orderLines;
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
    public List<ScheduledOrderLine> getScheduledOrderLines() {
        return this.scheduledOrderLines;
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
    public List<CompletedOrderLine> getCompletedOrderLines() {
        return this.completedOrderLines;
    }

}
