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
import au.com.scds.agric.dom.demo.FormulationMethodMixin;
import au.com.scds.agric.dom.demo.FormulationMixin;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.ProducerMixin;
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
			// import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/producer.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Producer pr = ((JAXBElement<Producer>) jaxbUnmarshaller.unmarshal(is)).getValue();
			producer = wrap(producerMenu).create(pr.getName());
			wrap(producer).setName(pr.getName());
			for(ProductLine _line : pr.getProductLines()){
				ProductType type = wrap(productMenu).createProductType(_line.getProductType().getName());
				ProductLine line = wrap(productMenu).createProductLine(_line.getName(), type);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
/**	
	<?xml version="1.0" encoding="UTF-8"?>
	<tns:producer 
	xmlns:tns="http://www.example.org/AgricProducerSchema" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
		<tns:name>tns:name</tns:name>
		<tns:product-line>
			<tns:name>tns:name</tns:name>
			<tns:product-type>
				<tns:name>tns:name</tns:name>
			</tns:product-type>
		</tns:product-line>
	</tns:producer>
*/
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
