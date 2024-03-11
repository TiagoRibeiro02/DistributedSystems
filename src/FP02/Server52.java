package FP02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import Ler.Ler;

import java.util.ArrayList;

public class Server52 {

    Server52() throws IOException ,EOFException {

        ///Não esquecer de implementar a leitura para um ficheiro

        try {
            FileInputStream lerDados = new FileInputStream("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunos.txt");
            ObjectInputStream oisf = null;
            ObjectOutputStream oosfacessos = null;
            ObjectInputStream oisfacessos = null;
            ArrayList<Aluno> listaDeAlunos = new ArrayList<>();
            Byte acessos = null;
            ServerSocket meuServidor = new ServerSocket(2222);
            try {
                oisfacessos = new ObjectInputStream(new FileInputStream("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/acessos.txt"));

            } catch (EOFException ex) {
                System.out.println("End of file dos bytes");
            }
            try {
                acessos = oisfacessos.readByte();
                oisfacessos.close();
            } catch(NullPointerException ex) {
                System.out.println("Acessos não existiram ainda");
                acessos = 0;
            }



            try {
                 oisf = new ObjectInputStream(lerDados);

            } catch (EOFException ex) {
                System.out.println("End of file");
            }

            try {
                listaDeAlunos = (ArrayList<Aluno>) oisf.readObject();
                oisf.close();
            } catch(NullPointerException ex) {
                System.out.println("Lista Vazia");
            }






            while (true) {
                Socket sServidor = meuServidor.accept();
                oosfacessos = new ObjectOutputStream(new FileOutputStream("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/acessos.txt"));
                acessos++;
                oosfacessos.writeByte(acessos);
                oosfacessos.flush();
                oosfacessos.close();
                String msg;

                ObjectInputStream ois = new ObjectInputStream(sServidor.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(sServidor.getOutputStream());
                int escolha = -1;
                do {
                    escolha = (int) ois.readObject();
                    switch (escolha) {
                        case 1:
                            Aluno novo = (Aluno) ois.readObject();
                            if (!listaDeAlunos.contains(novo)) {
                                listaDeAlunos.add(novo);
                                FileOutputStream escreveAluno = new FileOutputStream("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunos.txt");
                                ObjectOutputStream oosf = new ObjectOutputStream(escreveAluno);

                                oosf.writeObject(listaDeAlunos);
                                oosf.flush();
                                oosf.close();
                            }

                            oos.writeObject(listaDeAlunos.size());
                            break;
                        case 2:
                            System.out.println(listaDeAlunos);
                            break;
                        case 3:
                            System.out.println(acessos);
                            break;
                        case 4:
                            System.out.println("Insira o nome da pessoa");
                            String aPesquisar= Ler.umaString();
                            for(Aluno a : listaDeAlunos){
                                if(a.getNome().equals(aPesquisar)){
                                    System.out.println(a.getContacto());
                                    System.out.println(a.getNumero());
                                }
                            }
                            break;

                    }
                } while (escolha != 0);

            }

        }  catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }


    }




    public static void main(String args[]) throws IOException, NullPointerException {
        Server52 s = new Server52();
    }
}
