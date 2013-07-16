package firstdistributionsystem;

import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.tools.*;

import reflectclasses.MathLogic;

public class MathServer implements Runnable {

	static ServerSocket serverSocket;
	Socket clientSocket;
	static MathServer server;

	public MathServer(Socket socket) {
		this.clientSocket = socket;
	}

	/**
	 * Miguel Saldana
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(8080);
			Socket socket;
			while (true) {
				socket = serverSocket.accept();

				server = new MathServer(socket);

				Thread thread = new Thread(server);
				thread.start();
			}
		} catch (Exception e) {
		}

	}

	private void sendClasses() {
		try {
			JavaCompiler jcompile = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = jcompile.getStandardFileManager(null, null, null);

			JavaFileManager.Location loc = StandardLocation.CLASS_PATH;
			String packageName = "reflectclasses";
			Set<JavaFileObject.Kind> kinds = new HashSet<JavaFileObject.Kind>();
			kinds.add(JavaFileObject.Kind.CLASS);
			boolean loop = false;

			Iterable<JavaFileObject> javaFiles;

			javaFiles = fileManager.list(loc, packageName, kinds, loop);

			List<Class> classes = new ArrayList<Class>();
			String cNames = "";
			String temp = "";
			for (JavaFileObject javaFileObject : javaFiles) {
				String cName = javaFileObject.getName();
				int lIndex = cName.lastIndexOf('/');
				int rIndex = cName.lastIndexOf('.');

				temp = cName.substring(lIndex + 1, rIndex);
				cNames += temp;
				cNames += " ";
				classes.add(Class.forName(packageName + "." + temp));
			}
			
//			PrintWriter serverWrite = new PrintWriter(clientSocket.getOutputStream(), true);
			System.out.println(cNames);
//			serverWrite.println(cNames);
//			serverWrite.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sendMethods() {
		PrintWriter serverWrite;
		try {
			Class<?> c;
			c = Class.forName("reflectclasses.MathLogic");
			serverWrite = new PrintWriter(clientSocket.getOutputStream(), true);
			serverWrite.println(printMethods(c.getDeclaredMethods()));
			serverWrite.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Utilized from java docs
	private static String printMethods(Member[] members) {

		String s = "";

		for (Member member : members) {
			if (member instanceof Method)
				s += ((Method) member).toString() + " ";
		}

		return s;
	}

	private String getTypeFromString(String convert) {
		String result = "";

		if (convert.equalsIgnoreCase("int"))
			result = "java.lang.Integer";
		if (convert.equalsIgnoreCase("string"))
			result = "java.lang.String";
		if (convert.equalsIgnoreCase("byte"))
			result = "java.lang.Byte";
		if (convert.equalsIgnoreCase("double"))
			result = "java.lang.Double";
		if (convert.equalsIgnoreCase("boolean"))
			result = "java.lang.Boolean";

		return result;
	}

	@Override
	public void run() {
		try {
			server.sendClasses();
			server.sendMethods();
			
			Scanner clientScan;
			clientScan = new Scanner(clientSocket.getInputStream());
			clientScan.useDelimiter("$");
			String[] res = clientScan.nextLine().split(" ");

			String func = res[0];
			String[] input = res[1].split(",");

			String[] params = res[2].split(",");
			String[] types = new String[params.length];
			int i = 0;
			for (String s : params) {
				types[i] = getTypeFromString(params[i]);
				i++;
			}

			PrintWriter serverWrite = new PrintWriter(
					clientSocket.getOutputStream(), true);

			Class c = Class.forName("firstdistributionsystem.MathLogic");

			Constructor[] con = c.getConstructors();
			MathLogic ra = (MathLogic) con[0].newInstance();
			Method[] mets = c.getDeclaredMethods();
			Method met = null;

			for (Method m : mets) {
				if (func.equalsIgnoreCase(m.getName())) {
					met = m;
				}
			}

			Object[] complete = new Object[input.length];

			for (int r = 0; r < input.length; r++) {
				complete[r] = input[r];
			}

			int e = 0;
			for (String s : types) {
				Class pT = Class.forName(s);
				complete[e] = pT.getMethod("valueOf", String.class).invoke(
						null, (String) complete[e]);
				e++;
			}
			serverWrite.println(met.invoke(ra, complete));
//method.invoke(c.getDeclaredMethod("getInstance", null).invoke(null, null) complete);
			clientSocket.shutdownOutput();
			serverWrite.flush();
			serverSocket.close();
			clientSocket.close();
			clientScan.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
