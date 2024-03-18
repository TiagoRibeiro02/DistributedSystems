package FP04;

public class SalaCinema {
	private String nomeFilme;
	private int lotacao;
	private int bilhetesVendidos;
	
	public SalaCinema(String nomeFilme, int lotacao) {
		this.nomeFilme = nomeFilme;
		this.lotacao = lotacao;
		this.bilhetesVendidos = 0;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}
	
	public int getBilhetesDisponiveis() {
		return lotacao - bilhetesVendidos;
	}
	
	public int venderBilhete() {
		//while (bilhetesVendidos < lotacao) {
			//try {
				//wait();
			//} catch (InterruptedException e) {
				//e.printStackTrace();
			//}
		//}
		if(bilhetesVendidos >= lotacao) {
			//notifyAll();
			return -1;
		}
		
		bilhetesVendidos++;
		//notifyAll();
		return bilhetesVendidos;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public int getBilhetesVendidos() {
		return bilhetesVendidos;
	}

	public void setBilhetesVendidos(int bilhetesVendidos) {
		this.bilhetesVendidos = bilhetesVendidos;
	}

}
