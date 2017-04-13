package au.com.scds.agric.dom.demo;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;

import au.com.scds.agric.dom.demo.data.TestGroup;
import au.com.scds.agric.dom.demo.data.TestSingle;

@Mixin
public class TestGroupMixin {

	private TestGroup group;

	public TestGroupMixin(TestGroup group) {
		this.group = group;
	}
	
	@Action
	@ActionLayout(contributed = Contributed.AS_ACTION)
	public TestGroup addTestSingle(TestSingle test){
		this.group.getTests().add(test);
		return this.group;
	}
}
