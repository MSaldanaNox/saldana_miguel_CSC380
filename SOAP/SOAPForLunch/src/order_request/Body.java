
package order_request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://localhost:8080/restaurants}placeOrder"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "placeOrder"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected PlaceOrder placeOrder;

    /**
     * Gets the value of the placeOrder property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceOrder }
     *     
     */
    public PlaceOrder getPlaceOrder() {
        return placeOrder;
    }

    /**
     * Sets the value of the placeOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceOrder }
     *     
     */
    public void setPlaceOrder(PlaceOrder value) {
        this.placeOrder = value;
    }

}
