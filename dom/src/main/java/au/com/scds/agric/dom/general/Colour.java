package au.com.scds.agric.dom.general;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.DomainObject;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
@Discriminator(value = "COLOUR")
@Query(name = "findByName", language = "JDOQL", 
	value = "SELECT FROM au.com.scds.agric.dom.general.Colour "
			+ "WHERE name == :name")
@DomainObject(objectType = "COLOUR")
public class Colour extends Name {

	// needed by Isis?
	public Colour() {
		super();
	}

	public Colour(String name) {
		super(name);
	}
}
