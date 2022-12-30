/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Caracteristica;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe PlantaCaracteristicaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaCaracteristicaDao extends ModeloDao<PlantaCaracteristica> {

    public PlantaCaracteristicaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Planta.class);
        this.associacoes.add(Caracteristica.class);
    }

    /**
     *
     * @return List<PlantaCaracteristica>
     * @throws SQLException
     */
    public List<PlantaCaracteristica> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<PlantaCaracteristica>
     * @throws SQLException
     */
    @Override
    public List<PlantaCaracteristica> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from PlantaCaracteristica ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<PlantaCaracteristica> list = new ArrayList<PlantaCaracteristica>();
        while (rs.next()) {
            PlantaCaracteristica plantaCaracteristica = new PlantaCaracteristica();
            plantaCaracteristica.setIdPlanta(rs.getInt("idplanta"));
            plantaCaracteristica.setIdCaracteristica(rs.getInt("idcaracteristica"));
            plantaCaracteristica.setObservacao(rs.getString("observacao"));
            list.add(plantaCaracteristica);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return List<PlantaCaracteristica>
     * @throws SQLException
     */
    @Override
    public PlantaCaracteristica get(String condicaoUnica) throws SQLException {
        List<PlantaCaracteristica> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            PlantaCaracteristica pct = new PlantaCaracteristica();
            pct.setIdCaracteristica(-1);
            pct.setIdPlanta(-1);
            return pct;
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
    public void associar(PlantaCaracteristica objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Planta.class)) {
                objeto.setPlanta(PoolConexao.getPlantaDao().get(" where idplanta=" + objeto.getIdPlanta()));
            } else if (classe.equals(Caracteristica.class)) {
                objeto.setCaracteristica(PoolConexao.getCaracteristicaDao().get(" where idcaracteristica=" + objeto.getIdCaracteristica()));
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
    public Boolean set(PlantaCaracteristica objeto, boolean apenasInclusao) throws SQLException {
        PlantaCaracteristica plantaCaracteristica = get("where idplanta=" + objeto.getIdPlanta() + " and idcaracteristica=" + objeto.getIdCaracteristica());
        PreparedStatement stmt;
        if (plantaCaracteristica == null) {
            return false;
        }
        boolean existe = (plantaCaracteristica.getIdPlanta() == objeto.getIdPlanta()) && (plantaCaracteristica.getIdCaracteristica() == objeto.getIdCaracteristica());
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update PlantaCaracteristica set observacao=? where idplanta=? and idcaracteristica=?");
            stmt.setString(1, objeto.getObservacao());
            stmt.setInt(2, objeto.getIdPlanta());
            stmt.setInt(3, objeto.getIdCaracteristica());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into PlantaCaracteristica (idplanta,idcaracteristica,observacao) values (?,?,?)");
            stmt.setInt(1, objeto.getIdPlanta());
            stmt.setInt(2, objeto.getIdCaracteristica());
            stmt.setString(3, objeto.getObservacao());
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
    public Boolean remove(PlantaCaracteristica objeto) throws SQLException {
        PlantaCaracteristica plantaCaracteristica = get("where idplanta=" + objeto.getIdPlanta() + " and idcaracteristica=" + objeto.getIdCaracteristica());
        if (plantaCaracteristica == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  PlantaCaracteristica where idplanta=? and idcaracteristica=?");
            stmt.setInt(1, objeto.getIdPlanta());
            stmt.setInt(2, objeto.getIdCaracteristica());
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
        String sql = "select count(*) as numero from PlantaCaracteristica";
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
