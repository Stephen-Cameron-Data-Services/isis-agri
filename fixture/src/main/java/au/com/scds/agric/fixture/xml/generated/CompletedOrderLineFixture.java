//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.26 at 09:32:07 AM AEST 
//


package au.com.scds.agric.fixture.xml.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CompletedOrderLineFixture complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompletedOrderLineFixture">
 *   &lt;complexContent>
 *     &lt;extension base="{http://scds.com.au/acric/fixture/xml/generated}ScheduledOrderLineFixture">
 *       &lt;sequence>
 *         &lt;element name="completed" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="completed-by" type="{http://scds.com.au/acric/fixture/xml/generated}PersonFixture"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompletedOrderLineFixture", propOrder = {
    "completed",
    "completedBy"
})
public class CompletedOrderLineFixture
    extends ScheduledOrderLineFixture
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar completed;
    @XmlElement(name = "completed-by", required = true)
    protected PersonFixture completedBy;

    /**
     * Gets the value of the completed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompleted() {
        return completed;
    }

    /**
     * Sets the value of the completed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompleted(XMLGregorianCalendar value) {
        this.completed = value;
    }

    /**
     * Gets the value of the completedBy property.
     * 
     * @return
     *     possible object is
     *     {@link PersonFixture }
     *     
     */
    public PersonFixture getCompletedBy() {
        return completedBy;
    }

    /**
     * Sets the value of the completedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonFixture }
     *     
     */
    public void setCompletedBy(PersonFixture value) {
        this.completedBy = value;
    }

}