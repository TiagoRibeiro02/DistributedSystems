package FP00;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class exc5b {
	public static void main(String[] args) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\\\\\\\Users\\\\\\\\tigol\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\DistributedSystems\\\\\\\\src\\\\\\\\FP00\\\\\\\\person.ser"))){
			while(true) {
				try {
					Person person = (Person) ois.readObject();
					System.out.println(person);
				} catch(EOFException e) {
					System.out.println("End of file reached");
					break;
				} catch (ClassNotFoundException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
