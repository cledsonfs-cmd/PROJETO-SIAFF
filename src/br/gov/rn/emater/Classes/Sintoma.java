package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.SintomaDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Sintoma de doencas
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Sintoma extends Modelo<SintomaDao> {

    private int idsintoma;
    private String descricao;
    private Timestamp datacadastro;
    private int idUsuario;
    private Usuario usuario;
    private List<DoencaSintoma> doencaSintomas;

    /**
     * Retorna o id do sintoma
     * @return int
     */
    public int getIdsintoma() {
        return idsintoma;
    }

    /**
     * Seta o id do sintoma
     * @param idsintoma
     */
    public void setIdsintoma(int idsintoma) {
        this.idsintoma = idsintoma;
    }

    /**
     * Retorna a descricao do sintoma
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta a descricao do sintoma
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma instancia de SintomaDao com base no PoolConexao
     * @return SintomaDao
     */
    @Override
    public SintomaDao newDao() {
        return PoolConexao.getSintomaDao();
    }

    /**
     * Retorna uma lista de objetos doencasintoma
     * @return List<DoencaSintoma>
     */
    public List<DoencaSintoma> getDoencaSintoma() {
        if (doencaSintomas == null) {
            this.associar(DoencaSintoma.class);
        }
        return doencaSintomas;
    }

    /**
     * Seta uma lista de objetos doencasintoma
     * @param doencaSintomas
     */
    public void setDoencaSintoma(List<DoencaSintoma> doencaSintomas) {
        this.doencaSintomas = doencaSintomas;
    }

    /**
     * Retorna a data de cadastro do sintoma
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro do sintoma
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna o id do usuario que fez o cadastro do sintoma
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro do sintoma
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
 
