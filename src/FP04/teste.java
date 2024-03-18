package FP04;

public class teste {
	public static void main(String[] args) {
		int [] variavelPart = new int[1];
		variavelPart[0] = 0;
		
		Processo1 p1 = new Processo1(variavelPart);
		Processo2 p2 = new Processo2(variavelPart);
		
		System.out.println(variavelPart[0]);
	}

}
