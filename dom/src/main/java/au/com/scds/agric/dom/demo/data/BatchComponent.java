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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;


/**
 * A component of a Batch taken from a larger Supply
 * 			
 * 
 * <p>Java class for BatchComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchComponent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/AgricProducerSchema}Sampled">
 *       &lt;sequence>
 *         &lt;element name="ingredient" type="{http://www.example.org/AgricProducerSchema}Ingredient"/>
 *         &lt;element name="parent-supply" type="{http://www.example.org/AgricProducerSchema}IngredientSupply"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="unit" type="{http://www.example.org/AgricProducerSchema}SiUnit"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchComponent", propOrder = {
    "ingredient",
    "parentSupply",
    "quantity",
    "unit"
})
@DomainObject(objectType="BatchComponent")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class BatchComponent
    extends Sampled
{
	@XmlTransient
	@Column(allowsNull = "false")
	protected Batch batch;
	@XmlElement(required = true)
    @Column(allowsNull="true")
    protected Ingredient ingredient;
    @XmlElement(name = "parent-supply", required = true)
    @Column(allowsNull="true")
    protected IngredientSupply parentSupply;
    @Column(allowsNull="true")
    protected float quantity;
    @XmlElement(required = true)
    @Column(allowsNull="true")
    protected SiUnit unit;
    
    public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

    /**
     * Gets the value of the ingredient property.
     * 
     * @return
     *     possible object is
     *     {@link Ingredient }
     *     
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Sets the value of the ingredient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ingredient }
     *     
     */
    public void setIngredient(Ingredient value) {
        this.ingredient = value;
    }

    /**
     * Gets the value of the parentSupply property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientSupply }
     *     
     */
    public IngredientSupply getParentSupply() {
        return parentSupply;
    }

    /**
     * Sets the value of the parentSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientSupply }
     *     
     */
    public void setParentSupply(IngredientSupply value) {
        this.parentSupply = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(float value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link SiUnit }
     *     
     */
    public SiUnit getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiUnit }
     *     
     */
    public void setUnit(SiUnit value) {
        this.unit = value;
    }

}
