package FP05;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread{
	
	private Socket S;
	
	public Connection(Socket s) {
		super();
		S = s;
		start();
	}
	
	public void run() {
		try {
			// envia a data para o cliente duas vezes com um sleep entre elas
			ObjectOutputStream os = new ObjectOutputStream(S.getOutputStream());
            os.writeObject("A data e hora do sistema: " + new java.util.Date());
            os.flush();
            Thread.sleep(5000); // espera 5 segundos
            os.writeObject("A data e hora do sistema: " + new java.util.Date());
            os.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
