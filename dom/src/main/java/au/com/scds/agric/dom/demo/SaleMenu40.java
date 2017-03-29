package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import au.com.scds.agric.dom.demo.data.Sale;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Sale", menuOrder = "40")
public class SaleMenu40 {

	@Action()
	@MemberOrder(sequence = "1")
	public Sale createSale() {
		return saleRepo.createSale();
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public List<Sale> listAllSales() {
		return saleRepo.listAll();
	}
	
	
	@javax.inject.Inject
	ProducerRepository producerRepo;

	@javax.inject.Inject
	SaleRepository saleRepo;
}
