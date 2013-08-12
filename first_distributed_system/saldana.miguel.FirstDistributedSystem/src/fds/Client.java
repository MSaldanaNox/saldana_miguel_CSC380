package fds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

	public Client() {

	}

	public static void main(String[] args) {
		Client c = new Client();
		c.connectClient();
	}

	public void connectClient() {

		try {
			Socket server = new Socket("", 8080);
			OutputStream serverOut = server.getOutputStream();
			InputStream serverIn = server.getInputStream();

			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
