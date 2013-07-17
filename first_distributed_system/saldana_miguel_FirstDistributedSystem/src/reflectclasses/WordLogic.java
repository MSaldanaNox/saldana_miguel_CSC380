package reflectclasses;

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
	
	static int returnNumber(int s)
	{
		return s;
	}
	
	static int multiply(int s, int r)
	{
		return s*r;
	}
	
}
