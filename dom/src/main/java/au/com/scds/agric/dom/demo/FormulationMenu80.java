package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;

import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.ProductLine;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Formulations", menuOrder = "80")
public class FormulationMenu80 {

	@Action()
	@MemberOrder(sequence = "1")
	public Formulation create(String name) {
		return formulationRepo.createFormulation(name);
	}
	
	public List<ProductLine> choices0Create(){
		return productLineRepo.listAll();
	}

	@Inject
	FormulationRepository formulationRepo;
	
	@Inject
	ProductLineRepository productLineRepo;
}


