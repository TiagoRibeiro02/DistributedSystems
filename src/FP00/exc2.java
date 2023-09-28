package FP00;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class exc2 {
	public static void main(String args[]) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\tigol\\Documents\\GitHub\\DistributedSystems\\src\\FP00\\teste1.txt"));
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
