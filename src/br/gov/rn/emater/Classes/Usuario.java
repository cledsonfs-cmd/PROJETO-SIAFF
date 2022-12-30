/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.UsuarioDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Usuario do sistema
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Usuario extends Modelo<UsuarioDao> {

    private int idUsuario = 0;
    private String login = "";
    private String senha;
    private boolean administrador;
    private boolean operador;
    private boolean usuario;
    private boolean especialista;
    private Timestamp datacadastro;
    private List<Amostra> amostras;
    private List<Doenca> doencas;

    /**
     * Retorna o id do usuario
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Retorna o login do usuario
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Seta o login do usuario
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna o Senha do usuario
     * @return String
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Sera o Senha do usuario
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Testa se o usuario é do tipo Administrador
     * @return boolean
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * Seta o usuario como Administrador
     * @param administrador
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * Testa se o usuario é do tipo Operador
     * @return boolean
     */
    public boolean isOperador() {
        return operador;
    }

    /**
     * Seta o usuario como Operador
     * @param operador
     */
    public void setOperador(boolean operador) {
        this.operador = operador;
    }

    /**
     * Testa se o usuario é do tipo Usuario
     * @return boolean
     */
    public boolean isUsuario() {
        return usuario;
    }

    /**
     * Seta o usuario como Usuario
     * @param usuario
     */
    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }

    /**
     * Testa se o usuario é do tipo Especialista
     * @return boolean
     */
    public boolean isEspecialista() {
        return especialista;
    }

    /**
     * Seta o usuario como Especialista
     * @param especialista
     */
    public void setEspecialista(boolean especialista) {
        this.especialista = especialista;
    }

    /**
     * Retorna uma instancia de UsuarioDao com base no PoolConexao
     * @return UsuarioDao
     */
    @Override
    public UsuarioDao newDao() {
        return PoolConexao.getUsuarioDao();
    }

    /**
     * Retorna a data de cadastro do usuario
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro do usuario
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna uma lista de Amostras
     * @return List<Amostra>
     */
    public List<Amostra> getAmostras() {
        return amostras;
    }

    /**
     * Seta uma lista de Amostras
     * @param amostras
     */
    public void setAmostras(List<Amostra> amostras) {
        this.amostras = amostras;
    }

    /**
     * Retorna uma lista de Doencas
     * @return List<Doenca>
     */
    public List<Doenca> getDoencas() {
        return doencas;
    }

    /**
     * Seta uma lista de Doencas
     * @param doencas
     */
    public void setDoencas(List<Doenca> doencas) {
        this.doencas = doencas;
    }

    /**
     * Imprime os atributos da classe
     * @return String
     */
    @Override
    public String toString() {
        return "id:"+idUsuario+
                "\nLogin:"+login+
                "\nsenha:"+senha+
                "\nadministrador:"+administrador+
                "\noperador:"+operador+
                "\nusuario:"+usuario+
                "\nespecialista:"+especialista+
                "\ndata cadastro:"+datacadastro;
    }

}
