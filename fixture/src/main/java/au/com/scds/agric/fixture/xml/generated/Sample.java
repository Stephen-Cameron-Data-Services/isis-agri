//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.30 at 12:13:57 PM AEDT 
//


package au.com.scds.agric.fixture.xml.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A sample of a sampled item (product, batch, ingredient
 * 				etc.) taken for testing.
 * 			
 * 
 * <p>Java class for Sample complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sample">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sampled" type="{http://www.example.org/AgricProducerSchema}Sampled"/>
 *         &lt;element name="test" type="{http://www.example.org/AgricProducerSchema}TestSingle" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="test-group" type="{http://www.example.org/AgricProducerSchema}TestGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="test-suite" type="{http://www.example.org/AgricProducerSchema}TestSuite" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sample", propOrder = {
    "sampled",
    "test",
    "testGroup",
    "testSuite"
})
public class Sample {

    @XmlElement(required = true)
    protected Sampled sampled;
    protected List<TestSingle> test;
    @XmlElement(name = "test-group")
    protected List<TestGroup> testGroup;
    @XmlElement(name = "test-suite")
    protected List<TestSuite> testSuite;

    /**
     * Gets the value of the sampled property.
     * 
     * @return
     *     possible object is
     *     {@link Sampled }
     *     
     */
    public Sampled getSampled() {
        return sampled;
    }

    /**
     * Sets the value of the sampled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sampled }
     *     
     */
    public void setSampled(Sampled value) {
        this.sampled = value;
    }

    /**
     * Gets the value of the test property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the test property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestSingle }
     * 
     * 
     */
    public List<TestSingle> getTest() {
        if (test == null) {
            test = new ArrayList<TestSingle>();
        }
        return this.test;
    }

    /**
     * Gets the value of the testGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestGroup }
     * 
     * 
     */
    public List<TestGroup> getTestGroup() {
        if (testGroup == null) {
            testGroup = new ArrayList<TestGroup>();
        }
        return this.testGroup;
    }

    /**
     * Gets the value of the testSuite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testSuite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestSuite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestSuite }
     * 
     * 
     */
    public List<TestSuite> getTestSuite() {
        if (testSuite == null) {
            testSuite = new ArrayList<TestSuite>();
        }
        return this.testSuite;
    }

}
