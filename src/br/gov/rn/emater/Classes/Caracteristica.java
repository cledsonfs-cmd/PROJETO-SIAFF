package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.CaracteristicaDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe: Caracteristica
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Caracteristica extends Modelo<CaracteristicaDao> {

    private int idcaracteristica;
    private String descricao;
    private Timestamp datacadastro;
    private int idUsuario;
    private Usuario usuario;
    private List<PlantaCaracteristica> plantaCaracteristicas;

    /**
     * Retorna o id da caracteristica
     * @return int
     */
    public int getIdcaracteristica() {
        return idcaracteristica;
    }

    /**
     * Seta o id da caracteristica
     * @param idcaracteristica
     */
    public void setIdcaracteristica(int idcaracteristica) {
        this.idcaracteristica = idcaracteristica;
    }

    /**
     * Retorna uma descricao para a caracteristica
     * @return tString
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta uma descricao para a caracteristica
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma instancia do CaracteristicaDao com base no PoolConexao
     * @return AmostraDao
     */
    @Override
    public CaracteristicaDao newDao() {
        return PoolConexao.getCaracteristicaDao();
    }

    /**
     * Retorna uma lista de PlantaCaracteristica
     * @return List<PlantaCaracteristica>
     */
    public List<PlantaCaracteristica> getPlantaCaracteristicas() {
        if (plantaCaracteristicas == null) {
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
     * Retorna a data de cadastro da caracteristica
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro da caracteristica
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     *  Retorna o id do usuario que fez o cadastro da caracteristica
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro da caracteristica
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
        if (this.usuario == null) {
            this.associar(Usuario.class);
        }
        return this.usuario;
    }

    /**
     * Seta um objeto Usuario
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
