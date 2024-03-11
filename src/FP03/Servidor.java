package FP03;

import java.net.*;
import java.io.*;

public class Servidor {
	private ServerSocket ss;
	private Socket s;
	private ThreadServidor c;

	public Servidor() {
		Premio p = new Premio();
		try {
			ss = new ServerSocket(5432);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			while (true) {
				s = ss.accept();
				c = new ThreadServidor(s, p); // passamos para a Thread o socket
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String args[]) {
		Servidor server = new Servidor();
	}

}
