package FP04;

public class Processo1 extends Thread {
	private int[] variavelPart;
	private int x = 1;
	private int y = -1;

	public Processo1(int[] variavelPart) {
		super();
		this.variavelPart = variavelPart;
		start();
	}

	public void run() {
		while (true) {
			// secção crítica 1
			synchronized (variavelPart) {
				x = x - variavelPart[0];
				y = y + variavelPart[0];
			}

			if (x + y != 0) {
				System.out.println("Secção crítica violada" + variavelPart[0]);
				break;
			}
		}
	}

}
