
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
 *         &lt;element ref="{http://localhost:8080/restaurants}restaurant"/>
 *         &lt;element ref="{http://localhost:8080/restaurants}menuItem"/>
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
    "restaurant",
    "menuItem"
})
@XmlRootElement(name = "placeOrder", namespace = "http://localhost:8080/restaurants")
public class PlaceOrder {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected Restaurant restaurant;
    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected MenuItem menuItem;

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

    /**
     * Gets the value of the menuItem property.
     * 
     * @return
     *     possible object is
     *     {@link MenuItem }
     *     
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Sets the value of the menuItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link MenuItem }
     *     
     */
    public void setMenuItem(MenuItem value) {
        this.menuItem = value;
    }

}
