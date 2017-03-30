package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Person", menuOrder = "91")
public class PersonMenu91 {

	@Action()
	@MemberOrder(sequence = "1")
	public Person createPerson(String firstname, String lastname) {
		return personRepo.createPerson(firstname, lastname);
	}

	@javax.inject.Inject
	PersonRepository personRepo;
}
