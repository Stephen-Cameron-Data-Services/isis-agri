package au.com.scds.agric.dom.demo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import au.com.scds.agric.dom.demo.data.Sample;
import au.com.scds.agric.dom.demo.data.Sampled;

@Mixin
public class SampledMixin {

	private final Sampled sampled;

	public SampledMixin(Sampled sampled) {
		this.sampled = sampled;
	}
	
	@Action()
	@ActionLayout(contributed = Contributed.AS_ASSOCIATION)
	public List<Sample> getSamples() {
		return null;
	}
}
