package FP03;

public class MyThread5 extends Thread {
	public MyThread5(String name) {
		super(name);
	}
	public MyThread5(ThreadGroup tg, String name) {
		super(tg, name);
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Sou a " + this.getName());
			if (isInterrupted())
				break;
			Thread.yield();
		}
	}
}
