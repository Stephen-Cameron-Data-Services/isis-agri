package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Sale;
import au.com.scds.agric.dom.demo.data.SaleLine;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

public class SalesMixins {
	
	@Mixin
	public static class Sale_addSaleLine {

		private final Sale sale;

		public Sale_addSaleLine(Sale sale) {
			this.sale = sale;
		}

		@Action()
		@ActionLayout(contributed = Contributed.AS_ACTION)
		public SaleLine $$(ProductLine product) {
			SaleLine line = saleRepo.createSaleLine(this.sale, product);
			this.sale.getLines().add(line);
			return line;
		}

		@Inject
		SaleRepository saleRepo;
	}
}
