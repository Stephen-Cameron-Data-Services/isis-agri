
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
@XmlType(name = "ProductItem", propOrder = {
    "serialNumber"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class ProductItem
    extends Sampled
{
	protected Batch parentBatch;
	@XmlElement(name = "serial-number", required = true)
    protected String serialNumber;
	protected ProductPack productPack;
	
	@Column(allowsNull = "true")
    public Batch getParentBatch() {
		return parentBatch;
	}

	public void setParentBatch(Batch parentBatch) {
		this.parentBatch = parentBatch;
	}

    @Column(allowsNull = "true")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }
    
	@Column(allowsNull = "true")	
	public ProductPack getProductPack() {
		return productPack;
	}

	public void setProductPack(ProductPack productPack) {
		this.productPack = productPack;
	}

}
