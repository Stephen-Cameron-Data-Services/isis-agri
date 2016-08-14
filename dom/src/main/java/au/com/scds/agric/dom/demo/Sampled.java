
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sampled", propOrder = {
    "sample"
})
@XmlSeeAlso({
    BatchComponent.class,
    Batch.class,
    Supply.class,
    ProductItem.class
})
@DomainObject()
@PersistenceCapable()
public abstract class Sampled {

    protected List<Sample> samples;

    @Persistent(mappedBy="sampled")
    public List<Sample> getSamples() {
        if (samples == null) {
            samples = new ArrayList<Sample>();
        }
        return this.samples;
    }

	public void setSamples(List<Sample> sample) {
		this.samples = sample;
	}
	
	@Action
	public Sample createSample(){
		Sample sample = samplesRepo.createSample(this);
		getSamples().add(sample);
		return sample;
	}
	
	@Inject 
	Samples samplesRepo;

}
