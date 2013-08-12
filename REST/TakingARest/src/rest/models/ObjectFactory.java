package rest.models;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Restaurant createRestaurant() {
		return new Restaurant();
	}

	public Menu createMenu() {
		return new Menu();
	}

	public MenuItem createMenuItem() {
		return new MenuItem();
	}
}
