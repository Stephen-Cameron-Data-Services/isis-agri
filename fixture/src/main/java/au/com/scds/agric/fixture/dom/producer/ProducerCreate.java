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
import au.com.scds.agric.dom.demo.FormulationMenu40;
import au.com.scds.agric.dom.demo.PersonMenu210;
import au.com.scds.agric.dom.demo.ProducerMenu10;
import au.com.scds.agric.dom.demo.ProductLineMenu20;
import au.com.scds.agric.dom.demo.ProductTypeMenu21;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Producer;
import au.com.scds.agric.dom.demo.data.ProductLine;
import au.com.scds.agric.dom.demo.data.ProductType;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

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
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Producer pr = (Producer) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			producer = wrap(producerMenu).create(pr.getName());
			wrap(producer).setName(pr.getName());
			for(ProductLine _line : pr.getProductLines()){
				ProductType type = wrap(productTypeMenu).createProductType(_line.getProductType().getName());
				ProductLine line = wrap(productLineMenu).createProductLine(_line.getName(), type);
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
	private ProductLineMenu20 productLineMenu;
	@javax.inject.Inject
	private ProductTypeMenu21 productTypeMenu;
	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private PersonMenu210 personMenu;
	@javax.inject.Inject
	private FormulationMenu40 formulationMenu;
}
