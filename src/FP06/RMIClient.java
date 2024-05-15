package FP06;

import java.rmi.Naming;
import java.util.Scanner;
public class RMIClient {
	public static void main(String[] argv) {

		try {
			//bind server object to object in client
			RMIInterface myServerObject = (RMIInterface) Naming.lookup("RMIImpl");
			
			Scanner scanner = new Scanner(System.in);
			int opcao;
			do {
				System.out.println("==MENU==");
				System.out.println("1. Adicionar");
				System.out.println("2. Consulta");
				System.out.println("3. Nº de Consultas");
				System.out.println("4. Sair");
				System.out.println("====================");
				System.out.println("Digite a sua escolha:");
				opcao = scanner.nextInt();
				switch(opcao) {
					case 1:
						System.out.println("Opção 1 selecionada - Adicionar");
						scanner.nextLine();
						System.out.println("Digite o texto a ser adicionado:");
						String texto = scanner.nextLine();
						myServerObject.adiciona(texto);
						break;
					case 2:
						System.out.println("Opção 2 selecionada - Consulta.");
						System.out.println( myServerObject.consulta() );
						break;
					case 3:
						System.out.println("Opção 3 selecionada - Nº de Consultas.");
						System.out.println( myServerObject.getCount() );
						break;
					case 4:
						System.out.println("Encerrando...");
						break;
					default:
						System.out.println("Opção inválida, digite uma opção valida.");
						break;
				}
			} while(opcao != 4);
			scanner.close();
		}
		catch(Exception e) {
			System.out.println("Exception occured: " + e);
			System.exit(0);
		}
		System.out.println("RMI connection successful");
	}
}
