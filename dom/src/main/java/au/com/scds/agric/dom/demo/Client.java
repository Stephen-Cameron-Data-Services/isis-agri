
package au.com.scds.agric.dom.demo;

import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

import au.com.scds.agric.dom.demo.Order;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Client")
@DomainObject()
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Client
    extends Party
{
	protected List<Order> orders;

	@Persistent(mappedBy = "client")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		if(order == null)
			return;
		order.setClient(this);
		this.getOrders().add(order);	
	}

}
