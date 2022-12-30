/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Parte;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe ParteFacade
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class ParteFacade extends ModeloFacade<Parte>{

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Parte object) {
        try {
            retorno = PoolConexao.getParteDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(ParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return
     */
    public boolean remove(Parte object) {
        try {
            retorno = PoolConexao.getParteDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(ParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Parte
     */
    public Parte find(String id) {
        try {
            parte = PoolConexao.getParteDao().get(" where idparte="+id);
        } catch (SQLException ex) {
            Logger.getLogger(ParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parte;
    }

    /**
     * Localiza todos
     * @return List<Parte>
     */
    public List<Parte> findAll() {
        try {
            partes = PoolConexao.getParteDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(ParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partes;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Parte>
     */
    public List<Parte> findWhere(String opc) {
        try {
            partes = PoolConexao.getParteDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(ParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partes;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getParteDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(ParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

}
