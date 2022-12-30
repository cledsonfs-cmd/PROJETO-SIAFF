/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Amostra;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe AmostraFacade
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class AmostraFacade extends ModeloFacade<Amostra> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Amostra object) {
        try {
            retorno = PoolConexao.getAmostraDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return
     */
    public boolean remove(Amostra object) {
        try {
            retorno = PoolConexao.getAmostraDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * localiza por id
     * @param id
     * @return Amostra
     */
    public Amostra find(String id) {
        try {
            this.amostra = PoolConexao.getAmostraDao().get(" where idamostra=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.amostra;
    }

    /**
     * Localiza todos
     * @return List<Amostra>
     */
    public List<Amostra> findAll() {
        try {
            this.amostras = PoolConexao.getAmostraDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.amostras;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            this.registros = PoolConexao.getAmostraDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.registros;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Amostra>
     */
    public List<Amostra> findWhere(String opc) {
        try {
            this.amostras = PoolConexao.getAmostraDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.amostras;
    }

    public List<Amostra> findPorIdPlanta(String idPlanta) {
        try {
            this.amostras = PoolConexao.getAmostraDao().getListPorIdPlanta(idPlanta);
        } catch (SQLException ex) {
            Logger.getLogger(AmostraFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.amostras;
    }

    public List<InputStream> getInputStream() throws SQLException {
        return PoolConexao.getAmostraDao().getListStremImagens();
    }

    
}
