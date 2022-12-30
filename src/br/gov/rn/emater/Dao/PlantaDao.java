/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Genero;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe PlantaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaDao extends ModeloDao<Planta> {

    public PlantaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(PlantaParte.class);
        this.associacoes.add(PlantaCaracteristica.class);
        this.associacoes.add(Genero.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Planta>
     * @throws SQLException
     */
    public List<Planta> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Planta>
     * @throws SQLException
     */
    @Override
    public List<Planta> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from Planta ";
        if (condicao != null) {
            sql += condicao;
        }

        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Planta> list = new ArrayList<Planta>();
        while (rs.next()) {
            Planta planta = new Planta();
            planta.setIdplanta(rs.getInt("idplanta"));
            planta.setIdGenero(rs.getInt("idgenero"));
            planta.setIdUsuario(rs.getInt("idusuario"));
            planta.setNomecientifico(rs.getString("nomecientifico"));
            planta.setDescricao(rs.getString("descricao"));
            planta.setNomepopular(rs.getString("nomepopular"));
            planta.setDataCadastro(rs.getTimestamp("datacadastro"));
            list.add(planta);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Planta
     * @throws SQLException
     */
    @Override
    public Planta get(String condicaoUnica) throws SQLException {
        List<Planta> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Planta pt = new Planta();
            pt.setIdplanta(-1);
            return pt;
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
    public void associar(Planta objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(PlantaParte.class)) {
                objeto.setPlantaParte(PoolConexao.getPlantaParteDao().getList(" where idplanta=" + objeto.getIdplanta()));
            } else if (classe.equals(PlantaCaracteristica.class)) {
                objeto.setPlantaCaracteristicas(PoolConexao.getPlantaCaracteristicaDao().getList(" where idplanta=" + objeto.getIdplanta()));
            } else if (classe.equals(Genero.class)) {
                objeto.setGenero(PoolConexao.getGeneroDao().get(" where idgenero=" + objeto.getIdGenero()));
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
    public Boolean set(Planta objeto, boolean apenasInclusao) throws SQLException {
        Planta planta = get(" where idplanta=" + objeto.getIdplanta());
        PreparedStatement stmt;
        if (planta == null) {
            return false;
        }        
        boolean existe = planta.getIdplanta() == objeto.getIdplanta();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update Planta set idgenero=?,descricao=?,nomecientifico=?,nomepopular=?,datacadastro=?,idusuario=? where idplanta=?");
            stmt.setInt(1, objeto.getIdGenero());
            stmt.setString(2, objeto.getDescricao());
            stmt.setString(3, objeto.getNomecientifico());
            stmt.setString(4, objeto.getNomepopular());
            stmt.setTimestamp(5, objeto.getDataCadastro());
            stmt.setInt(6, objeto.getIdUsuario());
            stmt.setInt(7, objeto.getIdplanta());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into planta (idgenero,descricao,nomecientifico,nomepopular,datacadastro,idusuario) values (?,?,?,?,?,?)");
            stmt.setInt(1, objeto.getIdGenero());
            stmt.setString(2, objeto.getDescricao());
            stmt.setString(3, objeto.getNomecientifico());
            stmt.setString(4, objeto.getNomepopular());
            stmt.setTimestamp(5, objeto.getDataCadastro());
            stmt.setInt(6, objeto.getIdUsuario());
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
    public Boolean remove(Planta objeto) throws SQLException {
        Planta planta = get("where idplanta=" + objeto.getIdplanta());
        if (planta == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  Planta where idplanta=?");
            stmt.setInt(1, objeto.getIdplanta());
            stmt.execute();
            stmt.close();
            return true;
        }
    }

    /**
     * Associar
     * @return int
     * @throws SQLException
     */
    @Override
    public int count() throws SQLException {
        int numeroRegistros = 0;
        PreparedStatement stmt;
        String sql = "select count(idplanta) as numero from Planta ";
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            numeroRegistros = rs.getInt("numero");
        }
        rs.close();
        stmt.close();
        return numeroRegistros;
    }

    /**
     *
     * @return Planta
     * @throws SQLException
     */
    public Planta ultimoRegistro() throws SQLException {
        return get(" where idplanta=(select max(idplanta) from planta)");
    }

    /**
     *
     * @param planta
     * @return List<String>
     * @throws SQLException
     */
    public List<String> getOcorrenciasDePlantaEmTabelas(Planta planta) throws SQLException {
        PreparedStatement stmt;
        String sql = "select idplanta, 'PlantaCaracteristica' as tabela from plantacaracteristica where idplanta="+planta.getIdplanta()+" "
                + "union "
                + "select idplanta, 'Plantaparte' as tabela from plantaparte where idplanta="+planta.getIdplanta()+" ";
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            String linha = rs.getString("tabela");
            list.add(linha);
        }
        rs.close();
        stmt.close();
        return list;
    }
}
