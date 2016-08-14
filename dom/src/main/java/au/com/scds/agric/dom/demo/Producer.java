
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;


/**
 * <p>Java class for FProducer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FProducer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="product-line" type="{http://www.example.org/AgricProducerSchema}FProductLine" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FProducer", propOrder = {
    "productLine"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Producer {

    @XmlElement(name = "product-line", required = true)
    protected List<ProductLine> productLine;

    /**
     * Gets the value of the productLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductLine }
     * 
     * 
     */
    public List<ProductLine> getProductLine() {
        if (productLine == null) {
            productLine = new ArrayList<ProductLine>();
        }
        return this.productLine;
    }

}
