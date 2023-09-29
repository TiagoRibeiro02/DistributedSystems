package FP00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class exc9 {
	public static void main(String[] args) {
		ArrayList<String> booklist = readBooksFromFile();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println();
			System.out.println("1. Search for a book");
			System.out.println("2. List all books");
			System.out.println("3. Add a new book");
			System.out.println("4. Quit");
			System.out.print("Enter your choice (1-4): ");
			String choice = scanner.nextLine();
			switch(choice) {
			case "1":
				System.out.print("Enter the name of the book to search for: ");
				String bookName = scanner.nextLine();
				if(booklist.contains(bookName)) {
					System.out.println("The book '"+ bookName +"' is in the list");
				} else {
					System.out.println("The book '"+ bookName +"' is not in the list");
				}
				break;
			case "2":
				System.out.println("List of books:");
				for(String book : booklist) {
					System.out.println(book);
				}
				break;
			case "3":
				System.out.print("Enter the name of the new book: ");
				String newbookName = scanner.nextLine();
				booklist.add(newbookName);
				writeBookListToFile(booklist);
				System.out.println("Book added to the list.");
				break;
			case "4":
				System.out.println("Exiting...");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 4.");
			}
		}
		
	}

	private static void writeBookListToFile(ArrayList<String> booklist) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\\\\\Users\\\\\\\\tigol\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\DistributedSystems\\\\\\\\src\\\\\\\\FP00\\\\\\\\books.ser"))){
			oos.writeObject(booklist);
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static ArrayList<String> readBooksFromFile() {
		ArrayList<String> books = new ArrayList<String>();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\\\\\\\Users\\\\\\\\tigol\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\DistributedSystems\\\\\\\\src\\\\\\\\FP00\\\\\\\\books.ser"))){
			books = (ArrayList<String>) ois.readObject();
		} catch(IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return books;
	}
}
