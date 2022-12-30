/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Amostra;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaAgente;
import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaDao extends ModeloDao<Doenca> {

    public DoencaDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(PlantaParte.class);
        this.associacoes.add(DoencaTratamento.class);
        this.associacoes.add(Amostra.class);
        this.associacoes.add(DoencaAgente.class);
        this.associacoes.add(DoencaSintoma.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Doenca>
     * @throws SQLException
     */
    public List<Doenca> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Doenca>
     * @throws SQLException
     */
    @Override
    public List<Doenca> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from Doenca ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Doenca> list = new ArrayList<Doenca>();
        while (rs.next()) {
            Doenca doenca = new Doenca();
            doenca.setIddoenca(rs.getInt("iddoenca"));
            doenca.setDescricao(rs.getString("descricao"));
            doenca.setNomecientifico(rs.getString("nomecientifico"));
            doenca.setIdPlantaParte(rs.getInt("idplantaparte"));
            doenca.setIdUsuario(rs.getInt("idusuario"));
            doenca.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(doenca);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Doenca
     * @throws SQLException
     */
    @Override
    public Doenca get(String condicaoUnica) throws SQLException {
        List<Doenca> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Doenca dt = new Doenca();
            dt.setIddoenca(-1);
            return dt;
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
    public void associar(Doenca objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(PlantaParte.class)) {
                objeto.setPlantaParte(PoolConexao.getPlantaParteDao().get(" where idplantaparte=" + objeto.getIdPlantaParte()));
            } else if (classe.equals(DoencaTratamento.class)) {
                objeto.setDoencaTratamentos(PoolConexao.getDoencaTratamentoDao().getList(" where iddoenca=" + objeto.getIddoenca()));
            } else if (classe.equals(Amostra.class)) {
                objeto.setAmostras(PoolConexao.getAmostraDao().getList(" where iddoenca=" + objeto.getIddoenca()));
            } else if (classe.equals(DoencaAgente.class)) {
                objeto.setDoencaAgentes(PoolConexao.getDoencaAgenteDao().getList(" where iddoenca=" + objeto.getIddoenca()));
            } else if (classe.equals(DoencaSintoma.class)) {
                objeto.setDoencaSintomas(PoolConexao.getDoencaSintomaDao().getList(" where iddoenca=" + objeto.getIddoenca()));
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
    public Boolean set(Doenca objeto, boolean apenasInclusao) throws SQLException {
        Doenca doenca = get("where iddoenca=" + objeto.getIddoenca());
        PreparedStatement stmt;
        if (doenca == null) {
            return false;
        }
        boolean existe = doenca.getIddoenca() == objeto.getIddoenca();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update Doenca set descricao=?,nomecientifico=?,idplantaparte=?,idusuario=?,datacadastro=? where iddoenca=?");
            stmt.setString(1, objeto.getDescricao());
            stmt.setString(2, objeto.getNomecientifico());
            stmt.setInt(3, objeto.getIdPlantaParte());
            stmt.setInt(4, objeto.getIdUsuario());
            stmt.setTimestamp(5, objeto.getDatacadastro());
            stmt.setInt(6, objeto.getIddoenca());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into Doenca (descricao,nomecientifico,idplantaparte,idusuario,datacadastro) values (?,?,?,?,?)");
            stmt.setString(1, objeto.getDescricao());
            stmt.setString(2, objeto.getNomecientifico());
            stmt.setInt(3, objeto.getIdPlantaParte());
            stmt.setInt(4, objeto.getIdUsuario());
            stmt.setTimestamp(5, objeto.getDatacadastro());
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
    public Boolean remove(Doenca objeto) throws SQLException {
        Doenca doenca = get("where iddoenca=" + objeto.getIddoenca());
        if (doenca == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from Doenca where iddoenca=?");
            stmt.setInt(1, objeto.getIddoenca());
            stmt.execute();
            stmt.close();
            return true;
        }
    }

    /**
     * Conta registro
     * @return int
     * @throws SQLException
     */
    @Override
    public int count() throws SQLException {
        int numeroRegistros = 0;
        PreparedStatement stmt;
        String sql = "select count(iddoenca) as numero from Doenca ";
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
     * @return Doenca
     * @throws SQLException
     */
    public Doenca ultimoRegistro() throws SQLException {
        return get(" where iddoenca=(select max(iddoenca) from doenca)");
    }

    /**
     *
     * @param doenca
     * @return List<String>
     * @throws SQLException
     */
    public List<String> getOcorrenciasDeDoencaEmTabelas(Doenca doenca) throws SQLException {
        PreparedStatement stmt;
        String sql = "select iddoenca, 'DoencaSintoma' as tabela from doencasintoma where iddoenca="+doenca.getIddoenca()+" "
                + "union "
                + "select iddoenca, 'DoencaAgente' as tabela from doencaagente where iddoenca="+doenca.getIddoenca()+" "
                + "union "
                + "select iddoenca, 'Amostra' as tabela from amostra where iddoenca="+doenca.getIddoenca()+" "
                + "union "
                + "select iddoenca, 'DoencaTratamento' as tabela from doencatratamento where iddoenca="+doenca.getIddoenca()+" ";
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
