
package menu_response;

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
 *         &lt;element ref="{http://localhost:8080/restaurants}getMenuItemsResponse"/>
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
    "getMenuItemsResponse"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected GetMenuItemsResponse getMenuItemsResponse;

    /**
     * Gets the value of the getMenuItemsResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GetMenuItemsResponse }
     *     
     */
    public GetMenuItemsResponse getGetMenuItemsResponse() {
        return getMenuItemsResponse;
    }

    /**
     * Sets the value of the getMenuItemsResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMenuItemsResponse }
     *     
     */
    public void setGetMenuItemsResponse(GetMenuItemsResponse value) {
        this.getMenuItemsResponse = value;
    }

}
