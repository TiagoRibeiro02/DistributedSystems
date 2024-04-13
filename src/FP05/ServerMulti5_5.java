package FP05;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMulti5_5 {
    private ArrayList<Aluno> alunosRegistados;
    private int[] numeroAcessos;
    private ExecutorService pool;

    public ServerMulti5_5() {
        ServerSocket ss = null;

        // Cria um servidor socket na porta 2222
        try {
            ss = new ServerSocket(2222);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.alunosRegistados = new ArrayList<Aluno>();
        numeroAcessos = new int[1];
        numeroAcessos[0] = 0;
        carregarDados();

        pool = Executors.newFixedThreadPool(5);

        try {
            System.out.println("-- Processo Ex5_Servidor --\n");

            while (true) {
                // Aguarda uma conexão do cliente
                Socket sos = ss.accept();

                synchronized (numeroAcessos) {
                    if (numeroAcessos[0] < 5) {
                        
                    	numeroAcessos[0]++;
                        System.out.println("Cliente conectado: " + sos.getInetAddress());

                        // Submete uma nova conexão ao pool de threads
                        pool.submit(new Connection5_5(sos, alunosRegistados, numeroAcessos));
                    } else {
                        // Se já houver 5 clientes conectados, recusa a conexão
                        System.out.println("Limite de conexões atingido. Cliente rejeitado.");
                        sos.close();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void carregarDados() {
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
                Aluno aluno = new Aluno(numero, nome, curso, contacto);
                alunosRegistados.add(aluno);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ServerMulti5_5 s = new ServerMulti5_5();
    }
}
