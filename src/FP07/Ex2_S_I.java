package FP07;

public interface Ex2_S_I extends java.rmi.Remote {
	 public void printOnServer(String s) throws java.rmi.RemoteException;
	 public void subscribe (String s, Ex2_C_I cliente) throws java.rmi.RemoteException;
}
