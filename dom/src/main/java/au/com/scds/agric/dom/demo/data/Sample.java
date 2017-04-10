package au.com.scds.agric.dom.demo.data;

import java.util.ArrayList;
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
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

/**
 * A sample of a sampled item (product, batch, ingredient etc.) taken for
 * testing.
 * 
 * 
 * <p>
 * Java class for Sample complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
* &lt;complexType name="Sample">
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;element name="sampled" type="{http://www.example.org/AgricProducerSchema}Sampled"/>
*         &lt;element name="test" type="{http://www.example.org/AgricProducerSchema}TestMultiple" maxOccurs="unbounded" minOccurs="0"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sample", propOrder = { "sampled", "tests" })
@DomainObject(objectType = "Sample")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Sample {

	@XmlElement(required = true)
	@Column(allowsNull = "false")
	protected Sampled sampled;
	@Persistent(mappedBy = "sample")
	protected List<TestMultiple> tests = new ArrayList<>();

	/**
	 * Gets the value of the sampled property.
	 * 
	 * @return possible object is {@link Sampled }
	 * 
	 */
	public Sampled getSampled() {
		return sampled;
	}

	/**
	 * Sets the value of the sampled property.
	 * 
	 * @param value
	 *            allowed object is {@link Sampled }
	 * 
	 */
	public void setSampled(Sampled value) {
		this.sampled = value;
	}

	/**
	 * Gets the value of the test property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the test property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTest().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TestMultiple }
	 * 
	 * 
	 */
	public List<TestMultiple> getTests() {
		return this.tests;
	}

}
