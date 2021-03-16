package br.edu.ifpb.dac.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public Conexao() {

	}

	public static Connection abrirConexao() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("dbc:postgresql://localhost:5434/dac-jsf", "postegres", "666");

		} catch (Exception e) {
			System.out.print(e);
			return null;
		}
	}

	public static void CloseConecxao(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			System.out.print(e);
			// TODO: handle exception
		}
	}
}
