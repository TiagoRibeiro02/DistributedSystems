package FP01;

import java.net.*;
import java.io.*;
public class NametoIp {
public static void main (String args[] ) throws IOException{
 String s=" ";
 char c;
 System.out.print("Name? ");
 while ( (c=(char)System.in.read()) != 10)
	s+=c;
 	s=s.trim();
 	InetAddress host =null;
 	try {
 		host = InetAddress.getByName(s);
 		String ip = host.getHostAddress();
 		System.out.println(ip);
 	}
 	catch (UnknownHostException e){
 		System.out.println("IP malformed ");
 	}
 }
}