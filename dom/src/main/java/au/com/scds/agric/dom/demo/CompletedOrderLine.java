
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
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletedOrderLine", propOrder = {
    "completed",
    "completedBy"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class CompletedOrderLine
    extends ScheduledOrderLine
{
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date completed;
    @XmlElement(name = "completed-by", required = true)
    protected Person completedBy;

    @Column(allowsNull = "true")
    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date value) {
        this.completed = value;
    }

    @Column(allowsNull = "true")
    public Person getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(Person value) {
        this.completedBy = value;
    }

}
