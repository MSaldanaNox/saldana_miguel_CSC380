package reflectclasses;

public class MathLogic {

	private static MathLogic mL;
	
	private MathLogic()
	{
		
	}
	
	public static MathLogic getInstance()
	{
		if(mL == null)
		{
			mL = new MathLogic();
		}
		return mL;
	}
	
	static int add(int f, int s) {
		return f+s;
	}

	static int subtract(int f, int s) {		
		return f-s;
	}
}
