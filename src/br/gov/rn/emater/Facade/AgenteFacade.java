/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Agente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: AgenteFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class AgenteFacade extends ModeloFacade<Agente> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Agente object) {
        try {
            retorno = PoolConexao.getAgenteDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(AgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    

    /**
     * Remove o objeto
     * @param object
     * @return
     */
    public boolean remove(Agente object) {
        try {
            retorno = PoolConexao.getAgenteDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(AgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Agente
     */
    public Agente find(String id) {
        try {
            this.agente = PoolConexao.getAgenteDao().get(" where idagente=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(AgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.agente;
    }

    /**
     * Localiza todos
     * @return List<Agente>
     */
    public List<Agente> findAll() {
        try {
            this.agentes = PoolConexao.getAgenteDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(AgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.agentes;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            this.registros = PoolConexao.getAgenteDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(AgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.registros;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Agente>
     */
    public List<Agente> findWhere(String opc) {
        try {
            this.agentes = PoolConexao.getAgenteDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(AgenteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.agentes;
    }
}
