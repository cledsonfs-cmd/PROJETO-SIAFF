/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.Sintoma;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe: SintomaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class SintomaDao extends ModeloDao<Sintoma> {

    public SintomaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(DoencaSintoma.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Sintoma>
     * @throws SQLException
     */
    public List<Sintoma> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Sintoma>
     * @throws SQLException
     */
    @Override
    public List<Sintoma> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from sintoma ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Sintoma> list = new ArrayList<Sintoma>();
        while (rs.next()) {
            Sintoma sintoma = new Sintoma();
            sintoma.setIdsintoma(rs.getInt("idsintoma"));
            sintoma.setIdUsuario(rs.getInt("idusuario"));
            sintoma.setDescricao(rs.getString("descricao"));
            sintoma.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(sintoma);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Sintoma
     * @throws SQLException
     */
    @Override
    public Sintoma get(String condicaoUnica) throws SQLException {
        List<Sintoma> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Sintoma st = new Sintoma();
            st.setIdsintoma(-1);
            return st;
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
    public void associar(Sintoma objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(DoencaSintoma.class)) {
                objeto.setDoencaSintoma(PoolConexao.getDoencaSintomaDao().getList(" where idsintoma=" + objeto.getIdsintoma()));
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
    public Boolean set(Sintoma objeto, boolean apenasInclusao) throws SQLException {
        Sintoma sintoma = get("where idsintoma=" + objeto.getIdsintoma());
        PreparedStatement stmt;
        if (sintoma == null) {
            return false;
        }
        boolean existe = sintoma.getIdsintoma() == objeto.getIdsintoma();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update sintoma set descricao=?,datacadastro=?,idusuario=? where idsintoma=?");
            stmt.setString(1, objeto.getDescricao());            
            stmt.setTimestamp(2, objeto.getDatacadastro());
            stmt.setInt(3, objeto.getIdUsuario());
            stmt.setInt(4, objeto.getIdsintoma());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into sintoma (descricao,datacadastro,idusuario) values (?,?,?)");
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
    public Boolean remove(Sintoma objeto) throws SQLException {
        Sintoma sintoma = get("where idsintoma=" + objeto.getIdsintoma());
        if (sintoma == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  sintoma where idsintoma=?");
            stmt.setInt(1, objeto.getIdsintoma());
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
        String sql = "select count(idsintoma) as numero from sintoma ";
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
