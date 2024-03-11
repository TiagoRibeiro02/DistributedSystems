package FP03;

public class Teste8 {
	public static void main(String[] str) {
		MyThread8 Ta, Tb, Tc;
		Ta = new MyThread8();
		Tb = new MyThread8();
		Tc = new MyThread8();
		Ta.setPriority(1);
		Tb.setPriority(10);
		Tc.setPriority(5);
		Ta.start();
		Tb.start();
		Tc.start();
	}
}
