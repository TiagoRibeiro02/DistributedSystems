package FP07;

import java.rmi.*;

public class Ex2Cliente extends java.rmi.server.UnicastRemoteObject implements Ex2_C_I {
	private int numSeq = -1;
	public Ex2Cliente() throws RemoteException {
		super();
	}
	
	public int getNumSeq() {
		return numSeq;
	}
	
	public void setNumSeq(int numSeq) {
		this.numSeq = numSeq;
	}
	//Método remoto
	public void printOnClient (int s)throws java.rmi.RemoteException{
		System.out.println ("Vencedor: " + s);
	}
	public static void main (String [] args){
		// System.setSecurityManager(new SecurityManager());
		try {
			Ex2_S_I h= (Ex2_S_I)Naming.lookup ("Ex2");
			Ex2Cliente c = new Ex2Cliente();
			h.subscribe( "Nome da máquina cliente ...", (Ex2_C_I)c);
		}
		catch (Exception r){
			System.out.println ( "Exception in client" +r.getMessage());
		}
	}
}