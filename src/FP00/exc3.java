package FP00;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class exc3 {
	public static void main(String args[]) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter("C:\\Users\\tigol\\Documents\\GitHub\\DistributedSystems\\src\\FP00\\teste2.txt"));
			pw.println(2.31);
			pw.println(false);
			pw.print("X");
			pw.flush();
			pw.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
