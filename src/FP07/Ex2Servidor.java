package FP07;

import java.rmi.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
public class Ex2Servidor extends java.rmi.server.UnicastRemoteObject implements Ex2_S_I {
	private static Ex2_C_I client;
	private static int numVencedor;
	private static ArrayList<Ex2_C_I> clientesLastNList;
	private int clientesLigados;
	private int nclientes;
	public Ex2Servidor() throws java.rmi.RemoteException{
		super();
		clientesLigados = 0;
		nclientes = 0;
		numVencedor = 0;
		clientesLastNList = new ArrayList<Ex2_C_I>();
	}
	
	public ArrayList<Ex2_C_I> getClientesLastNList(){
		return clientesLastNList;
	}
	
	public void setClientesLastNList(ArrayList<Ex2_C_I> clientesLastNList) {
		this.clientesLastNList = clientesLastNList;
	}
	//Método remoto
	public void printOnServer(String s) throws java.rmi.RemoteException{
		System.out.println( " SERVER : " +s );
	}
	//Método remoto
	public void subscribe (String name, Ex2_C_I c) throws java.rmi.RemoteException{
		System.out.println("Subscribing " + name );
		client = c;
		clientesLigados = clientesLigados + 1;
		c.setNumSeq(clientesLigados);
		nclientes = nclientes +1;
		clientesLastNList.add(c);
		if (nclientes == 5) {
            Random random = new Random();
            int startIndex = Math.max(0, clientesLastNList.size() - 5);
            int indiceVencedor = startIndex + random.nextInt(Math.min(5, clientesLastNList.size()));
            try {
                int numerosort = clientesLastNList.get(indiceVencedor).getNumSeq();
                for (int i = startIndex; i < clientesLastNList.size(); i++) {
                    clientesLastNList.get(i).printOnClient(numerosort);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            nclientes = 0;
        }
	}
	//Método local
	public static String lerString (){
		String s = "";
		try{
			BufferedReader in = new BufferedReader ( new InputStreamReader (System.in), 1);
			s= in.readLine();
		}
		catch (IOException e){
			System.out.println( e.getMessage());
		}
		return s;
	}
	public static void main (String [] args){
		String s;
		try {
			try { // Iniciar a execução do registry no porto desejado
				java.rmi.registry.LocateRegistry.createRegistry(1099);
				System.out.println("RMI registry ready.");
			} catch (Exception e) {
				System.out.println("Exception starting RMI registry:");
			}
			Ex2Servidor h = new Ex2Servidor();
			Naming.rebind ("Ex2", h);
			while (true){
				System.out.println("Mensagem para o cliente:");
				s= lerString();
			}
		}
		catch (RemoteException r){
			System.out.println("Exception in server"+r.getMessage());
		}
		catch (java.net.MalformedURLException u){
			System.out.println("Exception in server - URL" );
		}
	}
}