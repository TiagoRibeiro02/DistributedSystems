package FP06;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

import Ler.Ler;

public class AlunoCliente {
	public static void main(String[] argv) {

		try {
			//bind server object to object in client
			Aluno myServerObject = (Aluno) Naming.lookup("rmi://127.0.0.1:1099/AlunoService");
			
			Scanner scanner = new Scanner(System.in);
			int opcao;
			do {
				// Exibe o menu de opções
				System.out.println("==== Menu ====");
				System.out.println("1 - Registar aluno");
				System.out.println("2 - Consultar quais os alunos registados");
				System.out.println("3 - Consultar o número de acessos");
				System.out.println("4 - Investigar aluno");
				System.out.println("5 - Sair");
				System.out.println("===============");
				System.out.print("Digite o número da opção desejada: ");
				opcao = Ler.umInt();

				switch (opcao) {
				case 1:
					String nome;
					int num;
					String curso;
					String contacto;
					System.out.println("\n--------------------------");
					System.out.println("1. Registo de um Aluno");
					System.out.print("Nome: ");
					nome = Ler.umaString();
					
					System.out.println("Número: ");
					num = Ler.umInt();

					System.out.print("Curso: ");
					curso = Ler.umaString();

					System.out.print("Contacto: ");
					contacto = Ler.umaString();

					AlunoO alCriado = new AlunoO(num, nome, curso, contacto);
					

					if(myServerObject.pesquisarAluno(nome).size() == 0) {
						System.out.println("\nAluno adicionado com sucesso!");
						System.out.println("Alunos Registados: " + myServerObject.registarAluno(alCriado));
					}
					else {
						System.out.println("\nAluno já registado!");
					}
					System.out.println("--------------------------\n");
					break;
				case 2:
					// Lógica para a opção 2
					System.out.println("Opção 2 selecionada - Consultar.");
					System.out.println(myServerObject.getAlunos().toString());
					break;
				case 3:
					// Lógica para a opção 3
					System.out.println("Opção 3 selecionada - Nº de Consultas");
					System.out.println(myServerObject.getAcessos());
					break;
				case 4:
					// Sair do programa
					System.out.println("Opçãop 4 selecionada - Investigar aluno");
					System.out.print("Nome Aluno: ");
					nome = Ler.umaString();
					
					// Receber Array de Alunos Pesquisados
					ArrayList<AlunoO> alunosEncotrados = (ArrayList<AlunoO>) myServerObject.pesquisarAluno(nome);
					System.out.println("Resultados da Pesquisa:");
					if(alunosEncotrados.size() == 0) {
						System.out.println("Não há resultados para a sua pesquisa!");
					}
					else {
						for(int i = 0; i < alunosEncotrados.size(); i++) {
							System.out.println("Nº " + (i + 1));
							System.out.println(alunosEncotrados.toString());
						}
					}
					
					break;
				case 5:
					// Sair do programa
					System.out.println("Encerrando o programa.");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
				}
			} while (opcao != 5);
			scanner.close();
		}
		catch(Exception e) {
			System.out.println("Exception occured: " + e);
			System.exit(0);
		}
		System.out.println("RMI connection successful");
	}
}
