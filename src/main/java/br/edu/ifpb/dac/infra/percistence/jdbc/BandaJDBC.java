package br.edu.ifpb.dac.infra.percistence.jdbc;

import br.edu.ifpb.dac.identidade.Banda;
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

public class BandaJDBC {
    private static final long serialVersionUID = 1L;
    private Connection conexao;
    private Banda banda;
    private IntegranteJDBC dBC;

    public BandaJDBC() {
        dBC = new IntegranteJDBC();
    }


    public void nova(Banda pessoa) {
        String sql = "INSERT INTO banda (id ,localDeOrigem, nomeFantasia) VALUES(?,?,?)";
        this.conexao = (Connection) Conexao.abrirConexao();
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, banda.getId());
            statement.setString(2, banda.getLocalDeOrigem());
            statement.setString(3, banda.getNomeFantasia());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }
    }


    public List<Banda> todas() {
        // TODO Auto-generated method stub
        this.conexao = (Connection) Conexao.abrirConexao();
        List<Banda> pessoas = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM pessoa;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            ResultSet resut = statement.executeQuery();
            while (resut.next()) {
                pessoas.add(criarBanda(resut));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }
        if (pessoas.size() > 0) {
            return Collections.unmodifiableList(pessoas);
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    public void excluir(Banda banda) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM banda WHERE uuid=?";
        try {
            this.conexao = Conexao.abrirConexao();
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, banda.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }

    }


    public void atualizar(Banda banda) {
        String sql = "UPDATE pessoa SET nomeFantasia=?, localDeOrigem=? WHERE uuid=?";
        this.conexao = Conexao.abrirConexao();
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, banda.getNomeFantasia());
            statement.setString(2, banda.getNomeFantasia());
            statement.setLong(3, banda.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao(conexao);
        }

    }


    public Banda localizarPessoaComId(long id) {
        Banda banda = new Banda();
        try {
            this.conexao = Conexao.abrirConexao();
            String consulta = "SELECT * FROM banda WHERE uuid=?;";
            PreparedStatement statement = conexao.prepareStatement(consulta);
            statement.setLong(1, id);
            banda = percorrerBanda(statement);
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            banda = banda.fake();
        } finally {
            Conexao.fecharConexao(conexao);
        }

        return banda;
    }


    public List<Integrante> todosOsDepentendes() {
        // TODO Auto-generated method stub
        return dBC.todosOsIntegrantes();

    }


    public Integrante localizarDependenteComId(int uuid) {
        return dBC.localizarintegranteComId(uuid);
    }


    public void novo(Integrante integrante) {
        // TODO Auto-generated method stub
        if (integrante.getId() <= 0) {
            dBC.nova(integrante);
        } else {
            dBC.atualizar(integrante);
        }
    }

    private Banda percorrerBanda(PreparedStatement statement) {
        try {
            ResultSet executeQuery = statement.executeQuery();
            executeQuery.next();

            return criarBanda(executeQuery);
        } catch (SQLException ex) {
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Banda.fake();
    }

    private Banda criarBanda(ResultSet result) {
        Banda banda = null;

        try {
            //  result.next();
            banda = Banda.of(result.getInt("id"),
                    result.getString("nomeFantasia"),
                    result.getString("localDeOrigem"));

        } catch (SQLException ex) {
            Banda.fake();
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return banda;
    }


    public void excluirIntegrante(Integrante integrante) {
        dBC.excluir(integrante);
    }




}
