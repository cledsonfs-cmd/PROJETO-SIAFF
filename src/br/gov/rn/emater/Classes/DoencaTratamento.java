/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.DoencaTratamentoDao;

/**
 * Classe DoencaTratamento
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaTratamento extends Modelo<DoencaTratamentoDao> {

    private int idDoenca;
    private int idTratamento;
    private String observacao;
    private Tratamento tratamento;
    private Doenca doenca;

    /**
     * Retorna o id da doenca
     * @return int
     */
    public int getIdDoenca() {
        return idDoenca;
    }

    /**
     * Seta o id da doenca
     * @param idDoenca
     */
    public void setIdDoenca(int idDoenca) {
        this.idDoenca = idDoenca;
    }

    /**
     * Retorna o id do tratamento
     * @return int
     */
    public int getIdTratamento() {
        return idTratamento;
    }

    /**
     * Seta o id do tratamento
     * @param idTratamento
     */
    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Retorna uma observação referente a associação dos objetos
     * @return String
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Seta uma observação referente a associação dos objetos
     * @param observacao
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * Retorna um objeto Tratamento
     * @return Tratamento
     */
    public Tratamento getTratamento() {
        if (this.tratamento == null) {
            this.associar(Tratamento.class);
        }
        return tratamento;
    }

    /**
     * Seta um objeto Tratamento
     * @param tratamento
     */
    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    /**
     * Retorna um objeto Doenca
     * @return Doenca
     */
    public Doenca getDoenca() {
        if (this.doenca == null) {
            this.associar(Doenca.class);
        }
        return doenca;
    }

    /**
     * Seta um objeto Doenca
     * @param doenca
     */
    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    /**
     * Retorna uma instancia de DoencaTratamentoDao com base no PoolConexao
     * @return DoencaDao
     */
    @Override
    public DoencaTratamentoDao newDao() {
        return PoolConexao.getDoencaTratamentoDao();
    }

    
}
