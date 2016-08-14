
package au.com.scds.agric.dom.demo;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Delivery", propOrder = {
    "date"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Delivery {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date date;

    @Column(allowsNull="false")
    public Date getDate() {
        return date;
    }

    public void setDate(Date value) {
        this.date = value;
    }
}
