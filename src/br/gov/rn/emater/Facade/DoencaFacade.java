/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Doenca;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe DoencaFacade
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaFacade extends ModeloFacade<Doenca>{

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Doenca object) {
        try {
            retorno = PoolConexao.getDoencaDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return
     */
    public boolean remove(Doenca object) {
        try {
            retorno = PoolConexao.getDoencaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Doenca
     */
    public Doenca find(String id) {
        try {
            doenca = PoolConexao.getDoencaDao().get(" where iddoenca="+id);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doenca;
    }

    /**
     * Localiza todos
     * @return List<Doenca>
     */
    public List<Doenca> findAll() {
        try {
            doencas = PoolConexao.getDoencaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencas;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Doenca>
     */
    public List<Doenca> findWhere(String opc) {
        try {
            doencas = PoolConexao.getDoencaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencas;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getDoencaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     *
     * @return
     */
    public int getUltimoId() {
        Doenca doenca = null;
        try {
            doenca = PoolConexao.getDoencaDao().ultimoRegistro();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (doenca == null) {
            return 0;
        } else {
            return doenca.getIddoenca();
        }
    }

    /**
     *
     * @param doenca
     * @return
     */
    public List<String> getTabelasOcorrenciaDoenca(Doenca doenca){
        List<String> tabelas = new ArrayList<String>();
        try {
            tabelas = PoolConexao.getDoencaDao().getOcorrenciasDeDoencaEmTabelas(doenca);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabelas;
    }

}
