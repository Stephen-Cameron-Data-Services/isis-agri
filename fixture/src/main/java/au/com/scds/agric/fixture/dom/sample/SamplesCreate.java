package au.com.scds.agric.fixture.dom.sample;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import com.google.common.base.Supplier;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.BatchMixin;
import au.com.scds.agric.dom.demo.BatchSampleMenu92;
import au.com.scds.agric.dom.demo.FormulationMenu80;
import au.com.scds.agric.dom.demo.IngredientMenu81;
import au.com.scds.agric.dom.demo.PersonMenu91;
import au.com.scds.agric.dom.demo.ResultRepository;
import au.com.scds.agric.dom.demo.SampleMixin;
import au.com.scds.agric.dom.demo.SiUnitRepository;
import au.com.scds.agric.dom.demo.SupplierMenu96;
import au.com.scds.agric.dom.demo.TestRepository;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.BatchComponent;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Person;
import au.com.scds.agric.dom.demo.data.Batches;
import au.com.scds.agric.dom.demo.data.Formulations;
import au.com.scds.agric.dom.demo.data.ProductItem;
import au.com.scds.agric.dom.demo.data.Result;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Sample2;
import au.com.scds.agric.dom.demo.data.Samples;
import au.com.scds.agric.dom.demo.data.SiUnit;
import au.com.scds.agric.dom.demo.data.Specification;
import au.com.scds.agric.dom.demo.data.TestGroup;
import au.com.scds.agric.dom.demo.data.TestSingle;
import au.com.scds.agric.dom.demo.data.TestSuite;
import au.com.scds.agric.dom.demo.data.Ingredient;
import au.com.scds.agric.dom.demo.data.IngredientManufacturer;
import au.com.scds.agric.dom.demo.data.IngredientSupplier;
import au.com.scds.agric.dom.demo.data.IngredientSupply;

public class SamplesCreate extends FixtureScript{

	public SamplesCreate() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Sample sample = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			// create and persist equivalent objects via menus.
			InputStream is = this.getClass().getResourceAsStream("/au/com/scds/agric/fixture/dom/samples.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Samples _samples = (Samples) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for(Sample2 _sample : _samples.getSample() )
			{
				//dummy batch to 'sample'
				Batch batch = wrap(batchMenu).createBatch();
				Sample sample = wrap(sampleMenu).createBatchSample(batch);
				SampleMixin smx = new SampleMixin(sample);
				for(TestSingle _test : _sample.getTests()){
					TestSingle test = testRepo.createTestSingle(_test.getTestName());
					wrap(smx).addTestSingle(test);
					wrap(smx).createResult(test, _test.getResult().getTestResult());
				}
				for(TestGroup _test : _sample.getTestGroups()){
					TestGroup test = testRepo.createTestGroup(_test.getTestName());
					wrap(smx).addTestGroup(test);
					//wrap(smx).createResult(test, _test.getResult().getTestResult());
				}
				for(TestSuite _test : _sample.getTestSuites()){
					TestSuite test = testRepo.createTestSuite(_test.getTestName());
					wrap(smx).addTestSuite(test);
					//wrap(smx).createResult(test, _test.getResult().getTestResult());
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**				 		
	<?xml version="1.0" encoding="UTF-8"?>
	<tns:samples xmlns:tns="http://www.example.org/AgricProducerSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/AgricProducerSchema AgricProducerSchema.xsd ">
	  <tns:sample>
	    <tns:batch xsi:type="tns:Batch"/>
	    <tns:test>
	      <tns:test-name>tns:test-name</tns:test-name>
	      <tns:result>
	        <tns:test-result>tns:test-result</tns:test-result>
	      </tns:result>
	    </tns:test>
	    <tns:test-group>
	      <tns:test-name>tns:test-name</tns:test-name>
	      <tns:test>
	        <tns:test-name>tns:test-name</tns:test-name>
	        <tns:result>
	          <tns:test-result>tns:test-result</tns:test-result>
	        </tns:result>
	      </tns:test>
	      <tns:test-group/>
	    </tns:test-group>
	    <tns:test-suite>
	      <tns:test-name>tns:test-name</tns:test-name>
	      <tns:test>
	        <tns:test-name>tns:test-name</tns:test-name>
	        <tns:result>
	          <tns:test-result>tns:test-result</tns:test-result>
	        </tns:result>
	      </tns:test>
	      <tns:test-group>
	        <tns:test-name>tns:test-name</tns:test-name>
	        <tns:test>
	          <tns:test-name>tns:test-name</tns:test-name>
	          <tns:result>
	            <tns:test-result>tns:test-result</tns:test-result>
	          </tns:result>
	        </tns:test>
	        <tns:test-group/>
	      </tns:test-group>
	    </tns:test-suite>
	  </tns:sample>
	</tns:samples>
	*/
		
	public Sample getBatch() {
		return this.sample;
	}

	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private BatchSampleMenu92 sampleMenu;
	@javax.inject.Inject
	private TestRepository testRepo;
	@javax.inject.Inject
	private SiUnitRepository unitRepo;
}
