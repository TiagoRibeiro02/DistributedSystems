package FP07;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class DonativoImpl extends java.rmi.server.UnicastRemoteObject implements Donativo {
    ArrayList<String> listaDoadores;
    int donativos;
    DonativosFactory doadoresCallBack;
    
    public DonativoImpl() throws java.rmi.RemoteException {
        super();
        listaDoadores = new ArrayList<String>();
        donativos = 0;
    }
    
    public DonativoImpl(ArrayList<String> listaDoadores) throws java.rmi.RemoteException {
        super();
        this.listaDoadores = listaDoadores;
        donativos = 150;
    }
    
    public synchronized void registrarCallback(DonativosFactory objCliente) throws RemoteException {
        doadoresCallBack = objCliente;
    }
    
    public int consultarTotal() throws java.rmi.RemoteException {
        return donativos;
    }
    
    public ArrayList<String> consultarDoadores() throws java.rmi.RemoteException {
        ArrayList<String> uniqueList = new ArrayList<>();
        for (String element : listaDoadores) {
            // Check if the element is not already in the unique list
            if (!uniqueList.contains(element)) {
                // Add the element to the unique list if it's not a duplicate
                uniqueList.add(element);
            }
        }
        return uniqueList;
    }

    @Override
    public void setDonativo(int valor, String nome) throws RemoteException {
        if (listaDoadores.size() == 2 && doadoresCallBack != null) {
            doadoresCallBack.notificarDoadorNumero3();
        }
        listaDoadores.add(nome);
        donativos += valor;
        System.out.println("Recebido donativo de " + valor + " euros do doador " + nome);
    }
    
    public static void main (String [] args){
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready!");
        } catch (Exception e) {
            System.out.println("Exception starting RMI registry");
        }
        
        try {
            // instanciar objeto remoto
            DonativoImpl objRemoto = new DonativoImpl();
            
            // registar o objeto remoto no Registry
            Naming.rebind("DoadoresImpl", objRemoto);
            
            System.out.println("Remote object ready");
        } catch (MalformedURLException | RemoteException e) {
            System.out.println(e.getMessage());
        }
    }
}
