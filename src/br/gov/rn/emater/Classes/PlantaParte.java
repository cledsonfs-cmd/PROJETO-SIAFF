package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.PlantaParteDao;
import java.util.List;

/**
 * Classe PlantaParte
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaParte extends Modelo<PlantaParteDao> {

    private int idPlantaParte;
    private int idParte;
    private int idPlanta;
    private String observacao;
    private Parte parte;
    private Planta planta;
    private List<Doenca> doenca;

    /**
     * Retorna o id da parte
     * @return int
     */
    public int getIdParte() {
        return idParte;
    }

    /**
     * Seta o id da parte
     * @param idParte
     */
    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

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
     * Retorna um objeto Parte
     * @return Parte
     */
    public Parte getParte() {
        if (this.parte == null) {
            this.associar(Parte.class);
        }
        return parte;
    }

    /**
     * Seta um objeto Parte
     * @param parte
     */
    public void setParte(Parte parte) {
        this.parte = parte;
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
     * Retorna um objeto doenca
     * @return List<Doenca>
     */
    public List<Doenca> getDoenca() {
        if (this.doenca == null) {
            this.associar(Doenca.class);
        }
        return doenca;
    }

    /**
     * Seta um objeto doenca
     * @param doenca
     */
    public void setDoenca(List<Doenca> doenca) {
        this.doenca = doenca;
    }

    /**
     * Retorna uma instancia de PlantaParteDao com base no PoolConexao
     * @return PlantaParteDao
     */
    @Override
    public PlantaParteDao newDao() {
        return PoolConexao.getPlantaParteDao();
    }

    /**
     * Retorna o id da plantaparte
     * @return int
     */
    public int getIdPlantaParte() {
        return idPlantaParte;
    }

    /**
     * Seta o id da plantaparte
     * @param idPlantaParte
     */
    public void setIdPlantaParte(int idPlantaParte) {
        this.idPlantaParte = idPlantaParte;
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
}
 
