package FP02;
import java.io.*;
import java.net.*;

import Ler.Ler;

public class Ex4_Server {
	public Ex4_Server() {
		while(true) {
			try {
				System.out.println("-- Processo Ex4_Servidor --\n");
				
				// Cria um servidor socket na porta 2222
				ServerSocket ss = new ServerSocket(2222);
				System.out.println("Ex4_Servidor aguardando conexões...");
				
				// Aguarda uma conexão do cliente
				Socket sos = ss.accept();
				System.out.println("Cliente conectado: " + sos.getInetAddress());
				
				// Cria streams de entrada e saída de objetos
	            ObjectOutputStream oos = new ObjectOutputStream(sos.getOutputStream());
	            ObjectInputStream ois = new ObjectInputStream(sos.getInputStream());
	            
	            
	            while(true) {
	            	// Recebe mensagem do cliente
		            System.out.println("Cliente:" + ois.readObject());
		            
		            
		            System.out.print("Servidor: ");
					String textoServerTemp = Ler.umaString();
					if(textoServerTemp.equals("fim")) {
						break;
					}
					oos.writeObject(textoServerTemp);
					oos.flush();
	            }

	            
	            // Fecha as streams e o socket
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
	}
	
	public static void main(String[] args) {
        Ex4_Server s = new Ex4_Server();
    }
}