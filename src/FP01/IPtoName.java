package FP01;

import java.net.*;
import java.io.*;
public class IPtoName {
public static void main (String args[] ) throws IOException{
 String s=" ";
 char c;
 System.out.print("IP address? ");
 while ( (c=(char)System.in.read()) != 10)
 s+=c;
 s=s.trim();
 InetAddress host =null;
 try {
	 host = InetAddress.getByName(s);
	 String nome = host.getHostName();
	 System.out.println(nome);
	 
 }
 catch (UnknownHostException e){
 System.out.println("IP malformed ");
 }
 }
}