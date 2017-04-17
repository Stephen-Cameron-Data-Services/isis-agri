package au.com.scds.agric.dom.demo;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientSupply;
import au.com.scds.agric.dom.demo.data.SiUnit;

public class IngredientMixins {
	
	@Mixin
	public static class Batch_addComponent{

		private final Batch batch;

		public Batch_addComponent(Batch batch) {
			this.batch = batch;
		}

		@Action()
		@ActionLayout(contributed = Contributed.AS_ACTION)
		public void $$(Ingredient ingredient, IngredientSupply supply, float quantity, SiUnit unit) {
			BatchComponent component = batchRepo.createComponent(this.batch, supply, ingredient, quantity, unit);
			this.batch.getBatchComponents().add(component);
			return;
		}

		@Inject
		BatchRepository batchRepo;
	}
}
