package FP05;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Connection5_5 extends Thread {

    private Socket S;
    private ArrayList<Aluno> alunosRegistados;
    private int[] numeroAcessos;

    public Connection5_5(Socket S, ArrayList<Aluno> alunosRegistados, int[] numeroAcessos) {
        super();
        this.S = S;
        this.alunosRegistados = alunosRegistados;
        this.numeroAcessos = numeroAcessos;
        start();
    }

    public void run() {
        try {

            // Cria streams de entrada e saída de objetos
            ObjectOutputStream oos = new ObjectOutputStream(S.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(S.getInputStream());

            int op = 0;
            int conversaTerminada = 0;
            while (conversaTerminada == 0) {
                // Recebe operação escolhida pelo cliente
                op = ois.readInt();

                switch (op) {
                    case 1:
                        // Recebe o aluno criado
                        Aluno alCriado = (Aluno) ois.readObject();

                        int numAlunosRegistados = registarAluno(alCriado);

                        // Envia o nº de alunos registados
                        oos.writeInt(numAlunosRegistados);
                        oos.flush();
                        break;
                    case 2:
                        // Envia o nº de alunos registados
                        oos.writeObject(alunosRegistados);
                        oos.flush();
                        break;
                    case 3:
                        // Nº Acessos ao Servidor
                        oos.writeInt(numeroAcessos[0]);
                        oos.flush();
                        break;
                    case 4:
                        // Envia o nº de alunos registados
                        String nomeAlunoPesquisado;
                        ArrayList<Aluno> alunosEncontrados = new ArrayList<Aluno>();
                        nomeAlunoPesquisado = (String) ois.readObject();
                        alunosEncontrados = pesquisaAluno(nomeAlunoPesquisado);
                        oos.writeObject(alunosEncontrados);
                        oos.flush();
                        break;
                    case 5:
                        System.out.println("Conexão terminada com o cliente!");
                        synchronized (numeroAcessos) {
                            numeroAcessos[0] = numeroAcessos[0] - 1;
                        }
                        conversaTerminada = 1;
                        break;
                    default:
                        System.out.println("Indique uma operação válida!");
                        break;
                }
            }

            // Fecha as streams e o socket
            oos.close();
            ois.close();
            S.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public int registarAluno(Aluno aluno) {
        synchronized (alunosRegistados) {
            if (alunosRegistados.contains(aluno)) {
                return -1; // aluno já está registado
            }

            alunosRegistados.add(aluno);
            escreverDados();

        }
        return alunosRegistados.size();
    }

    public ArrayList<Aluno> pesquisaAluno(String nomealuno) {
        ArrayList<Aluno> alunosEncontrados = new ArrayList<Aluno>();
        
            for (int i = 0; i < alunosRegistados.size(); i++) {
                if (alunosRegistados.get(i).getNome().equals(nomealuno)) {
                    alunosEncontrados.add(alunosRegistados.get(i));
                }
            }
        
        return alunosEncontrados;
    }

    private void escreverDados() {
        synchronized (alunosRegistados) {
            try {
                FileWriter writer = new FileWriter(
                        "C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP05/alunosregistados.txt");
                for (Aluno aluno : alunosRegistados) {
                    writer.write(aluno.getNumero() + "," + aluno.getNome() + "," + aluno.getCurso() + ","
                            + aluno.getContacto() + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
