package FP04;

public class PostoVenda extends Thread {
	String posto;
	SalaCinema SC;
	
	public PostoVenda(String posto, SalaCinema SC) {
		super();
		this.posto = posto;
		this.SC = SC;
		start();
	}
	
	public void run() {
		int pausa;
		System.out.println(Thread.currentThread().getName());
		while (true) {
			try {
				pausa = (int) (Math.random() * 2000);
				//pausa = 2;
				sleep(pausa);
				System.out.println(posto + " vendeu o bilhete " + SC.venderBilhete() + " para o filme " + SC.getNomeFilme());
				if (SC.getBilhetesDisponiveis() == 0) {
					System.out.println(posto + " fim");
					break;
				}
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
