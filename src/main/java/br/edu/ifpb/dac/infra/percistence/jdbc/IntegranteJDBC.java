package br.edu.ifpb.dac.infra.percistence.jdbc;

import br.edu.ifpb.dac.identidade.Integrante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntegranteJDBC {
    private static final long serialVersionUID = 1L;
    private Connection conexao;
    private Integrante integrante;

    public void nova(Integrante integrante) {
        String sql = "INSERT INTO integrante (nome, dataDeNascimento, cpf) VALUES(?,?,?)";
        this.conexao = Conexao.abrirConexao();
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getDataDeNascimento().toString());
            statement.setString(3, integrante.getCpf().valor());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }
    }

    public List<Integrante> todas() {
        // TODO Auto-generated method stub
        this.conexao = Conexao.abrirConexao();
        List<Integrante> dependente = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM integrante ";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            ResultSet resut = statement.executeQuery();
            while (resut.next()) {
                dependente.add(criarIntegrante(resut));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }
        if (dependente.size() > 0) {
            return Collections.unmodifiableList(dependente);
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    public void excluir(Integrante integrante) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM integrante WHERE id=?";
        try {
            this.conexao = Conexao.abrirConexao();
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setLong(1, integrante.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }

    }

    public void atualizar(Integrante integrante) {
        String sql = "UPDATE integrante SET nome=?, dataDenascimento=? WHERE id=?";
        this.conexao = Conexao.abrirConexao();
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getDataDeNascimento().toString());
            statement.setLong(3, integrante.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }

    }

    public Integrante localizarinteranteComId(long id) {
        Integrante integrante = new Integrante();
        try {
            this.conexao = Conexao.abrirConexao();
            String consulta = "SELECT * FROM integrante WHERE id=?;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            statement.setLong(1, id);
            integrante = percorrerIntegrante(statement);
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            integrante = Integrante.fake();
        } finally {
            Conexao.fecharConexao(conexao);
        }

        return integrante;
    }

    public List<Integrante> todosOsDepentendes() {
        this.conexao = Conexao.abrirConexao();
        List<Integrante> deps = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM integrante;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            ResultSet resut = statement.executeQuery();
            while (resut.next()) {
                deps.add(criarIntegrante(resut));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }
        if (deps.size() > 0) {
            return Collections.unmodifiableList(deps);
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    public void novo(Integrante integrante) {
        // TODO Auto-generated method stub

    }

    private Integrante percorrerIntegrante(PreparedStatement statement) {
        try {
            ResultSet executeQuery = statement.executeQuery();
            executeQuery.next();

            return criarIntegrante(executeQuery);
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integrante.fake();
    }

    private Integrante criarIntegrante(ResultSet result) {
        Integrante integrante = null;

        try {
            //  result.next();
            integrante = Integrante.of(result.getInt("id"),
                    result.getString("nome"),
                    result.getString("cpf"),
                    result.getString("dataDenascimento")
            );
        } catch (SQLException ex) {
            Integrante.fake();
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return integrante;
    }
    
}
