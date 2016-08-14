
package au.com.scds.agric.dom.demo;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchComponent", propOrder = {
    "parentSupply"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class BatchComponent
    extends Sampled
{

    @XmlElement(name = "parent-supply", required = true)
    protected Supply parentSupply;
    protected Batch parentBatch;
    protected Ingredient parentIngredient;

    @Column(allowsNull = "true")
    public Supply getSupply() {
        return parentSupply;
    }

    public void setSupply(Supply value) {
        this.parentSupply = value;
    }

    @Column(allowsNull = "true")
    public Batch getBatch() {
        return parentBatch;
    }

    public void setBatch(Batch value) {
        this.parentBatch = value;
    }

    @Column(allowsNull = "true")
	public Ingredient getIngredient() {
		return parentIngredient;
	}

	public void setIngredient(Ingredient parentIngredient) {
		this.parentIngredient = parentIngredient;
	}
}
