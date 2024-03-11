package FP03;

public class Teste5 {
	public static void main (String[] arg){
		 MyThread5 Ta, Tb, Tc;
		 ThreadGroup this_group;
		 this_group = Thread.currentThread().getThreadGroup();
		 System.out.println("O nome do grupo é: " + this_group.getName());
		 System.out.println("O nº de Threads activas no grupo é " + this_group.activeCount());
		 Ta=new MyThread5 ("Thread A");
		 Tb=new MyThread5 ("Thread B");
		 Tc=new MyThread5 ("Thread C");
		 Ta.start();
		 Tb.start();
		 Tc.start();
		 
		 System.out.println("O nº de Threads activas no grupo é " + this_group.activeCount());
		 
		 try {
			 Thread.sleep(500);
		 } catch(InterruptedException e) {
			 System.out.println(e.getMessage());
		 }
		// Pode invocar um método em todas as Threads do grupo:
		this_group.interrupt();
		ThreadGroup Mygroup = new ThreadGroup ("O meu grupo");
		System.out.println("O nº de Threads activas no grupo é " + this_group.activeCount());
	}
	
}
