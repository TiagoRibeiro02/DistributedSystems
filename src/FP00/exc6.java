package FP00;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class exc6 {
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in);
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\tigol\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\GitHub\\\\\\\\\\\\\\\\DistributedSystems\\\\\\\\\\\\\\\\src\\\\\\\\\\\\\\\\FP00\\\\\\\\\\\\\\\\user.ser"))){
			ArrayList<String> lines = new ArrayList<String>();
			while(true) {
				System.out.println("Enter a line of text or 'done' to finish: ");
				String line = scanner.nextLine();
				if(line.equals("done")) {
					break;
				}
				lines.add(line);
			}
			oos.writeObject(lines);
			System.out.println("done");
		}catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
