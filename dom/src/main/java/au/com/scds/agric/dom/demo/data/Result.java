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
 * <p>
 * Java class for Result complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="test" type="{http://www.example.org/AgricProducerSchema}Test"/>
 *         &lt;element name="test-result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = { "test", "testResult" })
@DomainObject(objectType="Result")
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Result {

	@XmlTransient
	@Column(allowsNull = "false")
	private Sample sample;
	@XmlTransient
	@Column(allowsNull = "true")
	private TestMultiple sampleTest;
	@XmlElement(required = true)
	@Column(allowsNull = "false")
	private TestSingle test;
	@XmlElement(name = "test-result", required = true)
	@Column(allowsNull = "true")
	private String testResult;
	
	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public TestMultiple getSampleTest() {
		return sampleTest;
	}

	public void setSampleTest(TestMultiple sampleTest) {
		this.sampleTest = sampleTest;
	}

	/**
	 * Gets the value of the test property.
	 * 
	 * @return possible object is {@link Test }
	 * 
	 */
	public TestSingle getTest() {
		return test;
	}

	/**
	 * Sets the value of the test property.
	 * 
	 * @param value
	 *            allowed object is {@link Test }
	 * 
	 */
	public void setTest(TestSingle value) {
		this.test = value;
	}

	/**
	 * Gets the value of the testResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTestResult() {
		return testResult;
	}

	/**
	 * Sets the value of the testResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTestResult(String value) {
		this.testResult = value;
	}

}
