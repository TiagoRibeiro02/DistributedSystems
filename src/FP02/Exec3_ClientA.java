package FP02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Exec3_ClientA {
	public Exec3_ClientA() {
		try {
			System.out.println("Processo Cliente");
			Socket sc = new Socket("127.0.0.1", 2222);
			ObjectOutputStream os = new ObjectOutputStream(sc.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(sc.getInputStream());
			//System.out.println(is.readObject());
			
			char tipoCliente = 'A';
			os.writeChar(tipoCliente);
			os.flush();
			
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			int vInteiro = 0;
			while (vInteiro != -1) {
				System.out.print("Digite um número inteiro (ou 'sair' para encerrar): ");
				String linha = teclado.readLine();
				if (linha.equalsIgnoreCase("sair")) {
					vInteiro = -1;
					os.writeInt(vInteiro);
					os.flush();
				} else {
					// Envio do valor a calcular
					vInteiro = Integer.parseInt(linha);
					os.writeInt(vInteiro);
					os.flush();
					// Resposta do Servidor
					System.out.println("[Servidor] " + vInteiro + "^ 2 = " + is.readInt());
				}

			}

			os.close();
			is.close();
			sc.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {
		Exec3_ClientA c3 = new Exec3_ClientA();
	}
}
