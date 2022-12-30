/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.DoencaTratamento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: DoencaTratamentoFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaTratamentoFacade extends ModeloFacade<DoencaTratamento> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(DoencaTratamento object) {
        try {
            retorno = PoolConexao.getDoencaTratamentoDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaTratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(DoencaTratamento object) {
        try {
            retorno = PoolConexao.getDoencaTratamentoDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaTratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

   /**
    * Localiza por idDoenca e idTratamento
    * @param idDoenca
    * @param idTratamento
    * @return DoencaTratamento
    */
   public DoencaTratamento find(String idDoenca, String idTratamento) {
        try {
            doencaTratamento = PoolConexao.getDoencaTratamentoDao().get(" where iddoenca=" + idDoenca + " and idtratamento=" + idTratamento);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaTratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaTratamento;
    }

   /**
     * Localiza todos
     * @return List<DoencaTratamento>
     */
   public List<DoencaTratamento> findAll() {
        try {
            doencaTratamentos = PoolConexao.getDoencaTratamentoDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaTratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaTratamentos;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<DoencaTratamento>
     */
    public List<DoencaTratamento> findWhere(String opc) {
        try {
            doencaTratamentos = PoolConexao.getDoencaTratamentoDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaTratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaTratamentos;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getDoencaTratamentoDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaTratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     * Localiza por id
     * @param id
     * @return DoencaTratamento
     */
    public DoencaTratamento find(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
