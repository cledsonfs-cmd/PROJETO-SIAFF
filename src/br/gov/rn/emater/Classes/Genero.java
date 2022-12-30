package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.GeneroDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Genero das plantas
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Genero extends Modelo<GeneroDao> {

    private int idgenero = 0;
    private String descricao;
    private Timestamp datacadastro;
    private int idUsuario;
    private int idFamilia;
    private Usuario usuario;
    private Familia familia;
    private List<Planta> plantas;

    /**
     * Retorna o id do genero
     * @return int
     */
    public int getIdgenero() {
        return idgenero;
    }

    /**
     * Seta o id do genero
     * @param idgenero
     */
    public void setIdgenero(int idgenero) {
        this.idgenero = idgenero;
    }

    /**
     * Retorna a descrição do genero
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta a descrição do genero
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma instancia de GeneroDao com base no PoolConexao
     * @return DoencaDao
     */
    @Override
    public GeneroDao newDao() {
        return PoolConexao.getGeneroDao();
    }

    /**
     * Retorna a data de cadastro do genero
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro do genero
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna o id do usuario que fez o cadastro do genero
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro do genero
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
     * Retorna o id da familia
     * @return int
     */
    public int getIdFamilia() {
        return idFamilia;
    }

    /**
     * Seta o id da familia
     * @param idFamilia
     */
    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    /**
     * Retorna um Objeto Familia
     * @return Familia
     */
    public Familia getFamilia() {
        if (this.familia == null) {
            this.associar(Familia.class);
        }
        return familia;
    }

    /**
     * Seta um Objeto Familia
     * @param familia
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    /**
     * Retorna uma lista de Plantas
     * @return List<Planta>
     */
    public List<Planta> getPlantas() {
        if (plantas == null) {
            this.associar(Planta.class);
        }
        return plantas;
    }

    /**
     * Seta uma lista de Plantas
     * @param plantas
     */
    public void setPlantas(List<Planta> plantas) {
        this.plantas = plantas;
    }
}
