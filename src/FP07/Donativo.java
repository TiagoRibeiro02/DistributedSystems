package FP07;

import java.util.ArrayList;

public interface Donativo extends java.rmi.Remote {
    public void setDonativo(int valor, String nome) throws java.rmi.RemoteException;
    public int consultarTotal() throws java.rmi.RemoteException;
    public ArrayList<String> consultarDoadores() throws java.rmi.RemoteException;
    public void registrarCallback(DonativosFactory objCliente) throws java.rmi.RemoteException;
}