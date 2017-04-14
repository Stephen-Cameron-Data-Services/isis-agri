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
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;



/**
 * A specific supply (or batch) of an Ingredient consumed
 * 				in the production of Batches of Product for sale.
 * 				Possibly purchased
 * 				from a supplier and produced by another manufacturer.
 * 			
 * 
 * <p>Java class for IngredientSupply complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IngredientSupply">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/AgricProducerSchema}Sampled">
 *       &lt;sequence>
 *         &lt;element name="manufacturer" type="{http://www.example.org/AgricProducerSchema}IngredientManufacturer" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://www.example.org/AgricProducerSchema}IngredientSupplier" minOccurs="0"/>
 *         &lt;element name="quantity-initial" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="quantity-remaining" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IngredientSupply", propOrder = {
    "manufacturer",
    "supplier",
    "quantityInitial",
    "quantityRemaining",
    "unit"
})
@DomainObject(objectType="IngredientSupply")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class IngredientSupply
    extends Sampled
{
	@Column(allowsNull="true")
    protected IngredientManufacturer manufacturer;
	@Column(allowsNull="true")
    protected IngredientSupplier supplier;
    @XmlElement(name = "quantity-initial", required = true)
    @Column(allowsNull="false")
    protected float quantityInitial;
    @XmlElement(name = "quantity-remaining", required = true)
    @Column(allowsNull="false")
    protected float quantityRemaining;
    @XmlElement(required = true)
    @Column(allowsNull="false")
    protected SiUnit unit;

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientManufacturer }
     *     
     */
    public IngredientManufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientManufacturer }
     *     
     */
    public void setManufacturer(IngredientManufacturer value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientSupplier }
     *     
     */
    public IngredientSupplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientSupplier }
     *     
     */
    public void setSupplier(IngredientSupplier value) {
        this.supplier = value;
    }

    /**
     * Gets the value of the quantityInitial property.
     * 
     */
    public float getQuantityInitial() {
        return quantityInitial;
    }

    /**
     * Sets the value of the quantityInitial property.
     * 
     */
    public void setQuantityInitial(float value) {
        this.quantityInitial = value;
    }
    
    /**
     * Gets the value of the quantityRemaining property.
     * 
     */
    public float getQuantityRemaining() {
        return quantityRemaining;
    }

    /**
     * Sets the value of the quantityRemaining property.
     * 
     */
    public void setQuantityRemaining(float value) {
        this.quantityRemaining = value;
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
