package FP03;

public class Normal extends Thread {
	public Normal() {
		super();
	}

	public void run() {
		 for (int i=0; i<6; i++){
		 try
		 { sleep(500);}
		 catch (InterruptedException e){
		 }
		 System.out.println (" I' m the normal Thread");
		 }
		 System.out.println (" The normal Thread is exiting");
		 }
}
