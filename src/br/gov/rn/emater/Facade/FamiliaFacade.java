/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Familia;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: FamiliaFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class FamiliaFacade extends ModeloFacade<Familia> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Familia object) {
        try {
            retorno = PoolConexao.getFamiliaDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(Familia object) {
        try {
            retorno = PoolConexao.getFamiliaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Familia
     */
    public Familia find(String id) {
        try {
            familia = PoolConexao.getFamiliaDao().get(" where idfamilia=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return familia;
    }

    /**
     * Localiza todos
     * @return List<Familia>
     */
    public List<Familia> findAll() {
        try {
            familias = PoolConexao.getFamiliaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return familias;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Familia>
     */
    public List<Familia> findWhere(String opc) {
        try {
            familias = PoolConexao.getFamiliaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return familias;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getFamiliaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }
}
