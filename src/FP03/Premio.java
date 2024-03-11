package FP03;

public class Premio {
	private int valor;
	public int getValor() {
	return valor;
	}
	//Alteramos o valor do prémio em exclusão mútua
	public synchronized void setValor(int valor) {
	this.valor = valor;
	}
	public synchronized void incValor() {
	valor ++;
	} 
}
