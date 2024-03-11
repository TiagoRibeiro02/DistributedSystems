package FP03;

public class MyThread extends Thread {
	public MyThread() {
		super();
	}

	public void run() {
		System.out.println("Hello there, from " + getName());
		for (int i = 0; i < 5; i++) {
			System.out.println("Thread " + getName() + "is running");
		}
	}
}
