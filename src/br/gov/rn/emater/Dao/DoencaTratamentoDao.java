/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.Tratamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaTratamentoDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaTratamentoDao extends ModeloDao<DoencaTratamento> {

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Doenca.class);
        this.associacoes.add(Tratamento.class);
    }

    /**
     *
     * @return List<DoencaTratamento>
     * @throws SQLException
     */
    public List<DoencaTratamento> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<DoencaTratamento>
     * @throws SQLException
     */
    @Override
    public List<DoencaTratamento> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from DoencaTratamento ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<DoencaTratamento> list = new ArrayList<DoencaTratamento>();
        while (rs.next()) {
            DoencaTratamento doencaTratamento = new DoencaTratamento();
            doencaTratamento.setIdDoenca(rs.getInt("iddoenca"));
            doencaTratamento.setIdTratamento(rs.getInt("idtratamento"));
            doencaTratamento.setObservacao(rs.getString("observacao"));
            list.add(doencaTratamento);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return DoencaTratamento
     * @throws SQLException
     */
    @Override
    public DoencaTratamento get(String condicaoUnica) throws SQLException {
        List<DoencaTratamento> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            DoencaTratamento dtt = new DoencaTratamento();
            return dtt;
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
    public void associar(DoencaTratamento objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Doenca.class)) {
                objeto.setDoenca(PoolConexao.getDoencaDao().get(" where iddoenca=" + objeto.getIdDoenca()));
            }else if (classe.equals(Tratamento.class)) {
                objeto.setTratamento(PoolConexao.getTratamentoDao().get(" where idtratamento=" + objeto.getIdTratamento()));
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
    public Boolean set(DoencaTratamento objeto, boolean apenasInclusao) throws SQLException {
        DoencaTratamento doencaTratamento = get("where iddoenca=" + objeto.getIdDoenca()+" and idtratamento="+objeto.getIdTratamento());
        PreparedStatement stmt;
        if (doencaTratamento == null) {
            return false;
        }
        boolean existe = (doencaTratamento.getIdDoenca() == objeto.getIdDoenca()) && (doencaTratamento.getIdTratamento() == objeto.getIdTratamento());
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update DoencaTratamento set observacao=? where iddoenca=? and idtratamento=?");
            stmt.setString(1, objeto.getObservacao());
            stmt.setInt(2, objeto.getIdDoenca());
            stmt.setInt(3, objeto.getIdTratamento());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into DoencaTratamento (iddoenca,idtratamento,observacao) values (?,?,?)");
            stmt.setInt(1, objeto.getIdDoenca());
            stmt.setInt(2, objeto.getIdTratamento());
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
    public Boolean remove(DoencaTratamento objeto) throws SQLException {
        DoencaTratamento doencaTratamento = get("where iddoenca=" + objeto.getIdDoenca()+" and idtratamento="+objeto.getIdTratamento());
        if (doencaTratamento == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  DoencaTratamento where iddoenca=? and idtratamento=?");
            stmt.setInt(1, objeto.getIdDoenca());
            stmt.setInt(2, objeto.getIdTratamento());
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
        String sql = "select count(*) as numero from DoencaTratamento ";
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
