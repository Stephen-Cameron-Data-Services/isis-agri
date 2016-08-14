
package au.com.scds.agric.dom.demo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = {
    "test",
    "testResult"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Result {

	protected Sample sample;
	@XmlElement(required = true)
    protected Test parentTest;
    @XmlElement(name = "test-result", required = true)
    protected String testResult;
    
    @Column(allowsNull="false")
    public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

    @Column(allowsNull="false")
    public Test getParentTest() {
        return parentTest;
    }

    public void setParentTest(Test value) {
        this.parentTest = value;
    }

    @Column(allowsNull="true")
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String value) {
        this.testResult = value;
    }

}
