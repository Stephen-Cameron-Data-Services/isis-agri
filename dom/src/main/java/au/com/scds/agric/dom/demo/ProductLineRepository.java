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

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = ProductLine.class)
public class ProductLineRepository {

	public ProductLine createProductLine(final Producer producer, final String name, final ProductType type) {
		if (name == null)
			return null;
		final ProductLine productLine = new ProductLine();
		productLine.setProducer(producer);
		productLine.setName(name);
		productLine.setProductType(type);
		serviceRegistry.injectServicesInto(productLine);
		repositoryService.persistAndFlush(productLine);
		return productLine;
	}

	public List<ProductLine> listAllProductLine() {
		return repositoryService.allInstances(ProductLine.class);
	}

	public ProductLine findProductLineById(String id) {
		return repositoryService.firstMatch(new QueryDefault<>(ProductLine.class, "findById", "id", id));
	}

	public ProductType createProductType(String name) {
		final ProductType ProductType = new ProductType();
		ProductType.setName(name);
		serviceRegistry.injectServicesInto(ProductType);
		repositoryService.persistAndFlush(ProductType);
		return ProductType;
	}

	public List<ProductType> listAllProductTypes() {
		return repositoryService.allInstances(ProductType.class);
	}

	public ProductType findProductTypeById(String id) {
		return repositoryService.firstMatch(new QueryDefault<>(ProductType.class, "findById", "id", id));
	}

	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;
}
