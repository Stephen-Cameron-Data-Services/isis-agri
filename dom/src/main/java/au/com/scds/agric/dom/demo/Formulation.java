
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;

import au.com.scds.agric.dom.demo.FormulationComponent;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Formulation", propOrder = { "FormulationComponent" })
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Formulation {

	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	protected String description;
	@XmlElement(required = true)
	@Persistent(mappedBy = "parentFormulation")
	protected List<FormulationComponent> components;

	@Column(allowsNull = "true")
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	@Column(allowsNull = "true")
	public String getDescription() {
		return description;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public List<FormulationComponent> getComponents() {
		if (components == null) {
			components = new ArrayList<FormulationComponent>();
		}
		return this.components;
	}

	public void setComponents(List<FormulationComponent> formulationComponents) {
		this.components = formulationComponents;
	}

	@Action
	public void addIngredient(Ingredient ingredient, Double quantity, String units) {
		if (ingredient == null || quantity == null || units == null)
			return;
		formulationsRepo.createFormulationComponent(this, ingredient, quantity, units);
	}

	void addFormulationComponent(FormulationComponent component) {
		if (component == null)
			return;
		getComponents().add(component);
	}

	@Inject
	Formulations formulationsRepo;

}
