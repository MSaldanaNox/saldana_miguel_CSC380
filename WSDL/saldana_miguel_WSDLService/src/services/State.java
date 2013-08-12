package services;

public class State {
	private String fullName;
	private String twoDigitCode;
	
	public State(String name, String code)
	{
		fullName = name;
		twoDigitCode = code;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getTwoDigitCode() {
		return twoDigitCode;
	}
	public void setTwoDigitCode(String twoDigitCode) {
		this.twoDigitCode = twoDigitCode;
	}
}
