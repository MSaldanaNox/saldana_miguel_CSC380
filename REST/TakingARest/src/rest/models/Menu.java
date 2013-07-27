package rest.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "menu")
public class Menu {

	@XmlElement(name = "menuitem", required = true)
	private List<MenuItem> menuItems;
	
	public Menu()
	{
		menuItems = new ArrayList<MenuItem>();
	}
	
	public List<MenuItem> getMenuItems()
	{
		if(menuItems == null)
		{
			menuItems = new ArrayList<MenuItem>();
		}
		return this.menuItems;
	}
}
