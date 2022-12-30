/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Facade;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe: UsuarioFacade
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class UsuarioFacade extends ModeloFacade<Usuario>{

    /**
     * Cria/Atualiza objeto
     * @param object
     * @return boolean
     */
    @Override
    public boolean createUpdate(Usuario object) {
        try {
            this.retorno = PoolConexao.getUsuarioDao().set(object);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retorno;
    }

    /**
     * Remove Objeto
     * @param object
     * @return boolean
     */
    @Override
    public boolean remove(Usuario object) {
        try {
            this.retorno = PoolConexao.getUsuarioDao().remove(object);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retorno;
    }

    /**
     * Localiza objeto por id
     * @param id
     * @return Usuario
     */
    @Override
    public Usuario find(String id) {
        try {
            this.usuario = PoolConexao.getUsuarioDao().get(" where idusuario=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.usuario;
    }

    /**
     * Retorna todos os objetos
     * @return List<Usuario>
     */
    @Override
    public List<Usuario> findAll() {
        try {
            this.usuarios = PoolConexao.getUsuarioDao().getList();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.usuarios;
    }

    /**
     * Localiza com opcao where
     * @param opc
     * @return List<Usuario>
     */
    @Override
    public List<Usuario> findWhere(String opc) {
        try {
            this.usuarios = PoolConexao.getUsuarioDao().getList(opc);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.usuarios;
    }

    /**
     * Conta o numero de registros
     * @return int
     */
    @Override
    public int count() {
        try {
            this.registros = PoolConexao.getUsuarioDao().count();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.registros;
    }

    /**
     * Retorna uma lista contendo o nome das tabelas em que o id do usuario esta gravado
     * @param usuario
     * @return List<String>
     */
    public List<String> getTabelasOcorrenciaUsuario(Usuario usuario){
        List<String> tabelas = new ArrayList<String>();
        try {
            tabelas = PoolConexao.getUsuarioDao().getOcorrenciasDeUsuarioEmTabelas(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabelas;
    }

}
