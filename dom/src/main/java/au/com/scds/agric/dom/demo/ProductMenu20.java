package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Product", menuOrder = "30")
public class ProductMenu20 {

	@Action()
	@MemberOrder(sequence = "1")
	public ProductLine createProductLine(String name, ProductType type) {
		return productLineRepo.createProductLine(producerRepo.currentProducer() ,name, type);
	}
	
	public List<ProductType> choices1CreateProductLine(){
		return productLineRepo.listAllProductTypes();
	}
	
	@Action()
	@MemberOrder(sequence = "2")
	public ProductType createProductType(String name) {
		return productLineRepo.createProductType(name);
	}
	
	@javax.inject.Inject
	ProducerRepository producerRepo;

	@javax.inject.Inject
	ProductLineRepository productLineRepo;


}
