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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	private static String packageName = "reflectclasses.";
	
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
			String packageName = "reflectclasses";
			URL packageUrl = this.getClass().getClassLoader()
					.getResource(packageName.replace(".", "/"));
			List allClasses = new ArrayList<>();
			if (packageUrl != null) {
				Path packagePath = Paths.get(packageUrl.toURI());
				if (Files.isDirectory(packagePath)) {
					try (DirectoryStream<Path> ds = Files.newDirectoryStream(
							packagePath, "*.class")) {
						for (Path d : ds) {
							allClasses.add(Class.forName(packageName
									+ "."
									+ d.getFileName().toString()
											.replace(".class", "")));
						}
					}
				}
				String cNames = "";
				for (Object o : allClasses) {
					cNames += o.toString() + ", ";
				}

				PrintWriter serverWrite = new PrintWriter(
						clientSocket.getOutputStream(), true);
				serverWrite.println(cNames);
				serverWrite.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private void sendMethods(String className) {
		PrintWriter serverWrite;
		try {
			Class<?> c;
			c = Class.forName(packageName + className);
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
			{
				if(!((Method) member).toString().contains("getInstance"))
					s += ((Method) member).toString() + " ";
			}
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
		if (convert.equalsIgnoreCase("long"))
			result = "java.lang.Long";

		return result;
	}

	@Override
	public void run() {
		try {
			server.sendClasses();
			
			Scanner clientScan;
			clientScan = new Scanner(clientSocket.getInputStream());
			clientScan.useDelimiter("$");
			String response = clientScan.nextLine();
			
			server.sendMethods(response);

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

			Class c = (Class) Class.forName(packageName+""+response);
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
			serverWrite.println(met.invoke(c.getDeclaredMethod("getInstance",null).invoke(null, null), complete));

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
