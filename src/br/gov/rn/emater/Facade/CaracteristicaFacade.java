/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Caracteristica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: CaracteristicaFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class CaracteristicaFacade extends ModeloFacade<Caracteristica> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Caracteristica object) {
        try {
            retorno = PoolConexao.getCaracteristicaDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(Caracteristica object) {
        try {
            retorno = PoolConexao.getCaracteristicaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Caracteristica
     */
    public Caracteristica find(String id) {
        try {
            caracteristica = PoolConexao.getCaracteristicaDao().get(" where idcaracteristica=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caracteristica;
    }

    /**
     * Localiza todos
     * @return List<Caracteristica>
     */
    public List<Caracteristica> findAll() {
        try {
            caracteristicas = PoolConexao.getCaracteristicaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caracteristicas;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Caracteristica>
     */
    public List<Caracteristica> findWhere(String opc) {
        try {
            caracteristicas = PoolConexao.getCaracteristicaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caracteristicas;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getCaracteristicaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }
}
