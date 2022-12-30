/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Agente;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaAgente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaAgenteDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaAgenteDao extends ModeloDao<DoencaAgente> {

    public DoencaAgenteDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Agente.class);
        this.associacoes.add(Doenca.class);
    }

    /**
     *
     * @return List<DoencaAgente>
     * @throws SQLException
     */
    public List<DoencaAgente> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<DoencaAgente>
     * @throws SQLException
     */
    @Override
    public List<DoencaAgente> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from DoencaAgente ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<DoencaAgente> list = new ArrayList<DoencaAgente>();
        while (rs.next()) {
            DoencaAgente doencaAgente = new DoencaAgente();
            doencaAgente.setIdAgente(rs.getInt("idagente"));
            doencaAgente.setIdDoenca(rs.getInt("iddoenca"));
            list.add(doencaAgente);
        }
        rs.close();
        stmt.close();
        return list;
    }
   
    /**
     *
     * @param condicaoUnica
     * @return DoencaAgente
     * @throws SQLException
     */
    @Override
    public DoencaAgente get(String condicaoUnica) throws SQLException {
        List<DoencaAgente> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            DoencaAgente dat = new DoencaAgente();
            dat.setIdAgente(-1);
            dat.setIdDoenca(-1);
            return dat;
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
    public void associar(DoencaAgente objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Agente.class)) {
                objeto.setAgente(PoolConexao.getAgenteDao().get(" where idagente=" + objeto.getIdAgente()));
            }else if (classe.equals(Doenca.class)) {
                objeto.setDoenca(PoolConexao.getDoencaDao().get(" where iddoenca=" + objeto.getIdDoenca()));
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
    public Boolean set(DoencaAgente objeto, boolean apenasInclusao) throws SQLException {
        DoencaAgente doencaAgente = get("where idagente=" + objeto.getIdAgente() + " and iddoenca=" + objeto.getIdDoenca());
        PreparedStatement stmt;
        if (doencaAgente == null) {
            return false;
        }
        boolean existe = (doencaAgente.getIdAgente() == objeto.getIdAgente()) && (doencaAgente.getIdDoenca() == objeto.getIdDoenca());
        if (existe && (!apenasInclusao)) {
            System.out.println("UPDATE NÃO SE APLICA A CLASSE DOENCAAGENTE");
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into DoencaAgente (idagente,iddoenca) values (?,?)");
            stmt.setInt(1, objeto.getIdAgente());
            stmt.setInt(2, objeto.getIdDoenca());
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
    public Boolean remove(DoencaAgente objeto) throws SQLException {
        DoencaAgente doencaAgente = get(" where idagente=" + objeto.getIdAgente() + " and iddoenca=" + objeto.getIdDoenca());
        if (doencaAgente.getIdAgente() == objeto.getIdAgente() && doencaAgente.getIdDoenca() == objeto.getIdDoenca()) {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  DoencaAgente where idagente=? and iddoenca=?");
            stmt.setInt(1, objeto.getIdAgente());
            stmt.setInt(2, objeto.getIdDoenca());
            stmt.execute();
            stmt.close();
            return true;
        } else {
            return false;
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
        String sql = "select count(*) as numero from DoencaAgente ";
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
