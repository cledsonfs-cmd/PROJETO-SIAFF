/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Caracteristica;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe CaracteristicaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class CaracteristicaDao extends ModeloDao<Caracteristica> {

    public CaracteristicaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Usuario.class);
        this.associacoes.add(PlantaCaracteristica.class);
    }

    /**
     *
     * @return List<Caracteristica>
     * @throws SQLException
     */
    public List<Caracteristica> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Caracteristica>
     * @throws SQLException
     */
    @Override
    public List<Caracteristica> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from caracteristica ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Caracteristica> list = new ArrayList<Caracteristica>();
        while (rs.next()) {
            Caracteristica caracteristica = new Caracteristica();
            caracteristica.setIdcaracteristica(rs.getInt("idcaracteristica"));
            caracteristica.setIdUsuario(rs.getInt("idusuario"));
            caracteristica.setDescricao(rs.getString("descricao"));
            caracteristica.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(caracteristica);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Caracteristica
     * @throws SQLException
     */
    @Override
    public Caracteristica get(String condicaoUnica) throws SQLException {
        List<Caracteristica> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Caracteristica ct = new Caracteristica();
            ct.setIdcaracteristica(-1);
            return ct;
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
    public void associar(Caracteristica objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(PlantaCaracteristica.class)) {
                objeto.setPlantaCaracteristicas(PoolConexao.getPlantaCaracteristicaDao().getList(" where idagente=" + objeto.getIdcaracteristica()));
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
    public Boolean set(Caracteristica objeto, boolean apenasInclusao) throws SQLException {
        Caracteristica caracteristica = get(" where idcaracteristica=" + objeto.getIdcaracteristica());
        PreparedStatement stmt;
        if (caracteristica == null) {
            return false;
        }
        boolean existe = caracteristica.getIdcaracteristica() == objeto.getIdcaracteristica();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update caracteristica set descricao=?,datacadastro=?,idusuario=? where idcaracteristica=?");
            stmt.setString(1, objeto.getDescricao());
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdcaracteristica());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into caracteristica (descricao,datacadastro,idusuario) values (?,?,?)");
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
    public Boolean remove(Caracteristica objeto) throws SQLException {
        Caracteristica caracteristica = get(" where idcaracteristica=" + objeto.getIdcaracteristica());
        if (caracteristica == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from caracterisitca where idcaracteristica=?");
            stmt.setInt(1, objeto.getIdcaracteristica());
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
        String sql = "select count(idcaracteristica) as numero from caracteristica ";
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
