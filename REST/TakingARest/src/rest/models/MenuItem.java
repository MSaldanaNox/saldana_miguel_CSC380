package rest.models;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "menuitem", propOrder = {
    "name",
    "price"
})
public class MenuItem {

	@XmlAttribute(name = "name", required = true)
	private String name;
	@XmlAttribute(name = "price", required = true)
	private double price;
	
	public String getName()
	{
		return this.name;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setPrice(double p)
	{
		price = p;
	}
}
