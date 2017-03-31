//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.31 at 07:50:58 AM AEDT 
//


package au.com.scds.agric.fixture.xml.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
    "quantity",
    "unit"
})
public class BatchComponent
    extends Sampled
{

    @XmlElement(required = true)
    protected Ingredient ingredient;
    protected float quantity;
    @XmlElement(required = true)
    protected SiUnit unit;

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
