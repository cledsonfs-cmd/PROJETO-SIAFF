/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Tratamento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: TratamentoFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class TratamentoFacade extends ModeloFacade<Tratamento> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Tratamento object) {
        try {
            retorno = PoolConexao.getTratamentoDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(Tratamento object) {
        try {
            retorno = PoolConexao.getTratamentoDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Tratamento
     */
    public Tratamento find(String id) {
        try {
            tratamento = PoolConexao.getTratamentoDao().get(" where idtratamento=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tratamento;
    }

    /**
     * Localiza todos
     * @return List<Tratamento>
     */
    public List<Tratamento> findAll() {
        try {
            tratamentos = PoolConexao.getTratamentoDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tratamentos;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Tratamento>
     */
    public List<Tratamento> findWhere(String opc) {
        try {
            tratamentos = PoolConexao.getTratamentoDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tratamentos;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getTratamentoDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }
}
