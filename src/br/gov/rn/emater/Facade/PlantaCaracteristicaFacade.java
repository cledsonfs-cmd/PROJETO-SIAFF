/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe PlantaCaracteristicaFacade
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaCaracteristicaFacade extends ModeloFacade<PlantaCaracteristica>{

    /**
     * Cria/atualiza um objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(PlantaCaracteristica object) {
        try {
            retorno = PoolConexao.getPlantaCaracteristicaDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaCaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove um objeto
     * @param object
     * @return boolean
     */
    public boolean remove(PlantaCaracteristica object) {
        try {
            retorno = PoolConexao.getPlantaCaracteristicaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaCaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * localiza um objeto a partir do idPlanta e idCaracteristica
     * @param idPlanta
     * @param idCaracteristica
     * @return PlantaCaracteristica
     */
    public PlantaCaracteristica find(String idPlanta, String idCaracteristica) {
        try {
            plantaCaracteristica = PoolConexao.getPlantaCaracteristicaDao().get(" where idplanta="+idPlanta+" and idcaracteristica="+idCaracteristica);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaCaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantaCaracteristica;
    }

    /**
     * Retorna todos os objetos cadastrados
     * @return List<PlantaCaracteristica>
     */
    public List<PlantaCaracteristica> findAll() {
        try {
            plantaCaracteristicas = PoolConexao.getPlantaCaracteristicaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaCaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantaCaracteristicas;
    }

    /**
     * Localiza com a opcao where
     * @param opc
     * @return List<PlantaCaracteristica>
     */
    public List<PlantaCaracteristica> findWhere(String opc) {
        try {
            plantaCaracteristicas = PoolConexao.getPlantaCaracteristicaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaCaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantaCaracteristicas;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getPlantaCaracteristicaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaCaracteristicaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     * Localiza pelo id (Não implementado)
     * @param id
     * @return PlantaCaracteristica
     */
    public PlantaCaracteristica find(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
