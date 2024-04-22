package FP07;

public interface Ex2_C_I extends java.rmi.Remote {
	 public void printOnClient (int numerosort)throws java.rmi.RemoteException;
	 public int getNumSeq() throws java.rmi.RemoteException;
	 public void setNumSeq(int numSeq) throws java.rmi.RemoteException;
}
