/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.DoencaAgente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: DoencaAgenteFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaAgenteFacade extends ModeloFacade<DoencaAgente>{

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(DoencaAgente object) {
        try {
            retorno = PoolConexao.getDoencaAgenteDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaAgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(DoencaAgente object) {
        try {
            retorno = PoolConexao.getDoencaAgenteDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaAgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por idDoenca e idAgente
     * @param idDoenca
     * @param idAgente
     * @return DoencaAgente
     */
    public DoencaAgente find(String idDoenca,String idAgente) {
        try {
            doencaAgente = PoolConexao.getDoencaAgenteDao().get(" where iddoenca="+idDoenca+" and idagente="+idAgente);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaAgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaAgente;
    }

    /**
     * Localiza todos
     * @return List<DoencaAgente>
     */
    public List<DoencaAgente> findAll() {
        try {
            doencaAgentes = PoolConexao.getDoencaAgenteDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaAgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaAgentes;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<DoencaAgente>
     */
    public List<DoencaAgente> findWhere(String opc) {
        try {
            doencaAgentes = PoolConexao.getDoencaAgenteDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaAgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaAgentes;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getDoencaAgenteDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaAgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     * Localiza por id
     * @param id
     * @return DoencaAgente
     */
    public DoencaAgente find(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
