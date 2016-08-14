
package au.com.scds.agric.dom.demo;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.agric.dom.demo.Person;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewOrderLine", propOrder = {
    "addedOn",
    "addedBy"
})
@XmlSeeAlso({
    ScheduledOrderLine.class
})
@DomainObject()
@PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class NewOrderLine
    extends OrderLine
{
    @XmlElement(name = "added-on", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date addedOn;
    @XmlElement(name = "added-by", required = true)
    protected Person addedBy;

	@Column(allowsNull = "true")
    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date value) {
        this.addedOn = value;
    }

	@Column(allowsNull = "true")
    public Person getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Person value) {
        this.addedBy = value;
    }

}
