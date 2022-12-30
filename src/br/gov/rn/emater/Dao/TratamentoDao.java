/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.Tratamento;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe TratamentoDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class TratamentoDao extends ModeloDao<Tratamento>{


    public TratamentoDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(DoencaTratamento.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Tratamento>
     * @throws SQLException
     */
    public List<Tratamento> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Tratamento>
     * @throws SQLException
     */
    @Override
    public List<Tratamento> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from tratamento ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Tratamento> list = new ArrayList<Tratamento>();
        while (rs.next()) {
            Tratamento tratamento = new Tratamento();
            tratamento.setIdtratamento(rs.getInt("idtratamento"));
            tratamento.setIdUsuario(rs.getInt("idusuario"));
            tratamento.setDescricao(rs.getString("descricao"));
            tratamento.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(tratamento);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Tratamento
     * @throws SQLException
     */
    @Override
    public Tratamento get(String condicaoUnica) throws SQLException {
        List<Tratamento> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Tratamento tt = new Tratamento();
            tt.setIdtratamento(-1);
            return tt;
        } else {
            return list.get(0);
        }
    }

    /**
     * Associar
     * @param objeto
     * @param classes
     * @param condicao
     * @throws SQLException
     */
    @Override
    public void associar(Tratamento objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(DoencaTratamento.class)) {
                objeto.setDoencaTratamentos(PoolConexao.getDoencaTratamentoDao().getList(" where idtratamento=" + objeto.getIdtratamento()));
            } else if (classe.equals(Usuario.class)) {
                objeto.setUsuario(PoolConexao.getUsuarioDao().get(" where idusuario=" + objeto.getIdUsuario()));
            }
        }
    }

    /**
     *
     * @param objeto
     * @param apenasInclusao
     * @return boolean
     * @throws SQLException
     */
    @Override
    public Boolean set(Tratamento objeto, boolean apenasInclusao) throws SQLException {
        Tratamento tratamento = get("where idtratamento=" + objeto.getIdtratamento());
        PreparedStatement stmt;
        if (tratamento == null) {
            return false;
        }
        boolean existe = tratamento.getIdtratamento() == objeto.getIdtratamento();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update tratamento set descricao=?,datacadastro=?,idusuario=? where idtratamento=?");
            stmt.setString(1, objeto.getDescricao());
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdtratamento());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into tratamento (descricao,datacadastro,idusuario) values (?,?,?)");
            stmt.setString(1, objeto.getDescricao());
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.execute();
            stmt.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param objeto
     * @return boolean
     * @throws SQLException
     */
    @Override
    public Boolean remove(Tratamento objeto) throws SQLException {
        Tratamento tratamento = get("where idtratamento=" + objeto.getIdtratamento());
        if (tratamento == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  tratamento where idtratamento=?");
            stmt.setInt(1, objeto.getIdtratamento());
            stmt.execute();
            stmt.close();
            return true;
        }
    }

    /**
     * Conta registros
     * @return int
     * @throws SQLException
     */
    @Override
    public int count() throws SQLException {
        int numeroRegistros = 0;
        PreparedStatement stmt;
        String sql = "select count(idtratamento) as numero from tratamento ";
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            numeroRegistros = rs.getInt("numero");
        }
        rs.close();
        stmt.close();
        return numeroRegistros;
    }
}
