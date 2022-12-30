/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe ModeloDao
 * @param <C>
 * @author cledsonfs,ururai
 * @version 1.0
 */
public abstract class ModeloDao<C> {

    /**
     *
     */
    public Conexao conexao = null;
    private Connection connection;
    /**
     *
     */
    protected List<Class> associacoes;

    /**
     *
     */
    public void abrirConexao() {
        try {
            setConnection(new Conexao().getConnection());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        try {
            if ((connection == null) || connection.isClosed()) {
                abrirConexao();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    /**
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inclui associacoes
     */
    public abstract void incluirAssociacoes();

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<C> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param objList
     * @return
     * @throws SQLException
     */
    public List<C> addList(List<C> objList) throws SQLException {
        return addList(objList, null);
    }

    /**
     *
     * @param objList
     * @param condicao
     * @return
     * @throws SQLException
     */
    public List<C> addList(List<C> objList, String condicao) throws SQLException {
        List<C> list = new ArrayList<C>();
        if(objList!=null){
            list.addAll(objList);
        }
        list.addAll(getList(condicao));
        return list;
    }

    /**
     *
     * @param condicao
     * @return
     * @throws SQLException
     */
    public abstract List<C> getList(String condicao) throws SQLException;

    /**
     *
     * @param condicaoUnica
     * @return
     * @throws SQLException
     */
    public abstract C get(String condicaoUnica) throws SQLException;

    /**
     *
     * @param objeto
     * @throws SQLException
     */
    public final void associar(C objeto) throws SQLException {
        associar(objeto, "");
    }

    /**
     *
     * @param objeto
     * @param condicao
     * @throws SQLException
     */
    public final void associar(C objeto, String condicao) throws SQLException {
        if (associacoes == null) {
            associacoes = new ArrayList<Class>();
            incluirAssociacoes();
        }
        if (associacoes == null) {
            System.out.println("Atributo associacoes não definido para a classe "
                    + this.getClass().getSimpleName());
        } else {
            associar(objeto, associacoes, condicao);
        }
    }

    /**
     *
     * @param objeto
     * @param classe
     * @throws SQLException
     */
    public final void associar(C objeto, Class classe) throws SQLException {
        associar(objeto, classe, "");
    }

    /**
     *
     * @param objeto
     * @param classe
     * @param condicao
     * @throws SQLException
     */
    public final void associar(C objeto, Class classe, String condicao) throws SQLException {
        List<Class> assoc = new ArrayList<Class>();
        assoc.add(classe);
        associar(objeto, assoc, condicao);
    }

    /**
     *
     * @param objeto
     * @param classes
     * @throws SQLException
     */
    public final void associar(C objeto, List<Class> classes) throws SQLException {
        associar(objeto, classes, "");
    }

    /**
     * Associacoes
     * @param objeto
     * @param classes
     * @param condicao
     * @throws SQLException
     */
    public abstract void associar(C objeto, List<Class> classes, String condicao) throws SQLException;

    /**
     *
     * @param objeto
     * @return C
     * @throws SQLException
     */
    public final Boolean set(C objeto) throws SQLException {
        return set(objeto, false);
    }

    /**
     *
     * @param objeto
     * @param apenasInclusao
     * @return boolean
     * @throws SQLException
     */
    public abstract Boolean set(C objeto, boolean apenasInclusao) throws SQLException;

    /**
     *
     * @param objeto
     * @return boolean
     * @throws SQLException
     */
    public abstract Boolean remove(C objeto) throws SQLException;

    /**
     *
     * @return int
     * @throws SQLException
     */
    public abstract int count() throws SQLException;
}
