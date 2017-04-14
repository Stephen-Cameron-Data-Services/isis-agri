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
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sample2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sample2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sampled" type="{http://www.example.org/AgricProducerSchema}Sampled"/>
 *         &lt;element name="test-single" type="{http://www.example.org/AgricProducerSchema}TestSingle" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Sample2", propOrder = {
    "sampled",
    "testSingle",
    "testGroup",
    "testSuite"
})
public class Sample2 {

    @XmlElement(required = true)
    protected Sampled sampled;
    @XmlElement(name = "test-single")
    protected List<TestSingle> testSingle;
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
     * Gets the value of the testSingle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testSingle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestSingle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestSingle }
     * 
     * 
     */
    public List<TestSingle> getTestSingle() {
        if (testSingle == null) {
            testSingle = new ArrayList<TestSingle>();
        }
        return this.testSingle;
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
