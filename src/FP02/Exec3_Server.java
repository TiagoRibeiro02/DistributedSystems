package FP02;

import java.io.*;
import java.net.*;

public class Exec3_Server {
	public Exec3_Server() {
		while (true) {
			try {
				System.out.println("-- Processo Ex3_Servidor --\n");

				// Cria um servidor socket na porta 2222
				ServerSocket ss = new ServerSocket(2222);
				System.out.println("Ex3_Servidor aguardando conexões...");

				// Aguarda uma conexão do cliente
				Socket sos = ss.accept();
				System.out.println("Cliente conectado: " + sos.getInetAddress());

				// Cria streams de entrada e saída de objetos
				ObjectOutputStream oos = new ObjectOutputStream(sos.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(sos.getInputStream());

				// Recebe um valor do tipo char (Identificação do Cliente)
				char clienteTipo = ois.readChar();
				System.out.println("Identificação Cliente [A / B]: " + clienteTipo);

				if (clienteTipo == 'A') {
					while (true) {
						int vInteiro = ois.readInt();
						if (vInteiro == -1) {
							break;
						}
						int quadrado = (int) Math.pow(vInteiro, 2);
						// Recebe outra mensagem!
						oos.writeInt(quadrado);
						oos.flush();
					}
				} else if (clienteTipo == 'B') {
					while (true) {
						double vDouble = ois.readDouble();
						if (vDouble == -1) {
							break;
						}
						double raizquadrada = (double) Math.sqrt(vDouble);
						// Recebe outra mensagem!
						oos.writeDouble(raizquadrada);
						oos.flush();
					}
				}
				// Fecha as streams e o socket
				oos.close();
				ois.close();
				sos.close();
				ss.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public static void main(String args[]) {
		Exec3_Server s = new Exec3_Server();
	}

}
