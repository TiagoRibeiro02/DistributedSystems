package FP03;

public class TesteSoma {
	public static void main(String[] str) {
		int dim = 1000000;
		int[] A = new int[dim];
		int[] B = new int[dim];
		for (int i = 0; i < A.length; i++) {
			A[i] = (int) (Math.random() * 100);
			B[i] = (int) (Math.random() * 100);
		}
		int[] C = new int[dim];
		
		long inicioSeq = System.currentTimeMillis();
		for (int i = 0; i < dim; i++) {
			C[i] = A[i] + B[i];
		}
		long fimSeq = System.currentTimeMillis();
		System.out.println("Tempo demorado exec sequencial " + (fimSeq - inicioSeq) + "ms");
		
		
		long inicioMulti = System.currentTimeMillis();
		ThreadSoma ts1 = new ThreadSoma(A, B, C, 0, dim / 2);
		ThreadSoma ts2 = new ThreadSoma(A, B, C, dim / 2, dim);
		
		try {
			System.out.println("Prioridade" + ts1.getPriority());
			System.out.println("Prioridade" + ts2.getPriority());
			ts1.join();
			ts2.join();
			long fimMulti = System.currentTimeMillis();
			System.out.println("Tempo demorado exec Multi-Thread " + (fimMulti - inicioMulti) + "ms");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Experimente mostrar o último valor do array resultado:
		System.out.println(C[dim - 1]);
	}
}
