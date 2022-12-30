/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.PlantaCaracteristicaDao;

/**
 * Classe PlantaCaracteristica
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaCaracteristica extends Modelo<PlantaCaracteristicaDao> {

    private int idPlanta;
    private int idCaracteristica;
    private String observacao;
    private Planta planta;
    private Caracteristica caracteristica;

    /**
     * Retorna o id da planta
     * @return int
     */
    public int getIdPlanta() {
        return idPlanta;
    }

    /**
     * Seta o id da planta
     * @param idPlanta
     */
    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }

    /**
     * Retorna o id da caracteristica
     * @return int
     */
    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    /**
     * Seta o id da caracteristica
     * @param idCaracteristica
     */
    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    /**
     * Retorna uma observacao da classe
     * @return String
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Seta uma observacao da classe
     * @param observacao
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * Retorna um objeto Planta
     * @return Planta
     */
    public Planta getPlanta() {
        if (this.planta == null) {
            this.associar(Planta.class);
        }
        return planta;
    }

    /**
     * Seta um objeto Planta
     * @param planta
     */
    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    /**
     * Retorna um objeto Caracteristica
     * @return Caracteristica
     */
    public Caracteristica getCaracteristica() {
        if (this.caracteristica == null) {
            this.associar(Caracteristica.class);
        }
        return caracteristica;
    }

    /**
     * Seta um objeto Caracteristica
     * @param caracteristica
     */
    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    /**
     * Retorna uma instancia de PlantaCaracteristicaDao com base no PoolConexao
     * @return PlantaCaracteristicaDao
     */
    @Override
    public PlantaCaracteristicaDao newDao() {
        return PoolConexao.getPlantaCaracteristicaDao();
    }
}
