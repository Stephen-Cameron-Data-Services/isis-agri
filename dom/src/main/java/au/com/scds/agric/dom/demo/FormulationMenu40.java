/*
MIT License

Copyright (c) 2017 Alexander Stephen Cameron (http://stephencamerondataservices.com.au/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

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
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.Specification;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(named = "Formulations", menuOrder = "40")
public class FormulationMenu40 {

	@Action()
	@MemberOrder(sequence = "1")
	public Formulation createFormulation(String name) {
		return formulationRepo.createFormulation(name);
	}

	@Action()
	@MemberOrder(sequence = "2")
	public Formulation copyFormulation(Formulation copied, String name) {
		return null;
	}

	@Action()
	@MemberOrder(sequence = "3")
	public List<Formulation> listAllFormulations() {
		return formulationRepo.listAll();
	}
	
	@Action()
	@MemberOrder(sequence = "10")
	public FormulationMethod createFormulationMethod(String name) {
		return formulationRepo.createFormulationMethod(name);
	}
	
	@Action()
	@MemberOrder(sequence = "50")
	public Specification createSpecification(String name) {
		return specificationRepo.createSpecification(name);
	}
	
	@Action()
	@MemberOrder(sequence = "51")
	public Specification copySpecification(Specification copied, String name) {
		return null;
	}

	@Action()
	@MemberOrder(sequence = "52")
	public List<Specification> listAllSpecifications() {
		return specificationRepo.listAll();
	}

	@Inject
	FormulationRepository formulationRepo;

	@Inject
	SpecificationRepository specificationRepo;





}
