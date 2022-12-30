/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.Sintoma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaSintomaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaSintomaDao extends ModeloDao<DoencaSintoma> {

    public DoencaSintomaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Doenca.class);
        this.associacoes.add(Sintoma.class);
    }

    /**
     *
     * @return List<DoencaSintoma>
     * @throws SQLException
     */
    public List<DoencaSintoma> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<DoencaSintoma>
     * @throws SQLException
     */
    @Override
    public List<DoencaSintoma> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from DoencaSintoma ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<DoencaSintoma> list = new ArrayList<DoencaSintoma>();
        while (rs.next()) {
            DoencaSintoma doencaSintoma = new DoencaSintoma();
            doencaSintoma.setIdDoenca(rs.getInt("iddoenca"));
            doencaSintoma.setIdSintoma(rs.getInt("idsintoma"));
            doencaSintoma.setObservacao(rs.getString("observacao"));
            list.add(doencaSintoma);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return DoencaSintoma
     * @throws SQLException
     */
    @Override
    public DoencaSintoma get(String condicaoUnica) throws SQLException {
        List<DoencaSintoma> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            DoencaSintoma dst = new DoencaSintoma();
            return dst;
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
    public void associar(DoencaSintoma objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Doenca.class)) {
                objeto.setDoenca(PoolConexao.getDoencaDao().get(" where iddoenca=" + objeto.getIdDoenca()));
            } else if (classe.equals(Sintoma.class)) {
                objeto.setSintoma(PoolConexao.getSintomaDao().get(" where idsintoma=" + objeto.getIdSintoma()));
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
    public Boolean set(DoencaSintoma objeto, boolean apenasInclusao) throws SQLException {
        DoencaSintoma doencaSintoma = get("where iddoenca=" + objeto.getIdDoenca()+" and idsintoma="+objeto.getIdSintoma());
        PreparedStatement stmt;
        if (doencaSintoma == null) {
            return false;
        }
        boolean existe = (doencaSintoma.getIdDoenca() == objeto.getIdDoenca()) && (doencaSintoma.getIdSintoma() == objeto.getIdSintoma());
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update DoencaSintoma set observacao=? where iddoenca=? and idsintoma=?");
            stmt.setString(1, objeto.getObservacao());
            stmt.setInt(2, objeto.getIdDoenca());
            stmt.setInt(3, objeto.getIdSintoma());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into DoencaSintoma (iddoenca,idsintoma,observacao) values (?,?,?)");
            stmt.setInt(1, objeto.getIdDoenca());
            stmt.setInt(2, objeto.getIdSintoma());
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
    public Boolean remove(DoencaSintoma objeto) throws SQLException {
        DoencaSintoma doencaSintoma = get("where iddoenca=" + objeto.getIdDoenca()+" and idsintoma="+objeto.getIdSintoma());
        if (doencaSintoma == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  DoencaSintoma where iddoenca=? and idsintoma=?");
            stmt.setInt(1, objeto.getIdDoenca());
            stmt.setInt(2, objeto.getIdSintoma());
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
        String sql = "select count(*) as numero from DoencaSintoma ";
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
