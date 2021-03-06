package edu.neumont.csc380;

public class ReflectionActivity {

	private int id;
	protected String name;
	public double number;

	public ReflectionActivity() {
		this.id = 1;
	}

	public ReflectionActivity(String name, double number) {
		this.id = 1;
		this.name = name;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double addToNumber(double number) {
		return this.number + number;
	}
}