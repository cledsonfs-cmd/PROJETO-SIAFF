/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Planta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: PlantaFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaFacade extends ModeloFacade<Planta> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Planta object) {
        try {
            retorno = PoolConexao.getPlantaDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(Planta object) {
        try {
            retorno = PoolConexao.getPlantaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Planta
     */
    public Planta find(String id) {
        try {
            planta = PoolConexao.getPlantaDao().get(" where idplanta=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return planta;
    }

    /**
     * Localiza todos
     * @return List<Planta>
     */
    public List<Planta> findAll() {
        try {
            plantas = PoolConexao.getPlantaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantas;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Planta>
     */
    public List<Planta> findWhere(String opc) {

        try {
            plantas = PoolConexao.getPlantaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantas;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getPlantaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     * Retorna o ultimo id
     * @return int
     */
    public int getUltimoId() {
        Planta planta = null;
        try {
            planta = PoolConexao.getPlantaDao().ultimoRegistro();
        } catch (SQLException ex) {
            Logger.getLogger(PlantaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (planta == null) {
            return 0;
        } else {
            return planta.getIdplanta();
        }
    }

    /**
     * Retorna as ocorrencias em outras tabelas
     * @param planta
     * @return List<String>
     */
    public List<String> getTabelasOcorrenciaPlanta(Planta planta){
        List<String> tabelas = new ArrayList<String>();
        try {
            tabelas = PoolConexao.getPlantaDao().getOcorrenciasDePlantaEmTabelas(planta);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabelas;
    }
}
