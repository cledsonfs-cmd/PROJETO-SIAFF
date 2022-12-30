package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.FamiliaDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Familia das plantas
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Familia extends Modelo<FamiliaDao> {

    private int idfamilia;
    private String descricao;
    private Timestamp datacadastro;
    private int idUsuario;
    private Usuario usuario;
    private List<Genero> generos;
    private List<Planta> plantas;

    /**
     * Retorna id da familia
     * @return int
     */
    public int getIdfamilia() {
        return idfamilia;
    }

    /**
     * Seta id da familia
     * @param idfamilia
     */
    public void setIdfamilia(int idfamilia) {
        this.idfamilia = idfamilia;
    }

    /**
     * Retorna a descricao da familia
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta a descricao da familia
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma lista de plantas
     * @return List<Planta>
     */
    public List<Planta> getPlantas() {
        return plantas;
    }

    /**
     * Seta uma lista de plantas
     * @param plantas
     */
    public void setPlantas(List<Planta> plantas) {
        this.plantas = plantas;
    }

    /**
     * Retorna uma instancia de FamiliaDao com base no PoolConexao
     * @return DoencaDao
     */
    @Override
    public FamiliaDao newDao() {
        return PoolConexao.getFamiliaDao();
    }

    /**
     * Retorna a data de cadastro da familia
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro da familia
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna o id do usuário que cadastrou a familia
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuário que cadastrou a familia
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
     * Retorna uma lista de generos
     * @return List<Genero>
     */
    public List<Genero> getGeneros() {
        if (this.generos == null) {
            this.associar(Genero.class);
        }
        return generos;
    }

    /**
     * Seta uma lista de generos
     * @param generos
     */
    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
