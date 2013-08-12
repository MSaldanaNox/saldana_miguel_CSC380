
package menu_request;

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
 *         &lt;element ref="{http://localhost:8080/restaurants}getMenuItems"/>
 *         &lt;element ref="{http://localhost:8080/restaurants}restaurant"/>
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
    "getMenuItems",
    "restaurant"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected GetMenuItems getMenuItems;
    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected Restaurant restaurant;

    /**
     * Gets the value of the getMenuItems property.
     * 
     * @return
     *     possible object is
     *     {@link GetMenuItems }
     *     
     */
    public GetMenuItems getGetMenuItems() {
        return getMenuItems;
    }

    /**
     * Sets the value of the getMenuItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMenuItems }
     *     
     */
    public void setGetMenuItems(GetMenuItems value) {
        this.getMenuItems = value;
    }

    /**
     * Gets the value of the restaurant property.
     * 
     * @return
     *     possible object is
     *     {@link Restaurant }
     *     
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets the value of the restaurant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Restaurant }
     *     
     */
    public void setRestaurant(Restaurant value) {
        this.restaurant = value;
    }

}
