/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package au.com.scds.agric.fixture.dom.producer;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.BatchMixin;
import au.com.scds.agric.dom.demo.FormulationMenu80;
import au.com.scds.agric.dom.demo.FormulationMixin;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.ProductMenu20;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.Formulation;
import au.com.scds.agric.dom.demo.data.FormulationComponent;
import au.com.scds.agric.dom.demo.data.FormulationMethod;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;
import au.com.scds.agric.dom.simple.SimpleObjectMenu;

import java.io.InputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import com.google.common.collect.Lists;

public class ProducerCreate extends FixtureScript {

	public ProducerCreate() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Producer producer = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			//import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/Producer.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Producer pr = ((JAXBElement<Producer>) jaxbUnmarshaller.unmarshal(is)).getValue();
			producer = wrap(producerMenu).create(pr.getName());
			ProductLine pl = pr.getProductLines().get(0);
			ProductType productType = wrap(productMenu).createProductType(pl.getProductType().getName());
			ProductLine productLine = wrap(productMenu).createProductLine(pl.getName(), productType);
			Batch ba = pl.getBatch().get(0);
			Batch batch = wrap(batchMenu).create(productLine);
			Person p1 = ba.getCreatedBy();
			Person p2 = ba.getCompletedBy();
			Person creator = wrap(personMenu).create(p1.getFirstName(),p1.getLastName());
			Person completor = wrap(personMenu).create(p2.getFirstName(),p2.getLastName());
			wrap(batch).setCreatedBy(creator);
			wrap(batch).setCompletedBy(completor);
			wrap(batch).setCreatedOn(ba.getCreatedOn());
			wrap(batch).setScheduledFor(ba.getScheduledFor());
			wrap(batch).setCompletedOn(ba.getCompletedOn());
			Formulation fo = ba.getFormulation();
			Formulation formulation = wrap(formulationMenu).create(fo.getName());
			wrap(formulation).setDescription(fo.getDescription());
			FormulationMethod fme = fo.getMethod();
			FormulationMixin fmx = new FormulationMixin(formulation);
			FormulationMethod formulationMethod = wrap(fmx).createMethod();
			FormulationComponent fmc = fo.getComponents().get(0);
			Ingredient ingredient = wrap(fmx).createComponentIngredient(fmc.getIngredient().getName(), fmc.getQuantity(), fmc.getUnit());
			wrap(ingredient).setName(fmc.getIngredient().getName());
			wrap(ingredient).setDescription(fmc.getIngredient().getDescription());
			wrap(ingredient).setSpecification(fmc.getIngredient().getSpecification());
			ProductItem pri = ba.getProductItems().get(0);
			BatchMixin bmx = new BatchMixin(batch);
			wrap(bmx).createProductItem(pri.getSerialNumber());
			/*
			 * <?xml version="1.0" encoding="UTF-8"?>
<tns:producer xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
  <tns:name>tns:name</tns:name>
  <tns:product-line>
    <tns:name>tns:name</tns:name>
    <tns:product-type>
      <tns:name>tns:name</tns:name>
    </tns:product-type>
    <tns:batch>
      <tns:created-on>2001-12-31T12:00:00</tns:created-on>
      <tns:created-by>
        <tns:first-name>tns:first-name</tns:first-name>
        <tns:last-name>tns:last-name</tns:last-name>
      </tns:created-by>
      <tns:scheduled-for>2001-12-31T12:00:00</tns:scheduled-for>
      <tns:completed-on>2001-12-31T12:00:00</tns:completed-on>
      <tns:completed-by>
        <tns:first-name>tns:first-name</tns:first-name>
        <tns:last-name>tns:last-name</tns:last-name>
      </tns:completed-by>
      <tns:formulation>
        <tns:name>tns:name</tns:name>
        <tns:description>tns:description</tns:description>
        <tns:method>
          <tns:description>tns:description</tns:description>
          <tns:step>
            <tns:description>tns:description</tns:description>
            <tns:order>0</tns:order>
          </tns:step>
        </tns:method>
        <tns:component>
          <tns:ingredient>
            <tns:name>tns:name</tns:name>
            <tns:description>tns:description</tns:description>
            <tns:specification>tns:specification</tns:specification>
          </tns:ingredient>
          <tns:quantity>tns:quantity</tns:quantity>
          <tns:unit>tns:unit</tns:unit>
        </tns:component>
      </tns:formulation>
      <tns:product-item>
        <tns:serial-number>tns:serial-number</tns:serial-number>
      </tns:product-item>
    </tns:batch>
  </tns:product-line>
</tns:producer>
			 * 
			 */
					/*
			 * for (BatchFixture f : BatchsFixture.getBatch()) { Batch batch =
			 * wrap(batchesRepo).createBatch();
			 * wrap(batch).setCreatedOn(f.getCreatedOn().toGregorianCalendar().
			 * getTime()); Person person =
			 * wrap(personsRepo).createPerson(f.getCreatedBy().getFirstname(),f.
			 * getCreatedBy().getLastname());
			 * wrap(person).setFirstName(f.getCreatedBy().getFirstname());
			 * wrap(person).setLastName(f.getCreatedBy().getLastname());
			 * wrap(batch).setCreatedBy(person);
			 * wrap(batch).setScheduledFor(f.getScheduledFor().
			 * toGregorianCalendar().getTime());
			 * wrap(batch).setCompletedOn(f.getCompletedOn().toGregorianCalendar
			 * ().getTime()); Person person2 =
			 * wrap(personsRepo).createPerson(f.getCompletedBy().getFirstname(),
			 * f.getCompletedBy().getLastname());
			 * wrap(batch).setCompletedBy(person2); Formulation formulation =
			 * wrap(formulationsRepo).createFormulation(f.getFormulation().
			 * getName()); wrap(formulation).setDescription(f.getFormulation().
			 * getDescription()); wrap(batch).setFormulation(formulation); for
			 * (IngredientFixture i : f.getFormulation().getIngredient()) {
			 * Ingredient ingredient =
			 * wrap(formulationsRepo).createIngredient(i.getName());
			 * wrap(formulation).addIngredient(ingredient,i.getQuantity(),i.
			 * getUnit()); } for (BatchComponentFixture componentFixture :
			 * f.getComponent()) { BatchComponent component =
			 * wrap(batchesRepo).createComponent();
			 * wrap(component).setIngredient(wrap(formulationsRepo).
			 * createIngredient(componentFixture.getIngredient().getName()));
			 * Supply supply =
			 * wrap(suppliersRepo).createSupply(wrap(component).getIngredient(),
			 * wrap(suppliersRepo).createSupplier(componentFixture.getSupplier()
			 * .getName())); wrap(component).setSupply(supply);
			 * wrap(batch).addComponent(component); } for (SampleFixture s :
			 * f.getSample()) { //Sample sample =
			 * wrap(samplesRepo).createSample(batch);
			 * wrap(batch).createSample(); } batches.add(batch); }
			 */
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Producer getProducer() {
		return this.producer;
	}
	
    @javax.inject.Inject
    private ProducerMenu10 producerMenu;
    @javax.inject.Inject
    private ProductMenu20 productMenu;
    @javax.inject.Inject
    private BatchMenu30 batchMenu;
    @javax.inject.Inject
    private PersonMenu91 personMenu;
    @javax.inject.Inject
    private FormulationMenu80 formulationMenu;
}
