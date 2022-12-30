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
 * Classe GeneroDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class GeneroDao extends ModeloDao<Genero> {

    public GeneroDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Familia.class);
        this.associacoes.add(Usuario.class);
        this.associacoes.add(Planta.class);
    }

    /**
     *
     * @return List<Genero>
     * @throws SQLException
     */
    public List<Genero> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Genero>
     * @throws SQLException
     */
    @Override
    public List<Genero> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from genero ";
        if (condicao != null) {
            sql += condicao;
        }        
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Genero> list = new ArrayList<Genero>();
        while (rs.next()) {
            Genero genero = new Genero();
            genero.setIdgenero(rs.getInt("idgenero"));
            genero.setIdFamilia(rs.getInt("idfamilia"));
            genero.setIdUsuario(rs.getInt("idusuario"));
            genero.setDescricao(rs.getString("descricao"));
            genero.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(genero);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Genero
     * @throws SQLException
     */
    @Override
    public Genero get(String condicaoUnica) throws SQLException {
        List<Genero> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Genero gt = new Genero();
            gt.setIdgenero(-1);
            return gt;
        } else {
            return list.get(0);
        }
    }

    /**
     * Executa associacoes
     * @param objeto
     * @param classes
     * @param condicao
     * @throws SQLException
     */
    @Override
    public void associar(Genero objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Familia.class)) {
                objeto.setFamilia(PoolConexao.getFamiliaDao().get(" where idfamilia=" + objeto.getIdFamilia()));
            } else if (classe.equals(Usuario.class)) {
                objeto.setUsuario(PoolConexao.getUsuarioDao().get(" where idusuario=" + objeto.getIdUsuario()));
            } else if (classe.equals(Planta.class)) {
                objeto.setPlantas(PoolConexao.getPlantaDao().getList(" where idgenero=" + objeto.getIdgenero()));
            }
        }
    }

    /**
     *
     * @param objeto
     * @param apenasInclusao
     * @return booelan
     * @throws SQLException
     */
    @Override
    public Boolean set(Genero objeto, boolean apenasInclusao) throws SQLException {
        Genero genero = get("where idgenero=" + objeto.getIdgenero());
        PreparedStatement stmt;
        if (genero == null) {
            return false;
        }
        boolean existe = genero.getIdgenero()==objeto.getIdgenero();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update genero set descricao=?,datacadastro=?,idusuario=?,idfamilia=? where idgenero=?");
            stmt.setString(1, objeto.getDescricao());
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdFamilia());
            stmt.setInt(5, objeto.getIdgenero());
            stmt.execute();
            stmt.close();
            return true;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into genero (descricao,datacadastro,idusuario,idfamilia) values (?,?,?,?)");
            stmt.setString(1, objeto.getDescricao());
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdFamilia());
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
     * @return booelan
     * @throws SQLException
     */
    @Override
    public Boolean remove(Genero objeto) throws SQLException {
        Genero genero = get("where idgenero=" + objeto.getIdgenero());
        if (genero == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  genero where idgenero=?");
            stmt.setInt(1, objeto.getIdgenero());
            stmt.execute();
            stmt.close();
            return true;
        }
    }

    /**
     * conta o numero de registros
     * @return int
     * @throws SQLException
     */
    @Override
    public int count() throws SQLException {
        int numeroRegistros = 0;
        PreparedStatement stmt;
        String sql = "select count(idgenero) as numero from Genero ";
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
