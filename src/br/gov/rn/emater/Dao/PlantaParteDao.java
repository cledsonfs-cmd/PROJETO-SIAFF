/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.Parte;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaParte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe PlantaParteDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaParteDao extends ModeloDao<PlantaParte>{

    public PlantaParteDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Parte.class);
        this.associacoes.add(Planta.class);
        this.associacoes.add(Doenca.class);
    }

    /**
     *
     * @return List<PlantaParte>
     * @throws SQLException
     */
    public List<PlantaParte> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<PlantaParte>
     * @throws SQLException
     */
    @Override
    public List<PlantaParte> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from PlantaParte ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<PlantaParte> list = new ArrayList<PlantaParte>();
        while (rs.next()) {
            PlantaParte plantaParte = new PlantaParte();
            plantaParte.setIdPlantaParte(rs.getInt("idplantaparte"));
            plantaParte.setIdPlanta(rs.getInt("idplanta"));
            plantaParte.setIdParte(rs.getInt("idparte"));
            plantaParte.setObservacao(rs.getString("observacao"));
            list.add(plantaParte);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return PlantaParte
     * @throws SQLException
     */
    @Override
    public PlantaParte get(String condicaoUnica) throws SQLException {
        List<PlantaParte> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            PlantaParte ppt = new PlantaParte();
            ppt.setIdPlantaParte(-1);
            return ppt;
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
    public void associar(PlantaParte objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Parte.class)) {
                objeto.setParte(PoolConexao.getParteDao().get(" where idparte=" + objeto.getIdParte()));
            }else if (classe.equals(Planta.class)) {
                objeto.setPlanta(PoolConexao.getPlantaDao().get(" where idplanta=" + objeto.getIdPlanta()));
            }else if (classe.equals(Doenca.class)) {
                objeto.setDoenca(PoolConexao.getDoencaDao().getList(" where idplantaparte=" + objeto.getIdPlantaParte()));
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
    public Boolean set(PlantaParte objeto, boolean apenasInclusao) throws SQLException {
        PlantaParte plantaParte = get(" where idplantaparte="+objeto.getIdPlantaParte());
        PreparedStatement stmt;
        if (plantaParte == null) {
            return false;
        }
        boolean existe = plantaParte.getIdPlantaParte() == objeto.getIdPlantaParte();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update PlantaParte set idplanta=?,idparte=?,observacao=? where idplantaparte=?");
            stmt.setInt(1, objeto.getIdPlanta());
            stmt.setInt(2, objeto.getIdParte());
            stmt.setString(3, objeto.getObservacao());
            stmt.setInt(4, objeto.getIdPlantaParte());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into PlantaParte (idplanta,idparte,observacao) values (?,?,?)");
            stmt.setInt(1, objeto.getIdPlanta());
            stmt.setInt(2, objeto.getIdParte());
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
    public Boolean remove(PlantaParte objeto) throws SQLException {
        PlantaParte plantaParte = get("where idplantaparte="+objeto.getIdPlanta());
        if (plantaParte == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  PlantaParte where idplantaparte=?");
            stmt.setInt(1, objeto.getIdPlantaParte());
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
        String sql = "select count(*) as numero from PlantaParte ";
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
