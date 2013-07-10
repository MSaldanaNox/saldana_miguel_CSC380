package firstdistributionsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class MathClient {

	public static void main(String[] args) {
		for (;;) {
			try {
				Socket server = new Socket("", 8080);
				OutputStream serverOut = server.getOutputStream();
				InputStream serverIn = server.getInputStream();

				PrintWriter serverWrite = new PrintWriter(serverOut, true);

				String prompt;
				do {
					prompt = JOptionPane
							.showInputDialog("Would you like to add or subtract?");
				} while (prompt == null || prompt.isEmpty()
						|| !prompt.equalsIgnoreCase("add")
						&& !prompt.equalsIgnoreCase("subtract"));
				int firstNumber = 0;
				do {
					try {
						firstNumber = Integer.parseInt(JOptionPane
								.showInputDialog("First number you'd like to "
										+ prompt));
					} catch (NumberFormatException e) {
						firstNumber = 0;
					}
				} while (firstNumber == 0);

				int secondNumber = 0;
				do {
					try {
						secondNumber = Integer.parseInt(JOptionPane
								.showInputDialog("Second number you'd like to "
										+ prompt));
					} catch (NumberFormatException e) {
						secondNumber = 0;
					}
				} while (secondNumber == 0);

				serverWrite.println(prompt + " " + firstNumber + " "
						+ secondNumber);
				server.shutdownOutput();

				BufferedReader read = new BufferedReader(new InputStreamReader(
						serverIn));
				String resp = read.readLine();
				JOptionPane.showMessageDialog(null, resp, "The server says:",
						JOptionPane.INFORMATION_MESSAGE);

				server.close();
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
		}
	}
}