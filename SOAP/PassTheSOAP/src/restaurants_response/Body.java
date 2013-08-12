
package restaurants_response;

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
 *         &lt;element ref="{http://localhost:8080/restaurants}getAllRestaurantsResponse"/>
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
    "getAllRestaurantsResponse"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected GetAllRestaurantsResponse getAllRestaurantsResponse;

    /**
     * Gets the value of the getAllRestaurantsResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GetAllRestaurantsResponse }
     *     
     */
    public GetAllRestaurantsResponse getGetAllRestaurantsResponse() {
        return getAllRestaurantsResponse;
    }

    /**
     * Sets the value of the getAllRestaurantsResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAllRestaurantsResponse }
     *     
     */
    public void setGetAllRestaurantsResponse(GetAllRestaurantsResponse value) {
        this.getAllRestaurantsResponse = value;
    }

}
