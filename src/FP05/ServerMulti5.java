package FP05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import Ler.Ler;

import java.util.ArrayList;

public class ServerMulti5 {

	FileInputStream lerDados = new FileInputStream("C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunos.txt");
    ObjectInputStream oisf = null;
    ObjectOutputStream oosfacessos = null;
    ObjectInputStream oisfacessos = null;
    ArrayList<Aluno> listaDeAlunos = new ArrayList<>();
    Byte acessos = null;
    private Connection5 c;
    private Socket s;
    private ServerSocket ss;
    
    public ServerMulti5() throws IOException ,EOFException, ClassNotFoundException{
    	try {
			ss = new ServerSocket(5432);
		} catch ( IOException e){ 
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("Processo Servidor");
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
			while(true) {
				s = ss.accept();
				c = new Connection5(s,acessos,listaDeAlunos);
				synchronized (acessos) {
					acessos++;
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
			}
		} catch (IOException e)	{
			System.out.println(e.getMessage());
		}
    }




    public static void main(String args[]) throws IOException, NullPointerException, ClassNotFoundException {
        ServerMulti5 s = new ServerMulti5();
    }
}
