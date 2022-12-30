/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Analise;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cledsonfs
 */
public class AnaliseFacade extends ModeloFacade<Analise> {

    @Override
    public boolean createUpdate(Analise object) {
        try {
            retorno = PoolConexao.getAnaliseDao().set(object, false);
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public boolean remove(Analise object) {
        try {
            retorno = PoolConexao.getAnaliseDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public Analise find(String id) {
        try {
            this.analise = PoolConexao.getAnaliseDao().get(" where id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.analise;
    }

    @Override
    public List<Analise> findAll() {
        try {
            this.analises = PoolConexao.getAnaliseDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.analises;
    }

    @Override
    public List<Analise> findWhere(String opc) {
        try {
            this.analises = PoolConexao.getAnaliseDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.analises;
    }

    @Override
    public int count() {
        try {
            this.registros = PoolConexao.getAnaliseDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(AnaliseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.registros;
    }
}
