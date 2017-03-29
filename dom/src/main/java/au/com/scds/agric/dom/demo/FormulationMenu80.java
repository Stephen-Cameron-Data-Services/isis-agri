package au.com.scds.agric.dom.demo;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.Specification;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Formulations", menuOrder = "80")
public class FormulationMenu80 {

	@Action()
	@MemberOrder(sequence = "1")
	public Formulation createFormulation(String name ) {
		return formulationRepo.createFormulation(name);
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public List<Formulation> listAllFormulations() {
		return formulationRepo.listAll();
	}
	
	@Action()
	@MemberOrder(sequence = "10")
	public Specification createSpecification(String name) {
		return specificationRepo.createSpecification(name);
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public List<Specification> listAllSpecifications() {
		return specificationRepo.listAll();
	}
	
	@Inject
	FormulationRepository formulationRepo;
	
	@Inject
	SpecificationRepository specificationRepo;


}


