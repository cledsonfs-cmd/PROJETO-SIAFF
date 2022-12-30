/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.DoencaSintoma;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: DoencaSintomaFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaSintomaFacade extends ModeloFacade<DoencaSintoma>{

    /**
     * Cria/Altera o objeto
     * @param object
     * @return boolean
     */
    public boolean createUpdate(DoencaSintoma object) {
        try {
            retorno = PoolConexao.getDoencaSintomaDao().set(object, true);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaSintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Remove o objeto
     * @param object
     * @return boolean
     */
    public boolean remove(DoencaSintoma object) {
        try {
            retorno = PoolConexao.getDoencaSintomaDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaSintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Localiza por idDoenca e idSintoma
     * @param idDoenca
     * @param idSintoma
     * @return DoencaSintoma
     */
    public DoencaSintoma find(String idDoenca, String idSintoma) {
        try {
            doencaSintoma = PoolConexao.getDoencaSintomaDao().get(" where iddoenca="+idDoenca+" and idsintoma="+idSintoma);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaSintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaSintoma;
    }

    /**
     * Localiza todos
     * @return List<DoencaSintoma>
     */
    public List<DoencaSintoma> findAll() {
        try {
            doencaSintomas = PoolConexao.getDoencaSintomaDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaSintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaSintomas;
    }

    /**
     * Localiza utilizando a clausula where
     * @param opc
     * @return List<DoencaSintoma>
     */
    public List<DoencaSintoma> findWhere(String opc) {
        try {
            doencaSintomas = PoolConexao.getDoencaSintomaDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(DoencaSintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doencaSintomas;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    public int count() {
        try {
            registros = PoolConexao.getDoencaSintomaDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(DoencaSintomaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    /**
     * Localiza por id
     * @param id
     * @return DoencaSintoma
     */
    public DoencaSintoma find(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
