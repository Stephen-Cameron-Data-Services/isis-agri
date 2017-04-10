package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.Programmatic;

import au.com.scds.agric.dom.demo.data.Result;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Sampled;
import au.com.scds.agric.dom.demo.data.Test;
import au.com.scds.agric.dom.demo.data.TestGroup;
import au.com.scds.agric.dom.demo.data.TestMultiple;
import au.com.scds.agric.dom.demo.data.TestSingle;
import au.com.scds.agric.dom.demo.data.TestSuite;

@Mixin
public class SampleMixin {

	private final Sample sample;

	public SampleMixin(Sample sample) {
		this.sample = sample;
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ASSOCIATION)
	public void addTestSingle(TestSingle test, Integer multiples) {
		TestMultiple multiple = testRepo.createTestMultiple(this.sample, test, multiples);
		this.sample.getTests().add(multiple);
		createResultsForTestMultiple(multiple);
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ASSOCIATION)
	public void addTestGroup(TestGroup group, Integer multiples) {
		TestMultiple multiple = testRepo.createTestMultiple(this.sample, group, multiples);
		this.sample.getTests().add(multiple);
		createResultsForTestMultiple(multiple);
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ASSOCIATION)
	public void addTestSuite(TestSuite suite, Integer multiples) {
		TestMultiple multiple = testRepo.createTestMultiple(this.sample, suite, multiples);
		this.sample.getTests().add(multiple);
		createResultsForTestMultiple(multiple);
	}

	@Programmatic
	private void createResultsForTestMultiple(TestMultiple multiple) {
		Test test = multiple.getTest();
		if (test instanceof TestSingle) {
			for (int i = 0; i < multiple.getMultiple(); i++) {
				Result result = resultRepo.createResult(this.sample, (TestSingle) test, null);
				multiple.getResults().add(result);
			}
		} else if (test instanceof TestGroup) {
			for (int i = 0; i < multiple.getMultiple(); i++) {
				List<Result> results = createResultsForTestGroup((TestGroup) test);
				multiple.getResults().addAll(results);
			}
		} else if (test instanceof TestSuite) {
			for (int i = 0; i < multiple.getMultiple(); i++) {
				List<Result> results = createResultsForTestSuite((TestSuite) test);
				multiple.getResults().addAll(results);
			}
		}
	}

	@Programmatic
	private List<Result> createResultsForTestSuite(TestSuite suite) {
		if (suite == null)
			return null;
		List<Result> list = new ArrayList<>();
		for (Test test : suite.getTests()) {
			if (test instanceof TestSingle) {
				Result result = resultRepo.createResult(this.sample, (TestSingle) test, null);
				list.add(result);
			} else if (test instanceof TestGroup) {
				List<Result> results = createResultsForTestGroup((TestGroup) test);
				list.addAll(results);
			}
		}
		return list;
	}

	@Programmatic
	private List<Result> createResultsForTestGroup(TestGroup group) {
		if (group == null)
			return null;
		List<Result> list = new ArrayList<>();
		for (Test test : group.getTests()) {
			Result result = resultRepo.createResult(this.sample, (TestSingle) test, null);
			list.add(result);
		}
		return list;
	}

	@javax.inject.Inject
	private ResultRepository resultRepo;
	@javax.inject.Inject
	private TestRepository testRepo;

}
