
package restaurants_request;

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
 *         &lt;element ref="{http://localhost:8080/restaurants}getAllRestaurants"/>
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
    "getAllRestaurants"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected GetAllRestaurants getAllRestaurants;

    /**
     * Gets the value of the getAllRestaurants property.
     * 
     * @return
     *     possible object is
     *     {@link GetAllRestaurants }
     *     
     */
    public GetAllRestaurants getGetAllRestaurants() {
        return getAllRestaurants;
    }

    /**
     * Sets the value of the getAllRestaurants property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAllRestaurants }
     *     
     */
    public void setGetAllRestaurants(GetAllRestaurants value) {
        this.getAllRestaurants = value;
    }

}
