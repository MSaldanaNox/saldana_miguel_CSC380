package rest.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "library", propOrder = {
    "menu","name"})
public class Restaurant {

	@XmlElement(nillable = false, name = "menu", required = true)
	private Menu menu;
	@XmlAttribute(name = "name", required = true)
	private String restaurantname;
	
	
	public String getName()
	{
		return this.restaurantname;
	}
	
	public void setName(String n)
	{
		restaurantname = n;
	}
	
	public Menu getMenu()
	{     
		if (menu == null) {
            menu = new Menu();
        }
        return this.menu;
}
}
