package FP06;

import java.util.ArrayList;

public interface Aluno extends java.rmi.Remote {
	public int registarAluno(AlunoO aluno) throws java.rmi.RemoteException;
	public ArrayList<AlunoO> pesquisarAluno(String nomeAluno) throws java.rmi.RemoteException;
	public ArrayList<AlunoO> getAlunos() throws java.rmi.RemoteException;
	public int getAcessos() throws java.rmi.RemoteException;
}
