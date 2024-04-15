package FP06;

import java.net.MalformedURLException;
import java.rmi.*;
public class CalculatorServer {
	public CalculatorServer() {
		// i)
		//System.setSecurityManager ( new SecurityManager());
		try {
			// ii)
			Calculator c = new CalculatorImpl();
			// iii)
			try { // Iniciar a execução do registry no porto desejado
				java.rmi.registry.LocateRegistry.createRegistry(1099);
				System.out.println("RMI registry ready.");
			} catch (Exception e) {
				System.out.println("Exception starting RMI registry:");
			}
			Naming.rebind("rmi://127.0.0.1:1099/CalculatorService", c);
		} catch (Exception e) {
			System.out.println("Trouble: " + e); } }
	public static void main(String args[]) {
		new CalculatorServer();
	}
}