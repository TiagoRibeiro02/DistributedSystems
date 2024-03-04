package FP02;

import java.io.*;
import java.net.*;

public class Server {
	public Server() {
		try {
			System.out.println("Processo Server");
			ServerSocket ss = new ServerSocket(2222);
			System.out.println("Servidor aguardando conexão..");
			
			Socket sos = ss.accept();
			System.out.println("Cliente conectado..");
			
			
			ObjectOutputStream oos = new ObjectOutputStream(sos.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(sos.getInputStream());
			oos.writeObject(" Olá, eu sou o servidor");
			oos.flush();
			System.out.println("Mensagem do Cliente: " + ois.readObject());
			oos.writeObject("Recebi a mensagem");
			oos.flush();
			System.out.println("Mensagem do Cliente: " + ois.readObject());

			oos.close();
			ois.close();
			sos.close();
			ss.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		Server s = new Server();
	}

}
