
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.agric.dom.demo.ProductType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductLine", propOrder = {
    "batch"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class ProductLine {
	
	@XmlElement(name = "name", required = true)
	protected String name;
    @XmlElement(name = "product-type")
    protected ProductType productType;
    @XmlElement(required = true)
    protected List<Batch> batches;
    
	@Column(allowsNull="false")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(allowsNull="true")
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType value) {
        this.productType = value;
    }
    
    public List<Batch> getBatches() {
        if (batches == null) {
            batches = new ArrayList<Batch>();
        }
        return this.batches;
    }

	private void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

	public void addBatch(Batch batch) {
		if(batch==null)
			return;
		getBatches().add(batch);
	}

    
    

}
