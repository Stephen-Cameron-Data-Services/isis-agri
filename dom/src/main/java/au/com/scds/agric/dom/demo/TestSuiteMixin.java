package au.com.scds.agric.dom.demo;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.TestGroup;
import au.com.scds.agric.dom.demo.data.TestSingle;
import au.com.scds.agric.dom.demo.data.TestSuite;

@Mixin
public class TestSuiteMixin {
	
	private TestSuite suite;
	
	public TestSuiteMixin(TestSuite suite){
		this.suite = suite;
	}
	
	@Action
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public TestSuite addTestSingle(TestSingle test){
		this.suite.getTests().add(test);
		return this.suite;
	}
	
	@Action
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public TestSuite addTestGroup(TestGroup group){
		this.suite.getTestGroups().add(group);
		return this.suite;
	}
}
