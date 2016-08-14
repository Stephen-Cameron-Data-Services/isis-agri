
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
@XmlType(name = "FSupply", propOrder = {
    "manufacturer"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Supply
    extends Sampled
{
	protected Supplier supplier;
	protected Ingredient parentIngredient;
	@XmlElement(required = true)
    protected Manufacturer manufacturer;
	
	@Column(allowsNull = "true")
    public Ingredient getParentIngredient() {
		return parentIngredient;
	}

	public void setParentIngredient(Ingredient parentIngredient) {
		this.parentIngredient = parentIngredient;
	}

	@Column(allowsNull = "true")
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer value) {
        this.manufacturer = value;
    }

	@Column(allowsNull = "true")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
