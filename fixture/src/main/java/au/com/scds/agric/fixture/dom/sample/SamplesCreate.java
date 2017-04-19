package au.com.scds.agric.fixture.dom.sample;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.com.scds.agric.dom.demo.BatchMenu30;
import au.com.scds.agric.dom.demo.BatchSampleMenu82;
import au.com.scds.agric.dom.demo.ProductLineMenu20;
import au.com.scds.agric.dom.demo.TestingMixins;
import au.com.scds.agric.dom.demo.SiUnitRepository;
import au.com.scds.agric.dom.demo.TestRepository;
import au.com.scds.agric.dom.demo.data.Batch;
import au.com.scds.agric.dom.demo.data.ObjectFactory;
import au.com.scds.agric.dom.demo.data.ProductLine;
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
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Samples _samples = (Samples) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (Sample2 _sample : _samples.getSample()) {
				// dummy batch to 'sample'
				ProductLine line = wrap(productLineMenu).createProductLine("dummy", null);
				Batch batch = wrap(batchMenu).createBatch(line);
				sample = wrap(sampleMenu).createBatchSample(batch);
				TestingMixins.Sample_addTestSingle addTestSingle = mixin(TestingMixins.Sample_addTestSingle.class,
						sample);
				TestingMixins.Sample_addTestGroup addTestGroup = mixin(TestingMixins.Sample_addTestGroup.class, sample);
				TestingMixins.Sample_addTestSuite addTestSuite = mixin(TestingMixins.Sample_addTestSuite.class, sample);
				for (TestSingle _test : _sample.getTestSingle()) {
					TestSingle test = testRepo.createTestSingle(_test.getTestName());
					wrap(addTestSingle).$$(test, 1);
				}
				for (TestGroup _test : _sample.getTestGroup()) {
					TestGroup testGroup = testRepo.createTestGroup(_test.getTestName());
					TestingMixins.TestGroup_addTestSingle tg_addTestSingle = mixin(
							TestingMixins.TestGroup_addTestSingle.class, testGroup);
					// TestGroupMixin tgx = new TestGroupMixin(testGroup);
					for (TestSingle _t : _test.getTests()) {
						TestSingle subtest = testRepo.createTestSingle(_test.getTestName());
						wrap(tg_addTestSingle).$$(subtest);
					}
					wrap(addTestGroup).$$(testGroup, 1);
				}
				for (TestSuite _test : _sample.getTestSuite()) {
					TestSuite testSuite = testRepo.createTestSuite(_test.getTestName());
					TestingMixins.TestSuite_addTestSingle ts_addTestSingle = mixin(
							TestingMixins.TestSuite_addTestSingle.class, testSuite);
					TestingMixins.TestSuite_addTestGroup ts_addTestGroup = mixin(
							TestingMixins.TestSuite_addTestGroup.class, testSuite);
					// TestSuiteMixin tsx = new TestSuiteMixin(testSuite);
					for (TestSingle _t : _test.getTests()) {
						TestSingle testSingle = testRepo.createTestSingle(_t.getTestName());
						wrap(ts_addTestSingle).$$(testSingle);
					}
					for (TestGroup _tg : _test.getTestGroups()) {
						TestGroup testGroup = testRepo.createTestGroup(_tg.getTestName());
						TestingMixins.TestGroup_addTestSingle tg_addTestSingle = mixin(
								TestingMixins.TestGroup_addTestSingle.class, testGroup);
						// TestGroupMixin tgx = new TestGroupMixin(testGroup);
						for (TestSingle _t : _tg.getTests()) {
							TestSingle subtest = testRepo.createTestSingle(_t.getTestName());
							wrap(tg_addTestSingle).$$(subtest);
						}
						wrap(ts_addTestGroup).$$(testGroup);
					}
					wrap(addTestSuite).$$(testSuite, 1);
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
	private ProductLineMenu20 productLineMenu;
	@javax.inject.Inject
	private BatchMenu30 batchMenu;
	@javax.inject.Inject
	private BatchSampleMenu82 sampleMenu;
	@javax.inject.Inject
	private TestRepository testRepo;
	@javax.inject.Inject
	private SiUnitRepository unitRepo;
}
