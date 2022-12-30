package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.PlantaDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Planta
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Planta extends Modelo<PlantaDao>{

    private int idplanta;
    private String descricao;
    private String nomecientifico;
    private String nomepopular;
    private int idGenero;
    private Timestamp dataCadastro;
    private int idUsuario;
    private Usuario usuario;
    private List<PlantaParte> plantaParte;
    private List<PlantaCaracteristica> plantaCaracteristicas;
    private Genero genero;

    /**
     * Retorna o id da planta
     * @return int
     */
    public int getIdplanta() {
        return idplanta;
    }

    /**
     * Seta o id da planta
     * @param idplanta
     */
    public void setIdplanta(int idplanta) {
        this.idplanta = idplanta;
    }

    /**
     * Retorna a descricao da planta
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta a descricao da planta
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o nome cientifico da planta
     * @return String
     */
    public String getNomecientifico() {
        return nomecientifico;
    }

    /**
     * Seta o nome cientifico da planta
     * @param nomecientifico
     */
    public void setNomecientifico(String nomecientifico) {
        this.nomecientifico = nomecientifico;
    }

    /**
     * Retorna o nome popular da planta
     * @return String
     */
    public String getNomepopular() {
        return nomepopular;
    }

    /**
     * Seta o nome popular da planta
     * @param nomepopular
     */
    public void setNomepopular(String nomepopular) {
        this.nomepopular = nomepopular;
    }

    /**
     * Retorna o id do genero da planta
     * @return int
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * Seta o id do genero da planta
     * @param idGenero
     */
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    /**
     * Retorna uma lista de PlantaParte
     * @return List<PlantaParte>
     */
    public List<PlantaParte> getPlantaParte() {
        if(this.plantaParte == null){
            this.associar(PlantaParte.class);
        }
        return plantaParte;
    }

    /**
     * Seta uma lista de PlantaParte
     * @param plantaParte
     */
    public void setPlantaParte(List<PlantaParte> plantaParte) {
        this.plantaParte = plantaParte;
    }

    /**
     * Retorna um objeto Genero
     * @return Genero
     */
    public Genero getGenero() {
        if(this.genero == null){
            this.associar(Genero.class);
        }
        return genero;
    }

    /**
     * Seta um objeto Genero
     * @param genero
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Retorna uma instancia de PlantaDao com base no PoolConexao
     * @return PlantaDao
     */
    @Override
    public PlantaDao newDao() {
        return PoolConexao.getPlantaDao();
    }

    /**
     * Seta uma lista de PlantaCaracteristica
     * @return List<PlantaCaracteristica>
     */
    public List<PlantaCaracteristica> getPlantaCaracteristicas() {
        if(this.plantaCaracteristicas == null){
            this.associar(PlantaCaracteristica.class);
        }
        return plantaCaracteristicas;
    }

    /**
     * Seta uma lista de PlantaCaracteristica
     * @param plantaCaracteristicas
     */
    public void setPlantaCaracteristicas(List<PlantaCaracteristica> plantaCaracteristicas) {
        this.plantaCaracteristicas = plantaCaracteristicas;
    }

    /**
     * Retorna a data de cadastro da planta
     * @return Timestamp
     */
    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    /**
     * Seta a data de cadastro da planta
     * @param dataCadastro
     */
    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * Retorna o id do usuario que fez o cadastro da planta
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro da planta
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Retorna um objeto Usuario
     * @return
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
}
 
