/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.PlantaParte;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: PlantaParteFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaParteFacade extends ModeloFacade<PlantaParte>{

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(PlantaParte object) {
        try {
            retorno = PoolConexao.getPlantaParteDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove objeto
     * @param object
     * @return boolean
     */
    public boolean remove(PlantaParte object) {
        try {
            retorno = PoolConexao.getPlantaParteDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza com base em idPlanta e idParte
     * @param idPlanta
     * @param idParte
     * @return PlantaParte
     */
    public PlantaParte find(String idPlanta, String idParte) {
        try {
            plantaParte = PoolConexao.getPlantaParteDao().get(" where idplanta="+idPlanta+" and idparte="+idParte);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantaParte;
    }

    /**
     * Localiza todos
     * @return List<PlantaParte>
     */
    public List<PlantaParte> findAll() {
        try {
            plantaPartes = PoolConexao.getPlantaParteDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantaPartes;
    }

    /**
     * Localica utilizando a clausula where
     * @param opc
     * @return List<PlantaParte>
     */
    public List<PlantaParte> findWhere(String opc) {
        try {
            plantaPartes = PoolConexao.getPlantaParteDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantaPartes;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getPlantaParteDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     * Localiza por id
     * @param id
     * @return PlantaParte
     */
    public PlantaParte find(String id) {
        try {
            this.plantaParte = PoolConexao.getPlantaParteDao().get(" where idplantaparte=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaParteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.plantaParte;
    }

}
