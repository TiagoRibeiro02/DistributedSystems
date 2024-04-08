package FP05;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Ler.Ler;

public class Client5 {
	public Client5() {
		try {
			System.out.println("-- Processo Cliente --\n");
			Socket sc = new Socket("127.0.0.1", 2222);
			ObjectOutputStream os = new ObjectOutputStream(sc.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(sc.getInputStream());


			int op = 0;
			int conversaTerminada = 0;
			while (conversaTerminada != 1) {
				System.out.println("[Menu Operações]");
				System.out.println("1 - Registar aluno");
				System.out.println("2 - Consultar quais os alunos registados");
				System.out.println("3 - Consultar o número de acessos");
				System.out.println("4 - Investigar aluno");
				System.out.println("5 - Sair");
				System.out.print("Indique a operação que deseja realizar: ");
				op = Ler.umInt();

				// Envio da Opção Escolhida
				os.writeInt(op);
				os.flush();

				switch (op) {
				case 1:
					String nome;
					int num;
					String curso;
					String contacto;
					System.out.println("\n--------------------------");
					System.out.println("1. Registo de um Aluno");
					System.out.print("Nome: ");
					nome = Ler.umaString();

					System.out.print("Número: ");
					num = Ler.umInt();

					System.out.print("Curso: ");
					curso = Ler.umaString();

					System.out.print("Contacto: ");
					contacto = Ler.umaString();

					Aluno alCriado = new Aluno(num, nome, curso, contacto);

					// Envio do Aluno criado
					os.writeObject(alCriado);
					os.flush();

					// Receber Nº Alunos Registos
					int nAlunosRegistados = is.readInt();

					if (nAlunosRegistados == -1) {
						System.out.println("\nAluno já existe! Tente novamente...");
					} else {
						System.out.println("\nAluno adicionado com sucesso!");
						System.out.println("Alunos Registados: " + nAlunosRegistados);
					}
					System.out.println("--------------------------\n");
					break;
				case 2:
					System.out.println("\n--------------------------");
					System.out.println("2. Consultar quais os alunos registados");
					// Receber Nº Alunos Registos
					ArrayList<Aluno> alunosJaRegistados = (ArrayList<Aluno>) is.readObject();
					System.out.println(alunosJaRegistados.toString());
					System.out.println("--------------------------\n");
					break;
				case 3:
					System.out.println("\n--------------------------");
					System.out.println("3. Acessos ao Servidor");
					int acessos = is.readInt();
					System.out.println("Contagem de acessos ao Servidor: " + acessos);
					System.out.println("--------------------------\n");
					break;
				case 4:
					System.out.println("\n--------------------------");
					System.out.println("4. Investigar Aluno");

					System.out.print("Nome do Aluno: ");
					String nomeA = Ler.umaString();

					// Envio do nome pesquisado
					os.writeObject(nomeA);
					os.flush();

					// Receber Array de Alunos Pesquisados
					int alunosEncotrados = is.readInt();
					System.out.println("Resultados da Pesquisa:");
					System.out.println("Nº " + alunosEncotrados);
						
					
					System.out.println("--------------------------\n");
					break;
				case 5:
					System.out.println("Sessão encerrada com sucesso!");
					conversaTerminada = 1;
					break;
				default:
					System.out.println("Indique uma operaçãp válida!");
				}
			}

			os.close();
			is.close();
			sc.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		Client5 c = new Client5();
	}
}