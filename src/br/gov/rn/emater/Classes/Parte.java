package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.ParteDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Parte das plantas
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Parte extends Modelo<ParteDao> {

    private int idparte;
    private String descricao;
    private Timestamp datacadastro;
    private int idUsuario;
    private Usuario usuario;
    private List<PlantaParte> plantaPartes;

    /**
     * Retorna o id da plarte
     * @return int
     */
    public int getIdparte() {
        return idparte;
    }

    /**
     * Seta o id da plarte
     * @param idparte
     */
    public void setIdparte(int idparte) {
        this.idparte = idparte;
    }

    /**
     * Retorna a descricao da parte
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta a descricao da parte
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma lista de plantaparte
     * @return List<PlantaParte>
     */
    public List<PlantaParte> getPlantaPartes() {
        if (this.plantaPartes == null) {
            this.associar(PlantaParte.class);
        }
        return plantaPartes;
    }

    /**
     * Seta uma lista de plantaparte
     * @param plantaPartes
     */
    public void setPlantaPartes(List<PlantaParte> plantaPartes) {
        this.plantaPartes = plantaPartes;
    }

    /**
     * Retorna uma instancia de ParteDao com base no PoolConexao
     * @return ParteDao
     */
    @Override
    public ParteDao newDao() {
        return PoolConexao.getParteDao();
    }

    /**
     * Retorna a data de cadastro da parte
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro da parte
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Seta o id do usuario que fez o cadastro da parte
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro da parte
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
 
