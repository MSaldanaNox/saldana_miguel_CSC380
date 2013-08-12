package firstdistributionsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class MathClient {

	static List<String> param = new ArrayList<String>();
	static String toSend;
	
	public static void main(String[] args) {
		try {
			Socket server = new Socket("", 8080);
			OutputStream serverOut = server.getOutputStream();
			InputStream serverIn = server.getInputStream();
			PrintWriter serverWrite = new PrintWriter(serverOut, true);
			
			//Classes
			String classes = getClassesFromServer(serverIn);
			String prompt;
			do {
				prompt = JOptionPane.showInputDialog(classes);
			} while (prompt == null || prompt.isEmpty()
					|| !function(prompt, classes));
			serverWrite.println(prompt);
			serverWrite.flush();

			// Methods
			String theMethods = getMethodsFromServer(serverIn);
			do {
				prompt = JOptionPane.showInputDialog(theMethods);
			} while (prompt == null || prompt.isEmpty()
					|| !function(prompt, theMethods));
			String input = "";
			do {
				input = JOptionPane
						.showInputDialog("Please type in an appropriate amount of parameters of the same type as the method you chose"
								+ "\nPlease seperate each input with a comma, do not type in any spaces");
			} while (input == null || input.isEmpty());

			serverWrite.println(prompt + " " + input + " " + toSend);
			serverWrite.flush();
			server.shutdownOutput();

			printServerResponse(serverIn);

			server.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

	private static boolean function(String c, String s) {
		boolean isIn = false;
		String[] options = s.split("\\\n");
		int num = options.length;

		for (int i = 0; i < num; i++) {
			if (options[i].contains(c))
			{
				isIn = true;
				if(param.size() != 0)
					toSend = param.get(i-1);
			}
		}
		return isIn;
	}

	private static String getClassesFromServer(InputStream serverIn)
	{
		String resp = "";
		
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(
					serverIn));
			resp = read.readLine();
			
			String[] theClasses = resp.split(", ");
			
			resp = "The current classes are: \n";
			
			for(String s : theClasses)
			{
				String[] temp =  s.split("\\.");
				resp += temp[temp.length-1] + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resp;
	}
	
	private static String getMethodsFromServer(InputStream serverIn) {
		String resp = "";
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(
					serverIn));
			resp = read.readLine();
			String[] methods = resp.split(" ");
			List<String> names = new ArrayList<String>();
			List<String> params = new ArrayList<String>();
			for (int i = 2; i < methods.length; i += 3) {
				String[] methodName = methods[i].split("\\.");
				String[] name = methodName[2].split("\\(");
				names.add(name[0]);
				params.add(name[1].replaceAll("\\)", ""));
			}

			resp = "The following operations are available to you:";
			int i = 0;
			for (String s : names) {
					resp += "\nMethod: " + s + "        Parameters: " + params.get(i);
					param.add(params.get(i));
					i ++;
			}
			resp += "\nWhat would you like to do?";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}

	private static void printServerResponse(InputStream serverIn) {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(
					serverIn));
			String resp = read.readLine();
			JOptionPane.showMessageDialog(null, resp, "The server says:",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}