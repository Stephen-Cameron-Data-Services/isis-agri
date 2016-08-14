
package au.com.scds.agric.dom.demo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FParty", propOrder = {
    "name"
})
@XmlSeeAlso({
    Manufacturer.class,
    Client.class,
    Supplier.class,
    Consumer.class
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public abstract class Party {

    @XmlElement(required = true)
    protected String name;

    @Column(allowsNull="true")
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}
