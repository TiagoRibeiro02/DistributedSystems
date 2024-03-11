package FP03;

public class TesteDaemon {
	public static void main(String[] str) {
		Normal Tnormal;
		Daemon Tdaemon;
		Tnormal = new Normal();
		Tdaemon = new Daemon();
		Tnormal.start();
		Tdaemon.start();
	}
}
