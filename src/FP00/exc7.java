package FP00;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class exc7 {
	public static void main(String[] args) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\\\\\\\Users\\\\\\\\tigol\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\DistributedSystems\\\\\\\\src\\\\\\\\FP00\\\\\\\\user.ser"))){
			ArrayList<String> lines = (ArrayList<String>) ois.readObject();
			System.out.println("Lines of text read from file:");
			for(String line : lines) {
				System.out.println(line);
			}
		} catch(IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
