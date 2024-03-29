package FP01;

import Ler.Ler;

import java.net.*;
import java.io.*;
public class UDPClient{
 public static String readString (){
	 BufferedReader canal;
	 canal = new BufferedReader ( new InputStreamReader (System.in));
	 try {
		 return canal.readLine();
	 }
	 catch (IOException ex) {
		 return null;
	 }
 }
 public static void main(String args[]){
	 String s;
	 System.out.print("Qual o servidor? ");
	 String host = readString();
	 DatagramSocket aSocket = null;
	 try {
		 aSocket = new DatagramSocket();
		 aSocket.setSoTimeout(10000);
		 while(true){
			 System.out.print("<Client> Mensagem a enviar = ");
			 s = Ler.umaString();
			 if(s.equals("fim")) {
				 break;
			 }
			 byte [] m = s.getBytes();
			 InetAddress aHost = InetAddress.getByName(host);
			 int serverPort = 2222;
			 DatagramPacket request = new DatagramPacket(m, m.length, aHost,serverPort);
			 long mensagemEnviada = System.currentTimeMillis();
			 aSocket.send(request);
			 byte[] buffer = new byte[100];
			 DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			 aSocket.receive(reply);
			 long mensagemRecebida = System.currentTimeMillis();
			 System.out.println("<Client> Recebeu: " + new String(reply.getData()));
			 System.out.println("<Client> Round-Trip-Time A -> B -> A: " + (mensagemRecebida - mensagemEnviada));
		 } // while
	 }catch (SocketTimeoutException e) {System.out.println("Socket: " + e.getMessage());
	 }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	 }catch (IOException e){System.out.println("IO: " + e.getMessage());
	 }finally {if(aSocket != null) aSocket.close();}
 }
}
