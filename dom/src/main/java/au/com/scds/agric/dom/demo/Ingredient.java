
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Property;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ingredient", propOrder = { "batchComponent" })
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Ingredient {
	
	@XmlElement(name = "name", required = true)
	protected String name;
	@XmlElement(name = "batch-component", required = true)
	protected List<BatchComponent> batchComponents;
	protected List<Supply> supplies;
	
	@Column(allowsNull="false")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Persistent(mappedBy = "parentIngredient")
	public List<BatchComponent> getBatchComponents() {
		if (batchComponents == null) {
			batchComponents = new ArrayList<BatchComponent>();
		}
		return this.batchComponents;
	}

	public void setBatchComponents(List<BatchComponent> batchComponents) {
		this.batchComponents = batchComponents;
	}

	@Persistent(mappedBy = "parentIngredient")
	private List<Supply> getSupplies() {
		if (supplies == null) {
			supplies = new ArrayList<Supply>();
		}
		return this.supplies;
	}

	private void setSupplies(List<Supply> supplies) {
		this.supplies = supplies;
	}

	@NotPersistent
	private List<Supply> getAvailableSupplies() {
		return getSupplies();
	}

	@NotPersistent
	public List<Supply> getUsedSupplies() {
		return getSupplies();
	}
	
	@NotPersistent
	public List<Supply> getOrderedSupplies() {
		return getSupplies();
	}

}
