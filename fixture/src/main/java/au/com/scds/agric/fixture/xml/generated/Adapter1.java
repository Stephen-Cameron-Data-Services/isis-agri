//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.31 at 07:50:58 AM AEDT 
//


package au.com.scds.agric.fixture.xml.generated;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (au.com.scds.agric.fixture.xml.DataTypeAdapter.parseDateTime(value));
    }

    public String marshal(Date value) {
        return (au.com.scds.agric.fixture.xml.DataTypeAdapter.printDateTime(value));
    }

}
