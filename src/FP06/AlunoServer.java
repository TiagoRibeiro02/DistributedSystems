package FP06;

import java.rmi.Naming;

public class AlunoServer {
	public AlunoServer() {
		// i)
		//System.setSecurityManager ( new SecurityManager());
		try {
			// ii)
			Aluno c = new AlunoImpl();
			// iii)
			try { // Iniciar a execução do registry no porto desejado
				java.rmi.registry.LocateRegistry.createRegistry(1099);
				System.out.println("RMI registry ready.");
			} catch (Exception e) {
				System.out.println("Exception starting RMI registry:");
			}
			Naming.rebind("rmi://127.0.0.1:1099/AlunoService", c);
		} catch (Exception e) {
			System.out.println("Trouble: " + e); } }
	public static void main(String args[]) {
		new AlunoServer();
	}
}
