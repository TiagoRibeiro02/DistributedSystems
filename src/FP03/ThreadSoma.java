package FP03;

public class ThreadSoma extends Thread {
	int[] A, B, C;
	int p, u;

	public ThreadSoma(int[] A, int[] B, int[] C, int p, int u) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.p = p;
		this.u = u;
		start();
	}

	public void run() {
		for (int i = p; i < u; i++) {
			C[i] = A[i] + B[i];
		}
	}
}