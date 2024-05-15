package FP07;

public interface CidadeFactory extends java.rmi.Remote {
	 public Cidade getServidorCidade (String nomeCidade) throws java.rmi.RemoteException;
	 public Cidade getServidorCidadePopulacao(String nomeCidade, int populacao)  throws java.rmi.RemoteException;
	}

