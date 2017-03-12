package au.com.scds.agric.dom.demo;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import au.com.scds.agric.dom.demo.data.Producer;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Producer", menuOrder = "10")
public class ProducerMenu10 {

	@Action()
	@MemberOrder(sequence = "1")
	public Producer create(String name) {
		return producerRepo.createProducer(name);
	}

	@javax.inject.Inject
	ProducerRepository producerRepo;
}