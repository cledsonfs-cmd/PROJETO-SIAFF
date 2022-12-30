package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.DoencaAgenteDao;

/**
 * Classe DoencaAgente
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaAgente extends Modelo<DoencaAgenteDao> {

    private int idAgente;
    private int idDoenca;
    private Agente agente;
    private Doenca doenca;

    /**
     * Retorna o id do agente causador
     * @return int
     */
    public int getIdAgente() {
        return idAgente;
    }

    /**
     * Seta o id do agente causador
     * @param idAgente
     */
    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

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
     * Retorna um objeto Agente
     * @return Agente
     */
    public Agente getAgente() {
        if (this.agente == null) {
            this.associar(Agente.class);
        }
        return agente;
    }

    /**
     * Seta um objeto Agente
     * @param agente
     */
    public void setAgente(Agente agente) {
        this.agente = agente;
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
     * Retorna uma instancia de DoencaAgenteDao com base no PoolConexao
     * @return DoencaDao
     */
    @Override
    public DoencaAgenteDao newDao() {
        return PoolConexao.getDoencaAgenteDao();
    }
}
