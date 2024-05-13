package FP05;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMulti5_5 {
	private ArrayList<Aluno> alunosRegistados;
    private int[] numeroAcessos;
    private Connection5 c;
    private final int MAX_THREADS = 5; // Maximum number of threads in the pool
    private WorkerThread[] threads;

    public ServerMulti5_5() {
        ServerSocket ss = null;

        // Create worker threads
        threads = new WorkerThread[MAX_THREADS];
        for (int i = 0; i < MAX_THREADS; i++) {
            threads[i] = new WorkerThread("Worker-" + i);
            threads[i].start();
        }

        // Create a server socket on port 2222
        try {
            ss = new ServerSocket(2222);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        this.alunosRegistados = new ArrayList<>();
        numeroAcessos = new int[1];
        numeroAcessos[0] = 0;
        carregarDados();

        try {
            System.out.println("-- Processo Ex5_Servidor --\n");

            while (true) {
                // Wait for a client connection
                Socket sos = ss.accept();
                synchronized (numeroAcessos) {
                    numeroAcessos[0]++;
                }
                System.out.println("Cliente conectado: " + sos.getInetAddress());
                assignWorker(sos);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void carregarDados() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP05/alunosregistados.txt"));
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

    private void assignWorker(Socket socket) {
        // Find the first available worker thread
        for (WorkerThread thread : threads) {
            if (!thread.isBusy()) {
                thread.assign(socket, alunosRegistados, numeroAcessos);
                return;
            }
        }
        // If no worker thread is available, wait and try again
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assignWorker(socket); // Retry assignment
    }

    public static void main(String[] args) {
        ServerMulti5_5 s = new ServerMulti5_5();
    }

    private class WorkerThread extends Thread {
        private Socket socket;
        private ArrayList<Aluno> alunosRegistados;
        private int[] numeroAcessos;
        private boolean busy;

        public WorkerThread(String name) {
            super(name);
            busy = false;
        }

        public void assign(Socket socket, ArrayList<Aluno> alunosRegistados, int[] numeroAcessos) {
            this.socket = socket;
            this.alunosRegistados = alunosRegistados;
            this.numeroAcessos = numeroAcessos;
            this.busy = true;
            synchronized (this) {
                notify(); // Notify the thread to start processing
            }
        }

        public boolean isBusy() {
            return busy;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (!busy) {
                        try {
                            wait(); // Wait until assigned a task
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Process client request
                c = new Connection5(socket, alunosRegistados, numeroAcessos);
                // Reset state
                socket = null;
                busy = false;
            }
        }
    }
}
