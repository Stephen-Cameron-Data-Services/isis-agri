
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

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FScheduledOrderLine", propOrder = {
    "scheduledOn",
    "scheduledBy",
    "scheduledBatch"
})
@XmlSeeAlso({
    CompletedOrderLine.class
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class ScheduledOrderLine
    extends NewOrderLine
{
    @XmlElement(name = "scheduled-on", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date scheduledOn;
    @XmlElement(name = "scheduled-by", required = true)
    protected Person scheduledBy;
    @XmlElement(name = "scheduled-batch", required = true)
    protected String scheduledBatch;

    @Column(allowsNull="true")
    public Date getScheduledOn() {
        return scheduledOn;
    }

    public void setScheduledOn(Date value) {
        this.scheduledOn = value;
    }

    @Column(allowsNull="true")
    public Person getScheduledBy() {
        return scheduledBy;
    }

    public void setScheduledBy(Person value) {
        this.scheduledBy = value;
    }

    @Column(allowsNull="true")
    public String getScheduledBatch() {
        return scheduledBatch;
    }

    public void setScheduledBatch(String value) {
        this.scheduledBatch = value;
    }

}
