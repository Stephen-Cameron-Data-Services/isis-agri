package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.Order;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Client", menuOrder = "100")
public class ClientMenu100 {
	
	@Action()
	@MemberOrder(sequence = "1")
	public Client createClient(String name) {
		return clientRepo.createClient(name);
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public List<Client> listAll() {
		return clientRepo.listAll();
	}
	
	@Action()
	@MemberOrder(sequence = "10")
	public Order createOrder(Client client) {
		return clientRepo.createOrder(client);
	}

	@Inject
	ClientRepository clientRepo;


}
