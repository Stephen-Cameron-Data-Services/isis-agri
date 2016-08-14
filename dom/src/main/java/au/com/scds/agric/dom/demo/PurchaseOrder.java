
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchaseOrder", propOrder = {
    "supply"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class PurchaseOrder {

    @XmlElement(required = true)
    protected List<Supply> supply;

    public List<Supply> getSupply() {
        if (supply == null) {
            supply = new ArrayList<Supply>();
        }
        return this.supply;
    }

}
