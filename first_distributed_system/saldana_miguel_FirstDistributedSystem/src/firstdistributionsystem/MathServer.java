package firstdistributionsystem;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MathServer {
	/**
	 * Miguel Saldana
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (;;) {
			try {
				while (true) {
					ServerSocket serverSocket = new ServerSocket(8080);
					Socket clientSocket = null;

					clientSocket = serverSocket.accept();

					Scanner clientScan = new Scanner(
							clientSocket.getInputStream());
					clientScan.useDelimiter("$");
					String[] resp = clientScan.nextLine().split(" ");

					int first = Integer.parseInt(resp[1]);
					int second = Integer.parseInt(resp[2]);

					PrintWriter serverWrite = new PrintWriter(
							clientSocket.getOutputStream(), true);

					if (resp[0].contains("add")) {
						serverWrite.println(add(first, second));
					} else if (resp[0].contains("subtract")) {
						serverWrite.println(subtract(first, second));
					}

					clientSocket.shutdownOutput();
					serverWrite.flush();
					serverSocket.close();
					clientSocket.close();
					clientScan.close();
				}
			} catch (Exception e) {
			}
		}
	}

	static int add(int first, int second) {
		int result = first + second;
		return result;
	}

	static int subtract(int first, int second) {
		int result = first - second;
		return result;
	}

}
