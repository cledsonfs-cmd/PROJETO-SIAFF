/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Familia;
import br.gov.rn.emater.Classes.Genero;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe FamiliaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class FamiliaDao extends ModeloDao<Familia> {

    public FamiliaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Genero.class);
        this.associacoes.add(Planta.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Familia>
     * @throws SQLException
     */
    public List<Familia> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Familia>
     * @throws SQLException
     */
    @Override
    public List<Familia> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from familia ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Familia> list = new ArrayList<Familia>();
        while (rs.next()) {
            Familia familia = new Familia();
            familia.setIdfamilia(rs.getInt("idfamilia"));
            familia.setIdUsuario(rs.getInt("idusuario"));
            familia.setDescricao(rs.getString("descricao"));            
            familia.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(familia);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Familia
     * @throws SQLException
     */
    @Override
    public Familia get(String condicaoUnica) throws SQLException {
        List<Familia> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Familia ft = new Familia();
            ft.setIdfamilia(-1);
            return ft;
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
    public void associar(Familia objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Genero.class)) {
                objeto.setGeneros(PoolConexao.getGeneroDao().getList(" where idfamilia=" + objeto.getIdfamilia()));
            }else if (classe.equals(Planta.class)) {
                objeto.setPlantas(PoolConexao.getPlantaDao().getList(" where idfamilia=" + objeto.getIdfamilia()));
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
    public Boolean set(Familia objeto, boolean apenasInclusao) throws SQLException {
        Familia familia = get("where idfamilia=" + objeto.getIdfamilia());
        PreparedStatement stmt;
        if (familia == null) {
            return false;
        }
        boolean existe = familia.getIdfamilia()==objeto.getIdfamilia();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update familia set descricao=?,datacadastro=?,idusuario=? where idfamilia=?");
            stmt.setString(1, objeto.getDescricao());            
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdfamilia());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into familia (descricao,datacadastro,idusuario) values (?,?,?)");
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
    public Boolean remove(Familia objeto) throws SQLException {
        Familia familia = get("where idfamilia=" + objeto.getIdfamilia());
        if (familia == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  familia where idfamilia=?");
            stmt.setInt(1, objeto.getIdfamilia());
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
        String sql = "select count(idfamilia) as numero from Familia ";
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
