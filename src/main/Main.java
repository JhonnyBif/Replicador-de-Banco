package main;

import java.sql.SQLException;

import execution.ReplicacaoExecutar;

public class Main {
	public static void main(String[] args) {
		try {
			ReplicacaoExecutar.execute(45000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
