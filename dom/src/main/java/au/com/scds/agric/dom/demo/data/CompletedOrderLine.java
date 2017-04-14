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
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
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
 * <p>Java class for CompletedOrderLine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompletedOrderLine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="order-line" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="completed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="completed-by" type="{http://www.example.org/AgricProducerSchema}Person" minOccurs="0"/>
 *         &lt;element name="completed-batch" type="{http://www.example.org/AgricProducerSchema}Batch" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletedOrderLine", propOrder = {
    "orderLine",
    "completedOn",
    "completedBy",
    "productPacks"
})
@DomainObject(objectType="CompletedOrderLine")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class CompletedOrderLine {

	@XmlTransient
	@Column(allowsNull="false")
	protected Order order;
	@XmlElement(name = "order-line", required = true)
    @Column(allowsNull="false")
    protected OrderLine orderLine;
    @XmlElement(name = "completed-on", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    @Column(allowsNull="true")
    protected Date completedOn;
    @XmlElement(name = "completed-by")
    @Column(allowsNull="true")
    protected Person completedBy;
    @XmlElement(name = "product-pack")
    @Join
    protected List<ProductPack> productPacks = new ArrayList<>();
    
    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    /**
     * Gets the value of the orderLine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    /**
     * Gets the value of the completed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCompletedOn() {
        return completedOn;
    }

    /**
     * Sets the value of the completed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompletedOn(Date value) {
        this.completedOn = value;
    }

    /**
     * Gets the value of the completedBy property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getCompletedBy() {
        return completedBy;
    }

    /**
     * Sets the value of the completedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setCompletedBy(Person value) {
        this.completedBy = value;
    }

    /**
     * Gets the value of the productPack property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productPack property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductPack().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductPack }
     * 
     * 
     */
    public List<ProductPack> getProductPacks() {
        return this.productPacks;
    }
    
    /**
     * 
     * Expose properties from wrapped OrderLine
     *
     */
    
    @NotPersistent
	public ProductLine getProductLine() {
		return getOrderLine().getProductLine();
	}

    @NotPersistent
	public Date getAddedOn() {
		return getOrderLine().getAddedOn();
	}

    @NotPersistent
	public Person getAddedBy() {
    	return getOrderLine().getAddedBy();
	}

}
