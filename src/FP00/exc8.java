package FP00;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class exc8 {
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in);
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\tigol\\\\\\\\\\\\\\\\Documents\\\\\\\\\\\\\\\\GitHub\\\\\\\\\\\\\\\\DistributedSystems\\\\\\\\\\\\\\\\src\\\\\\\\\\\\\\\\FP00\\\\\\\\\\\\\\\\books.ser"))){
			ArrayList<String> books = new ArrayList<String>();
			while(true) {
				System.out.println("Enter a book name or 'done' to finish: ");
				String book = scanner.nextLine();
				if(book.equals("done")) {
					break;
				}
				books.add(book);
			}
			oos.writeObject(books);
			System.out.println("done");
		}catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
