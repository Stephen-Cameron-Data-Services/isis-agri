
package au.com.scds.agric.dom.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;

import au.com.scds.agric.dom.demo.Client;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FOrder", propOrder = {
    "taken",
    "takenBy",
    "orderLine",
    "scheduledOrderLine",
    "completedOrderLine"
})
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Order {
	
	protected Client client;

	@XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date taken;
    @XmlElement(name = "taken-by", required = true)
    protected Person takenBy;
    @XmlElement(name = "order-line", required = true)
    protected List<NewOrderLine> orderLines;
    @XmlElement(name = "scheduled-order-line")
    protected List<ScheduledOrderLine> scheduledOrderLines;
    @XmlElement(name = "completed-order-line")
    protected List<CompletedOrderLine> completedOrderLines;
    
    @Column(allowsNull="true")
    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

    @Column(allowsNull="true")
    public Date getTaken() {
        return taken;
    }

    public void setTaken(Date value) {
        this.taken = value;
    }

    @Column(allowsNull="true")
    public Person getTakenBy() {
        return takenBy;
    }

    public void setTakenBy(Person value) {
        this.takenBy = value;
    }

    @Persistent(mappedBy="parentOrder")
    public List<NewOrderLine> getNewOrderLines() {
        if (orderLines == null) {
            orderLines = new ArrayList<NewOrderLine>();
        }
        return this.orderLines;
    }
    
	public void setNewOrderLines(List<NewOrderLine> orderLine) {
		this.orderLines = orderLine;
	}

    @Persistent(mappedBy="parentOrder")
    public List<ScheduledOrderLine> getScheduledOrderLines() {
        if (scheduledOrderLines == null) {
            scheduledOrderLines = new ArrayList<ScheduledOrderLine>();
        }
        return this.scheduledOrderLines;
    }
    
	public void setScheduledOrderLines(List<ScheduledOrderLine> scheduledOrderLine) {
		this.scheduledOrderLines = scheduledOrderLine;
	}

    @Persistent(mappedBy="parentOrder")
    public List<CompletedOrderLine> getCompletedOrderLines() {
        if (completedOrderLines == null) {
            completedOrderLines = new ArrayList<CompletedOrderLine>();
        }
        return this.completedOrderLines;
    }

	public void setCompletedOrderLines(List<CompletedOrderLine> completedOrderLine) {
		this.completedOrderLines = completedOrderLine;
	}

	@Programmatic
	public void addOrderLine(NewOrderLine orderLine) {
		if(orderLine == null)
			return;
		getNewOrderLines().add(orderLine);
	}
}
