package FP05;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Connection5 extends Thread{
	private Socket S;
	
	public Connection5(Socket s, Byte acessos, ArrayList<Aluno> listaDeAlunos) {
		super();
		S = s;
		start();
	} 
	
	public void run() {
        String msg;

        ObjectInputStream ois = new ObjectInputStream(S.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(S.getOutputStream());
        

        int escolha = -1;
        do {
            escolha = ois.readInt();
            Object aPesquisar;
			switch (escolha) {
                case 1:
                    Aluno novo = (Aluno) ois.readObject();
                    if (!listaDeAlunos.contains(novo)) {
                        listaDeAlunos.add(novo);
                        FileOutputStream escreveAluno = new FileOutputStream("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunos.txt");
                        ObjectOutputStream oosf = new ObjectOutputStream(escreveAluno);

                        oosf.writeObject(listaDeAlunos);
                    }

                    oos.writeInt(listaDeAlunos.size());
                    oos.flush();
                    break;
                case 2:
                	oos.writeObject(listaDeAlunos);
                	oos.flush();
                    break;
                case 3:
                    oos.writeInt(acessos);
                	oos.flush();
                    break;
                case 4:
                	aPesquisar = ois.readObject();
                    for(Aluno a : listaDeAlunos){
                        if(a.getNome().equals(aPesquisar)){
                        	oos.writeInt(a.getNumero());
                        	oos.flush();
                        }
                    }
                    break;

            }
        } while (escolha != 5);
	}
}
