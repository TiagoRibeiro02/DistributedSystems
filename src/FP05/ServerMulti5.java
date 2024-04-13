package FP05;

import java.io.*;
import java.net.*;
import java.util.ArrayList;



public class ServerMulti5 {
	private ArrayList<Aluno> alunosRegistados;
	private int []numeroAcessos;
	private Connection5 c;

	public ServerMulti5() {
		ServerSocket ss = null;

		// Cria um servidor socket na porta 2222
		try {
			ss = new ServerSocket(2222);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.alunosRegistados = new ArrayList<Aluno>();
		numeroAcessos = new int[1];
		numeroAcessos[0] = 0;
		carregarDados();

		try {
			System.out.println("-- Processo Ex5_Servidor --\n");

			while (true) {
				// Aguarda uma conexão do cliente
				Socket sos = ss.accept();
				synchronized (numeroAcessos) {
					numeroAcessos[0] = numeroAcessos[0] + 1;
				}
                System.out.println("Cliente conectado: " + sos.getInetAddress());
                c = new Connection5(sos, alunosRegistados, numeroAcessos);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private void carregarDados() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP05/alunosregistados.txt"));
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(",");
				int numero = Integer.parseInt(campos[0]);
				String nome = campos[1];
				String curso = campos[2];
				String contacto = campos[3];
				Aluno aluno = new Aluno(numero, nome, curso, contacto);
				alunosRegistados.add(aluno);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ServerMulti5 s = new ServerMulti5();
	}
}
