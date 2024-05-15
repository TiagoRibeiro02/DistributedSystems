package FP07;

public class CidadeImpl extends java.rmi.server.UnicastRemoteObject implements Cidade {
	private String nomeCidade;
	int populacao;

	
	public CidadeImpl() throws java.rmi.RemoteException {
		super();
		this.nomeCidade = "Não definido";
	}
	
	public CidadeImpl(String nomeCidade) throws java.rmi.RemoteException {
		super();
		this.nomeCidade = nomeCidade;
	}
	
	public CidadeImpl(String nomeCidade, int populacao) throws java.rmi.RemoteException {
		super();
		this.nomeCidade = nomeCidade;
		this.populacao = populacao;
	}
	
	public int getPopulacao() throws java.rmi.RemoteException {
		return populacao;
	}
}