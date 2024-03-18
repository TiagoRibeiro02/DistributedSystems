package FP04;

public class Processo2 extends Thread {
	private int[] variavelPart;

	public Processo2(int[] variavelPart) {
		super();
		this.variavelPart = variavelPart;
		setDaemon(true);
		start();
	}

	public void run() {
		while (true) {
			// secção crítica 2
			synchronized (variavelPart) {
				variavelPart[0] = variavelPart[0] + 1;
			}

			System.out.println("********P2: " + variavelPart[0]);
		}
	}
}
