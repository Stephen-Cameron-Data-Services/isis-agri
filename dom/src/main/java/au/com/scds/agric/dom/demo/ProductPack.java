
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FProductPack", propOrder = {
    "productItem",
    "delivery"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class ProductPack {

    @XmlElement(name = "product-item", required = true)
    protected List<ProductItem> productItem;
    protected Delivery delivery;

    @Persistent(mappedBy="productPack")
	public List<ProductItem> getProductItem() {
        if (productItem == null) {
            productItem = new ArrayList<ProductItem>();
        }
        return this.productItem;
    }
	
    public void setProductItem(List<ProductItem> productItem) {
		this.productItem = productItem;
	}

    @Column(allowsNull="true")
    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery value) {
        this.delivery = value;
    }

}
