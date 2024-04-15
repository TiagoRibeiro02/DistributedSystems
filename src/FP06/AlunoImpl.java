package FP06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AlunoImpl extends java.rmi.server.UnicastRemoteObject
implements Aluno {
	
	ArrayList<AlunoO> alunosRegistados;
	int numA = 0
			;
	public AlunoImpl() throws RemoteException {
		super();
		numA = numA + 1;
		alunosRegistados = new ArrayList<AlunoO>();
	}
	
	public ArrayList<AlunoO> getAlunos() throws RemoteException {
		alunosRegistados = carregarDados();
		return alunosRegistados;
	}
	public int getAcessos() throws RemoteException {
		return numA;
	}
	
	public int registarAluno(AlunoO aluno) throws java.rmi.RemoteException {
        ArrayList<AlunoO> alunosRegistados = carregarDados();
        if(alunosRegistados.contains(aluno)) {
        	return -1;
        	
        }
        alunosRegistados.add(aluno);
        escreverDados();
        
        return alunosRegistados.size();
    }

    public ArrayList<AlunoO> pesquisarAluno(String nomealuno) throws java.rmi.RemoteException{
    	ArrayList<AlunoO> alunosRegistados = carregarDados();
        ArrayList<AlunoO> alunosEncontrados = new ArrayList<AlunoO>();
        
        System.out.println(alunosRegistados.toString());
        for(int i = 0; i < alunosRegistados.size(); i++) {
        	if(alunosRegistados.get(i).getNome().equals(nomealuno)) {
        		alunosEncontrados.add(alunosRegistados.get(i));
        	}
        }
        return alunosEncontrados;
    }

    private void escreverDados() throws java.rmi.RemoteException{
    	ArrayList<AlunoO> alunosRegistados = carregarDados();
            try {
                FileWriter writer = new FileWriter(
                        "C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP05/alunosregistados.txt");
                for (AlunoO aluno : alunosRegistados) {
                    writer.write(aluno.getNumero() + "," + aluno.getNome() + "," + aluno.getCurso() + ","
                            + aluno.getContacto() + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    private ArrayList<AlunoO> carregarDados() throws java.rmi.RemoteException{
    	ArrayList<AlunoO> alunosRegistados = carregarDados();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP05/alunosregistados.txt"));
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(",");
				int numero = Integer.parseInt(campos[0]);
				String nome = campos[1];
				String curso = campos[2];
				String contacto = campos[3];
				AlunoO aluno = new AlunoO(numero, nome, curso, contacto);
				alunosRegistados.add(aluno);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
		}
		return alunosRegistados;
	}

}
