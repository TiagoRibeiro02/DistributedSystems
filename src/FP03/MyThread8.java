package FP03;

public class MyThread8 extends Thread {
	public MyThread8() {
		super();
	}

	public void run() {
		System.out.println("Hello there, from " + getName());
		while(true) {
			System.out.println("Thread " + getName() + "is running");
		}
	}
}