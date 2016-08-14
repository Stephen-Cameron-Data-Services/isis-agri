
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestSuite", propOrder = {
    "testOrTestGroup"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class TestSuite {

    protected List<Test> tests;
    protected List<TestGroup> testGroups;

    @Persistent(table="testsuite_test")
    @Join(column="testsuite_id")
    @Element(column="test_id")
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))
    public List<Test> getTests() {
        if (tests == null) {
        	tests = new ArrayList<Test>();
        }
        return this.tests;
    }
    
    public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	@Persistent(table="testsuite_testgroup")
    @Join(column="testsuite_id")
    @Element(column="testgroup_id")
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))
    public List<TestGroup> getTestGroups() {
        if (testGroups == null) {
        	testGroups = new ArrayList<TestGroup>();
        }
        return this.testGroups;
    }

	public void setTestGroups(List<TestGroup> testGroups) {
		this.testGroups = testGroups;
	}

}
