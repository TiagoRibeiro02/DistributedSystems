package FP02;
import java.io.*;
import java.net.*;

import Ler.Ler;

public class Ex4_Client {
	public Ex4_Client() {
		try {
			System.out.println("-- Processo Cliente --\n");
			Socket sc = new Socket("127.0.0.1", 2222);
			ObjectOutputStream os = new ObjectOutputStream(sc.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(sc.getInputStream());
			
			int conversaTerminada = 0;
			while(conversaTerminada != 1) {
				System.out.print("Cliente: ");
				String textoClienteTemp = Ler.umaString();
				os.writeObject(textoClienteTemp);
				os.flush();
				
				String textoServerTemp = (String) is.readObject();
				System.out.println("Servidor: " + textoServerTemp);
				if(textoServerTemp.equals("fim")) {
					conversaTerminada = 1;
				}
			}
			

			os.close();
			is.close();
			sc.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		Ex4_Client c = new Ex4_Client();
	}
}
