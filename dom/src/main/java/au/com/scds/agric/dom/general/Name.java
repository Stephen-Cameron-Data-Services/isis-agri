package au.com.scds.agric.dom.general;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import org.apache.isis.applib.annotation.DomainObject;

@DomainObject()
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.VALUE_MAP, column = "class", value = "NAME")
public abstract class Name {

	@PrimaryKey
	@Column(allowsNull = "false", length = 50)
	private String name;

	public Name() {
	}

	public Name(String name) {
		this.name = name;
	}

	public String title() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!this.getClass().isInstance(obj)) {
			return false;
		}
		return ((Name) obj).getName().equals(this.getName());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ":" + this.getName();
	}
}
