
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FOrderLine", propOrder = {
    "productPack"
})
@XmlSeeAlso({
    NewOrderLine.class
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public abstract class OrderLine {

	protected Order parentOrder;
	protected ProductLine productLine;
	protected Integer quantity;

	@XmlElement(name = "product-pack")
    protected List<ProductPack> productPack;
	
	@Column(allowsNull="false")
	public Order getParentOrder() {
		return parentOrder;
	}

	public void setParentOrder(Order parentOrder) {
		this.parentOrder = parentOrder;
	}
    
	@Column(allowsNull="true")
    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine value) {
        this.productLine = value;
    }
    
    @Column(allowsNull="true")
    public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
    public List<ProductPack> getProductPack() {
        if (productPack == null) {
            productPack = new ArrayList<ProductPack>();
        }
        return this.productPack;
    }
    
    public void setProductPack(List<ProductPack> productPack) {
		this.productPack = productPack;
	}
}
