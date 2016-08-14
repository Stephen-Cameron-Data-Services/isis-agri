
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

/**
 * A batch of product, efficiently created in bulk for
 * 				subdivision into product-item lots.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FBatch", propOrder = {
    "createdOn",
    "createdBy",
    "scheduledFor",
    "completedOn",
    "completedBy",
    "formulation",
    "productItem"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Batch
    extends Sampled
{
	protected ProductLine productLine;


	@XmlElement(name = "created-on", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date createdOn;
    @XmlElement(name = "created-by", required = true)
    protected Person createdBy;
    @XmlElement(name = "scheduled-for")
    @XmlSchemaType(name = "dateTime")
    protected Date scheduledFor;
    @XmlElement(name = "completed-on")
    @XmlSchemaType(name = "dateTime")
    protected Date completedOn;
    @XmlElement(name = "completed-by")
    protected Person completedBy;
    @XmlElement(required = true)
    protected Formulation formulation;
    @XmlElement(name = "product-item")
    protected List<ProductItem> productItem;
    
    protected List<BatchComponent> components;
    
    @Column(allowsNull = "false")
    public ProductLine getProductLine() {
 		return productLine;
 	}

 	public void setProductLine(ProductLine productLine) {
 		this.productLine = productLine;
 	}

    @Column(allowsNull = "true")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date value) {
        this.createdOn = value;
    }

    @Column(allowsNull = "true")
    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person value) {
        this.createdBy = value;
    }

    @Column(allowsNull = "true")
    public Date getScheduledFor() {
        return scheduledFor;
    }

    public void setScheduledFor(Date value) {
        this.scheduledFor = value;
    }

    @Column(allowsNull = "true")
    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date value) {
        this.completedOn = value;
    }

    @Column(allowsNull = "true")
    public Person getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(Person value) {
        this.completedBy = value;
    }

    @Column(allowsNull = "true")
    public Formulation getFormulation() {
        return formulation;
    }

    public void setFormulation(Formulation value) {
        this.formulation = value;
    }

    @Persistent(mappedBy = "parentBatch")
    public List<BatchComponent> getComponents() {
        if (components == null) {
            components = new ArrayList<BatchComponent>();
        }
        return this.components;
    }
    
    public void setComponents(List<BatchComponent> components) {
		this.components = components;
	}

	@Persistent(mappedBy = "parentBatch")
    public List<ProductItem> getProductItem() {
        if (productItem == null) {
            productItem = new ArrayList<ProductItem>();
        }
        return this.productItem;
    }

	public void setProductItem(List<ProductItem> productItem) {
		this.productItem = productItem;
	}

	public void addComponent(BatchComponent component) {
		if(component == null)
			return;
		getComponents().add(component);
	}
	
 
}
