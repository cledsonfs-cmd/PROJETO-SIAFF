/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.DoencaSintomaDao;

/**
 * Classe DoencaSintoma
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaSintoma extends Modelo<DoencaSintomaDao> {

    private int idDoenca;
    private int idSintoma;
    private String observacao;
    private Sintoma sintoma;
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
     * Retorna o id do sintoma
     * @return int
     */
    public int getIdSintoma() {
        return idSintoma;
    }

    /**
     * Seta o id do sintoma
     * @param idSintoma
     */
    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
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
     * Retorna um objeto Sintoma
     * @return Sintoma
     */
    public Sintoma getSintoma() {
        if (this.sintoma == null) {
            this.associar(Sintoma.class);
        }
        return sintoma;
    }

    /**
     * Seta um objeto Sintoma
     * @param sintoma
     */
    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    /**
     * Seta um objeto Doenca
     * @return Doenca
     */
    public Doenca getDoenca() {
        if (this.doenca == null) {
            this.associar(Doenca.class);
        }
        return doenca;
    }

    /**
     * Retorna um objeto Doenca
     * @param doenca
     */
    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    /**
     * Retorna uma instancia de DoencaSintomaDao com base no PoolConexao
     * @return DoencaDao
     */
    @Override
    public DoencaSintomaDao newDao() {
        return PoolConexao.getDoencaSintomaDao();
    }
}
