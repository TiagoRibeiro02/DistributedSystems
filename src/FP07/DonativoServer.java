package FP07;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class DonativoServer {
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
