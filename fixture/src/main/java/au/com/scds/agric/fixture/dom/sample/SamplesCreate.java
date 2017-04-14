package au.com.scds.agric.fixture.dom.sample;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.BatchSampleMenu82;
import au.com.scds.agric.dom.demo.SampleMixin;
import au.com.scds.agric.dom.demo.SiUnitRepository;
import au.com.scds.agric.dom.demo.TestGroupMixin;
import au.com.scds.agric.dom.demo.TestRepository;
import au.com.scds.agric.dom.demo.TestSuiteMixin;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Sample2;
import au.com.scds.agric.dom.demo.data.Samples;
import au.com.scds.agric.dom.demo.data.TestGroup;
import au.com.scds.agric.dom.demo.data.TestSingle;
import au.com.scds.agric.dom.demo.data.TestSuite;

public class SamplesCreate extends FixtureScript {

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
			for (Sample2 _sample : _samples.getSample()) {
				// dummy batch to 'sample'
				Batch batch = wrap(batchMenu).createBatch();
				sample = wrap(sampleMenu).createBatchSample(batch);
				SampleMixin sampleMixin = new SampleMixin(sample);
				for (TestSingle _test : _sample.getTestSingle()) {
					TestSingle test = testRepo.createTestSingle(_test.getTestName());
					wrap(sampleMixin).addTestSingle(test, 1);
				}
				for (TestGroup _test : _sample.getTestGroup()) {
					TestGroup testGroup = testRepo.createTestGroup(_test.getTestName());
					TestGroupMixin tgx = new TestGroupMixin(testGroup);
					for (TestSingle _t : _test.getTests()) {
						TestSingle subtest = testRepo.createTestSingle(_test.getTestName());
						wrap(tgx).addTestSingle(subtest);
					}
					wrap(sampleMixin).addTestGroup(testGroup, 1);
				}
				for (TestSuite _test : _sample.getTestSuite()) {
					TestSuite testSuite = testRepo.createTestSuite(_test.getTestName());
					TestSuiteMixin tsx = new TestSuiteMixin(testSuite);
					for (TestSingle _t : _test.getTests()) {
						TestSingle testSingle = testRepo.createTestSingle(_t.getTestName());
						wrap(tsx).addTestSingle(testSingle);
					}
					for (TestGroup _tg : _test.getTestGroups()) {
						TestGroup testGroup = testRepo.createTestGroup(_tg.getTestName());
						TestGroupMixin tgx = new TestGroupMixin(testGroup);
						for (TestSingle _t : _tg.getTests()) {
							TestSingle subtest = testRepo.createTestSingle(_t.getTestName());
							wrap(tgx).addTestSingle(subtest);
						}
						wrap(tsx).addTestGroup(testGroup);
					}
					wrap(sampleMixin).addTestSuite(testSuite, 1);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	
	
	 */

	public Sample getSample() {
		return this.sample;
	}

	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private BatchSampleMenu82 sampleMenu;
	@javax.inject.Inject
	private TestRepository testRepo;
	@javax.inject.Inject
	private SiUnitRepository unitRepo;
}
