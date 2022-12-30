/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Sintoma;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: SintomaFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class SintomaFacade extends ModeloFacade<Sintoma>{

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Sintoma object) {
        try {
            retorno = PoolConexao.getSintomaDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(SintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(Sintoma object) {
        try {
            retorno = PoolConexao.getSintomaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(SintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Sintoma
     */
    public Sintoma find(String id) {
        try {
            sintoma = PoolConexao.getSintomaDao().get(" where idsintoma="+id);
        } catch (SQLException ex) {
            Logger.getLogger(SintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sintoma;
    }

    /**
     * Localiza todos
     * @return List<Sintoma>
     */
    public List<Sintoma> findAll() {
        try {
            sintomas = PoolConexao.getSintomaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(SintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sintomas;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Sintoma>
     */
    public List<Sintoma> findWhere(String opc) {
        try {
            sintomas = PoolConexao.getSintomaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(SintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sintomas;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getSintomaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(SintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

}
