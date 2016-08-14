
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
@XmlType(name = "FPerson", propOrder = {
    "firstName",
    "lastName"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Person {

    @XmlElement(name = "first-name", required = true)
    protected String firstName;
    @XmlElement(name = "last-name", required = true)
    protected String lastName;

    @Column(allowsNull="false")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    @Column(allowsNull="false")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

}
