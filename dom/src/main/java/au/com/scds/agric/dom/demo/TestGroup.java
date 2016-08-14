
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
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestGroup", propOrder = {
    "test"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class TestGroup {

    @XmlElement(required = true)
    protected List<Test> test;

	@Persistent(table="testgroup_test")
    @Join(column="testgroup_id")
    @Element(column="test_id")
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))    
    public List<Test> getTest() {
        if (test == null) {
            test = new ArrayList<Test>();
        }
        return this.test;
    }

	public void setTest(List<Test> test) {
		this.test = test;
	}
}
