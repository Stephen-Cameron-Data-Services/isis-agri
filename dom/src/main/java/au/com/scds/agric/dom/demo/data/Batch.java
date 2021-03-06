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
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Order;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.isis.applib.annotation.DomainObject;

/**
 * A batch of product, efficiently created in bulk for subdivision into
 * product-item lots.
 * 
 * 
 * <p>
 * Java class for Batch complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Batch">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/AgricProducerSchema}Sampled">
 *       &lt;sequence>
 *         &lt;element name="created-on" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="created-by" type="{http://www.example.org/AgricProducerSchema}Person"/>
 *         &lt;element name="scheduled-for" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="completed-on" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="completed-by" type="{http://www.example.org/AgricProducerSchema}Person" minOccurs="0"/>
 *         &lt;element name="formulation" type="{http://www.example.org/AgricProducerSchema}Formulation"/>
 *         &lt;element name="product-item" type="{http://www.example.org/AgricProducerSchema}ProductItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Batch", propOrder = { "productLine", "createdOn", "createdBy", "scheduledFor", "completedOn", "completedBy",
		"formulation","specification","batchComponents", "productItems" })
@DomainObject(objectType = "Batch")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Queries({
		@Query(name = "findById", language = "JDOQL", value = "SELECT FROM au.com.scds.agric.dom.demo.data.Batch WHERE batch_id_OID == :id ") })
public class Batch extends Sampled {

    @XmlElement(name = "product-line", required = true)
	@Column(allowsNull = "false")
	protected ProductLine productLine;
	@XmlElement(name = "created-on", required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	protected Date createdOn;
	@XmlElement(name = "created-by", required = true)
	@Column(allowsNull = "true")
	protected Person createdBy;
	@XmlElement(name = "scheduled-for", type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	protected Date scheduledFor;
	@XmlElement(name = "completed-on", type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	protected Date completedOn;
	@XmlElement(name = "completed-by")
	@Column(allowsNull = "true")
	protected Person completedBy;
	@XmlElement()
	@Column(allowsNull = "true")
	protected Formulation formulation;
	@XmlElement()
	@Column(allowsNull = "true")	
    protected Specification specification;
    @XmlElement(name = "batch-component")
	@Persistent(mappedBy = "batch")
	@Order(column="batchorder_idx")
    protected List<BatchComponent> batchComponents = new ArrayList<>();
	@XmlElement(name = "product-item")
	@Persistent(mappedBy = "batch")
	@Order(column="batchorder_idx")
	protected List<ProductItem> productItems = new ArrayList<>();
	@XmlTransient
	@Persistent(mappedBy = "batch")
	@Order(column="batchorder_idx")
	protected List<OrderLineBatch> batchOrderLines = new ArrayList<>();


	public ProductLine getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

	/**
	 * Gets the value of the createdOn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the value of the createdOn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatedOn(Date value) {
		this.createdOn = value;
	}

	/**
	 * Gets the value of the createdBy property.
	 * 
	 * @return possible object is {@link Person }
	 * 
	 */
	public Person getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the value of the createdBy property.
	 * 
	 * @param value
	 *            allowed object is {@link Person }
	 * 
	 */
	public void setCreatedBy(Person value) {
		this.createdBy = value;
	}

	/**
	 * Gets the value of the scheduledFor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getScheduledFor() {
		return scheduledFor;
	}

	/**
	 * Sets the value of the scheduledFor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setScheduledFor(Date value) {
		this.scheduledFor = value;
	}

	/**
	 * Gets the value of the completedOn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getCompletedOn() {
		return completedOn;
	}

	/**
	 * Sets the value of the completedOn property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCompletedOn(Date value) {
		this.completedOn = value;
	}

	/**
	 * Gets the value of the completedBy property.
	 * 
	 * @return possible object is {@link Person }
	 * 
	 */
	public Person getCompletedBy() {
		return completedBy;
	}

	/**
	 * Sets the value of the completedBy property.
	 * 
	 * @param value
	 *            allowed object is {@link Person }
	 * 
	 */
	public void setCompletedBy(Person value) {
		this.completedBy = value;
	}

	/**
	 * Gets the value of the formulation property.
	 * 
	 * @return possible object is {@link Formulation }
	 * 
	 */
	public Formulation getFormulation() {
		return formulation;
	}

	/**
	 * Sets the value of the formulation property.
	 * 
	 * @param value
	 *            allowed object is {@link Formulation }
	 * 
	 */
	public void setFormulation(Formulation value) {
		this.formulation = value;
	}
	
    /**
     * Gets the value of the specification property.
     * 
     * @return
     *     possible object is
     *     {@link Specification }
     *     
     */
    public Specification getSpecification() {
        return specification;
    }

    /**
     * Sets the value of the specification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Specification }
     *     
     */
    public void setSpecification(Specification value) {
        this.specification = value;
    }

    /**
     * Gets the value of the batchComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchComponent }
     * 
     * 
     */
    public List<BatchComponent> getBatchComponents() {
        return this.batchComponents;
    }

	/**
	 * Gets the value of the productItem property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the productItem property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getProductItem().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ProductItem }
	 * 
	 * 
	 */
	public List<ProductItem> getProductItems() {
		return this.productItems;
	}

	public List<OrderLineBatch> getBatchOrderLines() {
		return this.batchOrderLines;
	}

}
