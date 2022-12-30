/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Genero;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: GeneroFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class GeneroFacade extends ModeloFacade<Genero> {

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(Genero object) {
        try {
            retorno = PoolConexao.getGeneroDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(GeneroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(Genero object) {
        try {
            retorno = PoolConexao.getGeneroDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(GeneroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por id
     * @param id
     * @return Genero
     */
    public Genero find(String id) {
        try {
            genero = PoolConexao.getGeneroDao().get(" where idgenero=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(GeneroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genero;
    }

    /**
     * Localiza todos
     * @return List<Genero>
     */
    public List<Genero> findAll() {
        try {
            generos = PoolConexao.getGeneroDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(GeneroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generos;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<Genero>
     */
    public List<Genero> findWhere(String opc) {
        try {
            generos = PoolConexao.getGeneroDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(GeneroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generos;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getGeneroDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(GeneroFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }
}
