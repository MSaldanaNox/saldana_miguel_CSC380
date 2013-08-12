package fds.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Client c = new Client();
		c.connectClient();
	}
	public void connectClient()
	{
		
		Socket server = null;
		PrintWriter serverOut = null;
		BufferedReader serverIn = null;
		
		try {
			server = new Socket("localhost",8080);
			serverOut = new PrintWriter(server.getOutputStream(),true);
			serverIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
			ObjectInputStream ios = new ObjectInputStream(server.getInputStream());
			String[] classes =(String[]) ios.readObject();
			
			String className = chooseClass(classes);
			serverOut.write(className + "\n");
			serverOut.flush();
			
			Class c = (Class)ios.readObject();
			Method m = getUserInputReflection(c);
			String params = getParamInput(m);		
			serverOut.write( m.getName()+ ";" + params + "\n");
			serverOut.flush();
		
			System.out.println(serverIn.readLine());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public String chooseClass(String[] classes){
		boolean invalid = true;
		int index = -1;
		try{
		while(invalid){
		int count = 0;
		System.out.println("Choose a class:");
		for(String cl : classes){
			System.out.println(count + " class name: " + cl);
			count++;
		}
		Scanner scan = new Scanner(System.in);
		index = scan.nextInt();
		invalid = false;
		}
		
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		return classes[index];
	}
	public String getParamInput(Method m) throws ClassNotFoundException{
		Scanner scan = new Scanner(System.in);
		String params = "";
		
		for(Class c : m.getParameterTypes()){
			boolean invalid = true;
			while(invalid){
				c = Class.forName(getPrimitiveType(c.getName()));
			System.out.println("Please enter a " + c.getName());
			String userInput = scan.nextLine();
			try{
				if(!c.getName().equals("java.lang.String")){
			Method method = c.getMethod("valueOf",String.class);
			params += method.invoke(null, userInput) + "-" + c.getName()+ ",";
				}
				else
				{
					params += userInput +"-" + c.getName() + ",";
				}
			invalid = false;
			}catch(Exception e){				
				System.out.println("Invalid Input");
				
			}
			
		}
			}
		return params;
		
	}
	 public String getPrimitiveType(String name)
	    {		
	        if (name.equals("byte")) return "java.lang.Byte";
	        if (name.equals("short")) return "java.lang.Short";
	        if (name.equals("int")) return "java.lang.Integer";
	        if (name.equals("long")) return "java.lang.Long";
	        if (name.equals("char")) return "java.lang.Character";
	        if (name.equals("float")) return "java.lang.Float";
	        if (name.equals("double")) return "java.lang.Double";
	        if (name.equals("boolean")) return "java.lang.Boolean";

	        return name;
	    }
	public Method getUserInputReflection(Class c){
		boolean isInt = false;
		Method[] methods = null;
		int userChoice = -1;
		Scanner scan = new Scanner(System.in);
		do{
		System.out.println("Choose a method to use based on number");		
		methods = c.getDeclaredMethods();
		int count = 0;
		String response = "";
		for(Method m : methods){
			boolean inside = false;
			response += count + " " +m.getName() + "(";	
			
				for(int i = 0; i < m.getParameterTypes().length; i++){
					inside = true;
					if(i == m.getParameterTypes().length-1){
						response += m.getParameterTypes()[i] + ")" + "\n";
					}
					else
					response += m.getParameterTypes()[i] + ",";
				}
				if(!inside){
					response += ")" + "\n";
				}
			count++;
		}
		System.out.println(response);
		try{
		userChoice = scan.nextInt();
		isInt = true;
		}
		catch(Exception e){
			
		}
	}while(!isInt);
		return methods[userChoice];
		
	}	
}
