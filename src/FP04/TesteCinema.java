package FP04;

public class TesteCinema {
	public static void main(String[] args) {
		SalaCinema sc = new SalaCinema("Filme 1", 35);
		
		PostoVenda pv1 = new PostoVenda("Localidade 1", sc);
		PostoVenda pv2 = new PostoVenda("Localidade 2", sc);
		PostoVenda pv3 = new PostoVenda("Localidade 3", sc);
	}

}
