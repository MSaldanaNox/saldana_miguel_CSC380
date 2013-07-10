package firstdistributionsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MathServer implements Runnable {

	static ServerSocket serverSocket;
	Socket clientSocket;

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
				MathServer server = new MathServer(socket);
				Thread thread = new Thread(server);
				thread.start();
			}
		} catch (Exception e) {
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

	@Override
	public void run() {
		try {
			Scanner clientScan;
			clientScan = new Scanner(clientSocket.getInputStream());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
