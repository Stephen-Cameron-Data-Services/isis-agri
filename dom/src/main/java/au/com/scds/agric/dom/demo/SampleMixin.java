/*
MIT License

Copyright (c) 2017 Alexander Stephen Cameron (http://stephencamerondataservices.com.au/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureResult;

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
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public void addTestSingle(TestSingle test, Integer multiples) {
		TestMultiple multiple = testRepo.createTestMultiple(this.sample, test, multiples);
		this.sample.getTests().add(multiple);
		createResultsForTestMultiple(multiple);
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public void addTestGroup(TestGroup group, Integer multiples) {
		TestMultiple multiple = testRepo.createTestMultiple(this.sample, group, multiples);
		this.sample.getTests().add(multiple);
		createResultsForTestMultiple(multiple);
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public void addTestSuite(TestSuite suite, Integer multiples) {
		TestMultiple multiple = testRepo.createTestMultiple(this.sample, suite, multiples);
		this.sample.getTests().add(multiple);
		createResultsForTestMultiple(multiple);
	}

	@Action()
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public List<Result> getResults() {
		ArrayList<Result> list = new ArrayList<>();
		for (TestMultiple multiple : sample.getTests()) {
			list.addAll(multiple.getResults());
		}
		return list;
	}

	@Programmatic
	private void createResultsForTestMultiple(TestMultiple multiple) {
		Test test = multiple.getTest();
		if (test instanceof TestSingle) {
			for (int i = 0; i < multiple.getMultiple(); i++) {
				Result result = resultRepo.createResult(this.sample, multiple, (TestSingle) test, null);
				multiple.getResults().add(result);
			}
		} else if (test instanceof TestGroup) {
			for (int i = 0; i < multiple.getMultiple(); i++) {
				List<Result> results = createResultsForTestGroup(multiple, (TestGroup) test);
				multiple.getResults().addAll(results);
			}
		} else if (test instanceof TestSuite) {
			for (int i = 0; i < multiple.getMultiple(); i++) {
				List<Result> results = createResultsForTestSuite(multiple, (TestSuite) test);
				multiple.getResults().addAll(results);
			}
		}
	}

	@Programmatic
	private List<Result> createResultsForTestSuite(TestMultiple multiple, TestSuite suite) {
		List<Result> list = new ArrayList<>();
		if (suite == null)
			return list;
		for (TestSingle test : suite.getTests()) {
			Result result = resultRepo.createResult(this.sample, test, null);
			list.add(result);
		}
		for (TestGroup test : suite.getTestGroups()) {
			List<Result> results = createResultsForTestGroup(multiple, test);
			list.addAll(results);
		}
		return list;
	}

	@Programmatic
	private List<Result> createResultsForTestGroup(TestMultiple multiple, TestGroup group) {
		List<Result> list = new ArrayList<>();
		if (group == null)
			return list;
		for (TestSingle test : group.getTests()) {
			Result result = resultRepo.createResult(this.sample, test, null);
			list.add(result);
		}
		return list;
	}

	@javax.inject.Inject
	private ResultRepository resultRepo;
	@javax.inject.Inject
	private TestRepository testRepo;

}
