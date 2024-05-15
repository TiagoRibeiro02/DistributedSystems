package FP07;

import java.rmi.Naming;

import Ler.Ler;

public class DonativoCliente extends java.rmi.server.UnicastRemoteObject implements DonativosFactory {
    
    public DonativoCliente() throws java.rmi.RemoteException {
        super();
    }
    
    public void notificarDoadorNumero3() throws java.rmi.RemoteException {
        System.out.println("Parabéns! Você é o doador número 3.");
    }
    
    public static void main(String[] argv) {
        try {
            Donativo myServerObject = (Donativo) Naming.lookup("DoadoresImpl");

            DonativoCliente objCliente = new DonativoCliente();
            myServerObject.registrarCallback((DonativosFactory)objCliente);

            int opcao;

            do {
                // Exibe o menu de opções
                System.out.println("==== Menu ====");
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Fazer donativo");
                System.out.println("2 - Consultar total doado");
                System.out.println("3 - Consultar lista de doadores");
                System.out.println("4 - Sair");
                System.out.println("===============");
                System.out.print("Digite o número da opção desejada: ");
                opcao = Ler.umInt();

                switch (opcao) {
                case 1:
                    System.out.print("Nome do doador: ");
                    String nomeDoador = Ler.umaString();
                    System.out.print("Valor a doar (em euros): ");
                    int valor = Ler.umInt();
                    myServerObject.setDonativo(valor, nomeDoador);
                    break;
                case 2:
                    // Lógica para a opção 2
                    System.out.println("Total doado: " + myServerObject.consultarTotal());
                    break;
                case 3:
                    // Lógica para a opção 3
                    System.out.println("Lista de doadores: " + myServerObject.consultarDoadores());
                    break;
                case 4:
                    // Sair do programa
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
                }
            } while (opcao != 4); // Repete até que a opção 4 (Sair) seja selecionada

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
            System.exit(0);
        }
        System.out.println("RMI connection successful");
    }
}
