package FP06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AlunoImpl extends java.rmi.server.UnicastRemoteObject implements Aluno {
    
    private ArrayList<AlunoO> alunosRegistados;
    private int numA;

    public AlunoImpl() throws RemoteException {
        super();
        this.numA = 0;
        this.alunosRegistados = new ArrayList<>();
        carregarDados(); // Load data at initialization
    }
    
    public ArrayList<AlunoO> getAlunos() throws RemoteException {
        return new ArrayList<>(alunosRegistados); // Return a copy to avoid external modification
    }
    
    public int getAcessos() throws RemoteException {
        return numA;
    }
    
    public int registarAluno(AlunoO aluno) throws RemoteException {
        if (alunosRegistados.contains(aluno)) {
            return -1;
        }
        alunosRegistados.add(aluno);
        escreverDados();
        return alunosRegistados.size();
    }

    public ArrayList<AlunoO> pesquisarAluno(String nomealuno) throws RemoteException {
        ArrayList<AlunoO> alunosEncontrados = new ArrayList<>();
        for (AlunoO aluno : alunosRegistados) {
            if (aluno.getNome().equals(nomealuno)) {
                alunosEncontrados.add(aluno);
            }
        }
        return alunosEncontrados;
    }

    private void escreverDados() {
        try (FileWriter writer = new FileWriter("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP06/alunosregistados.txt")) {
            for (AlunoO aluno : alunosRegistados) {
                writer.write(aluno.getNumero() + "," + aluno.getNome() + "," + aluno.getCurso() + "," + aluno.getContacto() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void carregarDados() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP06/alunosregistados.txt"))) {
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
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
        }
    }
}
