package edu.neumont.csc380;

public class WordLogic {

	private static WordLogic wl;
	
	private WordLogic()
	{
		
	}
	
	public static WordLogic getInstance()
	{
		if(wl == null)
		{
			wl = new WordLogic();
		}
		
		return wl;
	}
	
	static String returnWord(String s)
	{
		return s;
	}
	
	static String combineWords(String s, String r)
	{
		return s + " and " + r;
	}
	
}
