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

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Client;
import au.com.scds.agric.dom.demo.data.Invoice;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.demo.data.Receipt;
import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.SaleLine;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Sale.class)
public class SaleRepository {

	public Sale createSale(Client client) {
		final Sale sale = new Sale();
		sale.setClient(client);
		serviceRegistry.injectServicesInto(sale);
		repositoryService.persistAndFlush(sale);
		return sale;
	}

	public List<Sale> listAll() {
		return repositoryService.allInstances(Sale.class);
	}

	public Sale findById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(Sale.class, "findById", "id", id));
	}
	
	public SaleLine createSaleLine(Sale sale, ProductLine product) {
		final SaleLine line = new SaleLine();
		line.setSale(sale);
		line.setProductLine(product);
		serviceRegistry.injectServicesInto(line);
		repositoryService.persistAndFlush(line);
		return line;
	}
	
	public Invoice createInvoice(Client client) {
		final Invoice invoice = new Invoice();
		invoice.setClient(client);
		serviceRegistry.injectServicesInto(invoice);
		repositoryService.persistAndFlush(invoice);
		return invoice;
	}

	public List<Invoice> listAllInvoices() {
		return repositoryService.allInstances(Invoice.class);
	}
	
	public Receipt createReceipt(Client client) {
		final Receipt receipt = new Receipt();
		receipt.setClient(client);
		serviceRegistry.injectServicesInto(receipt);
		repositoryService.persistAndFlush(receipt);
		return receipt;
	}

	public List<Receipt> listAllReceipts() {
		return repositoryService.allInstances(Receipt.class);
	}
	
	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;




}
