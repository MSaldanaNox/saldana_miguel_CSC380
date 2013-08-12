package fds.server;

import java.io.Serializable;

public class MathLogic implements ReflectionParent, Serializable {
	
	public MathLogic(){
		
	}
	public int add(int a, int b){
		System.out.println(a + " + " + b);
		return a + b;
	}
	public int subtract(int a, int b){
		System.out.println(a + " - " + b);
		return a - b;
	}
	

}
