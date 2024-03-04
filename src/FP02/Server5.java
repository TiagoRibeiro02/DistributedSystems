package FP02;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Ler.Ler;

public class Server5 {
	private ArrayList<Aluno> alunosRegistados;
	private int numeroAcessos;

	public Server5() {
		ServerSocket ss = null;

		// Cria um servidor socket na porta 2222
		try {
			ss = new ServerSocket(2222);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.alunosRegistados = new ArrayList<Aluno>();
		carregarDados();

		try {
			System.out.println("-- Processo Ex5_Servidor --\n");

			while (true) {
				// Aguarda uma conexão do cliente
				Socket sos = ss.accept();
				numeroAcessos = numeroAcessos + 1;
				System.out.println("Cliente conectado: " + sos.getInetAddress()); 
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public int registarAluno(Aluno aluno) {
		if (alunosRegistados.contains(aluno)) {
			return -1; // aluno já está registado
		}

		alunosRegistados.add(aluno);
		escreverDados();

		return alunosRegistados.size();
	}

	public ArrayList<Aluno> pesquisaAluno(String nomealuno) {
		ArrayList<Aluno> alunosEncontrados = new ArrayList<Aluno>();
		for (int i = 0; i < alunosRegistados.size(); i++) {
			if (alunosRegistados.get(i).getNome().equals(nomealuno)) {
				alunosEncontrados.add(alunosRegistados.get(i));
			}
		}
		return alunosEncontrados;
	}

	private void escreverDados() {
		try {
			FileWriter writer = new FileWriter(
					"C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunosregistados.txt");
			for (Aluno aluno : alunosRegistados) {
				writer.write(aluno.getNumero() + "," + aluno.getNome() + "," + aluno.getCurso() + ","
						+ aluno.getContacto() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarDados() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunosregistados.txt"));
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
		Server5 s = new Server5();
	}
}
