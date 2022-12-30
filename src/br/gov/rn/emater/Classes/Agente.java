/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.AgenteDao;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.ImageIcon;
/**
 * Classe Agente Causador de Doencas
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Agente extends Modelo<AgenteDao> {

    private int idagente;
    private String descricao;
    private String nomecientifico;
    private Timestamp datacadastro;
    private int idUsuario;
    private ImageIcon imagem;
    private Usuario usuario;
    private List<DoencaAgente> doencasAgentes;

    /**
     * Retorna o id do agente
     * @return int
     */
    public int getIdagente() {
        return idagente;
    }

    /**
     * Seta o id do agente
     * @param idagente
     */
    public void setIdagente(int idagente) {
        this.idagente = idagente;
    }

    /**
     * Retorna o nome do agente
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta o nome do agente
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o nome científico do agente
     * @return String
     */
    public String getNomecientifico() {
        return nomecientifico;
    }

    /**
     * Seta o nome científico do agente
     * @param nomecientifico
     */
    public void setNomecientifico(String nomecientifico) {
        this.nomecientifico = nomecientifico;
    }

    /**
     * Retorna uma lista de DoencasAgentes
     * @return List<DoencaAgente>
     */
    public List<DoencaAgente> getDoencasAgentes() {
        if (doencasAgentes == null) {
            this.associar(DoencaAgente.class);
        }
        return doencasAgentes;
    }

    /**
     * Seta uma lista de DoencasAgentes
     * @param doencasAgentes
     */
    public void setDoencasAgentes(List<DoencaAgente> doencasAgentes) {
        this.doencasAgentes = doencasAgentes;
    }

    /**
     * Retorna uma instancia do AgenteDao com base no PoolConexao
     * @return AgenteDao
     */
    @Override
    public AgenteDao newDao() {
        return PoolConexao.getAgenteDao();
    }

    /**
     * Retorna a data de cadastro do agente
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro do agente
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna o id do usuario responsável pelo cadastro do agente
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario responsável pelo cadastro do agente
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Retorna um objeto Usuario
     * @return Usuario
     */
    public Usuario getUsuario() {
        if (usuario == null) {
            this.associar(Usuario.class);
        }
        return usuario;
    }

    /**
     * Seta um objeto Usuario
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna a imagem do agente
     * @return ImageIcon
     */
    public ImageIcon getImagem() {
        return imagem;
    }

    /**
     * Seta a imagem do agente
     * @param imagem
     */
    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }
}
 
