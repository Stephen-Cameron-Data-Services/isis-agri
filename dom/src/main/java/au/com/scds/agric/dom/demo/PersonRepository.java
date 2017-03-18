package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Person;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Person.class)
public class PersonRepository {

	public Person createPerson(String firstname, String lastname) {
		if(firstname == null || lastname==null)
			return null;
		final Person person = new Person();
		person.setFirstName(firstname);
		person.setLastName(lastname);
		serviceRegistry.injectServicesInto(person);
		repositoryService.persistAndFlush(person);
		return person;
	}
	
	public List<Person> listAll() {
		return repositoryService.allInstances(Person.class);
	}

	public List<Person> findById(final String id) {
		return repositoryService.allMatches(new QueryDefault<>(Person.class, "findById", "id", id));
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
