/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Parte;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ParteDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class ParteDao extends ModeloDao<Parte> {

    public ParteDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(PlantaParte.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Parte>
     * @throws SQLException
     */
    public List<Parte> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Parte>
     * @throws SQLException
     */
    @Override
    public List<Parte> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from Parte ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Parte> list = new ArrayList<Parte>();
        while (rs.next()) {
            Parte parte = new Parte();
            parte.setIdparte(rs.getInt("idparte"));
            parte.setIdUsuario(rs.getInt("idusuario"));
            parte.setDescricao(rs.getString("descricao"));
            parte.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(parte);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Parte
     * @throws SQLException
     */
    @Override
    public Parte get(String condicaoUnica) throws SQLException {
        List<Parte> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Parte pt = new Parte();
            pt.setIdparte(-1);
            return pt;
        } else {
            return list.get(0);
        }
    }

    /**
     *
     * @param objeto
     * @param classes
     * @param condicao
     * @throws SQLException
     */
    @Override
    public void associar(Parte objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(PlantaParte.class)) {
                objeto.setPlantaPartes(PoolConexao.getPlantaParteDao().getList(" where idparte=" + objeto.getIdparte()));
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
    public Boolean set(Parte objeto, boolean apenasInclusao) throws SQLException {
        Parte parte = get("where idparte=" + objeto.getIdparte());
        PreparedStatement stmt;
        if (parte == null) {
            return false;
        }
        boolean existe = parte.getIdparte() == objeto.getIdparte();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update Parte set descricao=?,datacadastro=?,idusuario=? where idparte=?");
            stmt.setString(1, objeto.getDescricao());
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdparte());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into Parte (descricao,datacadastro,idusuario) values (?,?,?)");
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
    public Boolean remove(Parte objeto) throws SQLException {
        Parte parte = get("where idparte=" + objeto.getIdparte());
        if (parte == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  Parte where idparte=?");
            stmt.setInt(1, objeto.getIdparte());
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
        String sql = "select count(idparte) as numero from Parte ";
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
