package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import au.com.scds.agric.dom.demo.data.Client;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Client", menuOrder = "100")
public class ClientMenu100 {
	
	@Action()
	@MemberOrder(sequence = "1")
	public Client createClient(String name) {
		return clientRepo.create(name);
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public List<Client> listAll() {
		return clientRepo.listAll();
	}

	@Inject
	ClientRepository clientRepo;
}
