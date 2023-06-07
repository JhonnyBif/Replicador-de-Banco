package main;

import java.sql.SQLException;

import execution.ReplicacaoExecutar;
import graphic.Replicacao;

public class Main {
	public static Replicacao rep;
	public Main() {
		rep = new Replicacao();
		rep.setVisible(true);
	}
	public static void main(String[] args) {
		
		new Main();
	}

}
