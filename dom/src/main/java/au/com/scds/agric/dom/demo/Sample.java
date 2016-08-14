
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sample", propOrder = {
    "result"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Sample {

	protected Sampled sampled;
	protected List<Result> results;
	
	@Column(allowsNull="false")
    public Sampled getSampled() {
		return sampled;
	}

	public void setSampled(Sampled sampled) {
		this.sampled = sampled;
	}

    @Persistent(mappedBy="sample")
    public List<Result> getResults() {
        if (results == null) {
            results = new ArrayList<Result>();
        }
        return this.results;
    }

	public void setResults(List<Result> result) {
		this.results = result;
	}

}
