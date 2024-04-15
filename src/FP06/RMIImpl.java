package FP06;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIImpl extends UnicastRemoteObject implements RMIInterface
{
	ArrayList<String> lista;
	int[] contConsulta = new int[1];
	
	public RMIImpl() throws java.rmi.RemoteException {
		super();
		lista = new ArrayList<String>();
	}
	
	public void adiciona ( String s) throws java.rmi.RemoteException {
		synchronized(lista) {
			lista.add(s);
		}
	}
	
	public ArrayList<String> consulta() throws java.rmi.RemoteException{
		synchronized(contConsulta) {
			contConsulta[0] ++;
		}
		return lista;
	}
	
	public int getCount() throws java.rmi.RemoteException{
		return contConsulta[0];
	}
}
