package br.edu.ifpb.dac.infra.percistence.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    public static Connection abrirConexao() {
        ;
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5434/dac-jsf",
                    "postgres", "12345");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void fecharConexao(Connection connec) {
        try {
            connec.close();
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
