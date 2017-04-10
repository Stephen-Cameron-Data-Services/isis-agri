package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Test;
import au.com.scds.agric.dom.demo.data.TestGroup;
import au.com.scds.agric.dom.demo.data.TestMultiple;
import au.com.scds.agric.dom.demo.data.TestSingle;
import au.com.scds.agric.dom.demo.data.TestSuite;

@DomainService(nature = NatureOfService.DOMAIN)
public class TestRepository {

	public TestSingle createTestSingle(String name) {
		if (name == null)
			return null;
		final TestSingle test = new TestSingle();
		test.setTestName(name);
		serviceRegistry.injectServicesInto(test);
		repositoryService.persistAndFlush(test);
		return test;
	}

	public List<TestSingle> listAllTestSingle() {
		return repositoryService.allInstances(TestSingle.class);
	}

	public TestSingle findTestSingleById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(TestSingle.class, "findById", "id", id));
	}

	public TestGroup createTestGroup(String name) {
		if (name == null)
			return null;
		final TestGroup test = new TestGroup();
		test.setTestName(name);
		serviceRegistry.injectServicesInto(test);
		repositoryService.persistAndFlush(test);
		return test;
	}

	public List<TestGroup> listAllTestGroup() {
		return repositoryService.allInstances(TestGroup.class);
	}

	public TestGroup findTestGroupById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(TestGroup.class, "findById", "id", id));
	}

	public TestSuite createTestSuite(String name) {
		if (name == null)
			return null;
		final TestSuite test = new TestSuite();
		test.setTestName(name);
		serviceRegistry.injectServicesInto(test);
		repositoryService.persistAndFlush(test);
		return test;
	}

	public List<TestSuite> listAllTestSuite() {
		return repositoryService.allInstances(TestSuite.class);
	}

	public TestSuite findTestSuiteById(final String id) {
		return repositoryService.firstMatch(new QueryDefault<>(TestSuite.class, "findById", "id", id));
	}

	public TestMultiple createTestMultiple(Sample sample, Test test, Integer multiples) {
		if (test == null || multiples == null)
			return null;
		final TestMultiple multiple = new TestMultiple();
		multiple.setSample(sample);
		multiple.setTest(test);
		multiple.setMultiple(multiples);
		serviceRegistry.injectServicesInto(multiple);
		repositoryService.persistAndFlush(multiple);
		return multiple;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}