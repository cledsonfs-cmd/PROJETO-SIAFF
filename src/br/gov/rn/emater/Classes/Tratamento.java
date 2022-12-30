package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.TratamentoDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Tratamento de doencas
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Tratamento extends Modelo<TratamentoDao> {

    private int idtratamento;
    private String descricao;
    private Timestamp datacadastro;
    private int idUsuario;
    private Usuario usuario;
    private List<DoencaTratamento> doencaTratamentos;

    /**
     * Retorna o id do tratamento
     * @return int
     */
    public int getIdtratamento() {
        return idtratamento;
    }

    /**
     * Seta o id do tratamento
     * @param idtratamento
     */
    public void setIdtratamento(int idtratamento) {
        this.idtratamento = idtratamento;
    }

    /**
     * Retorna a descricao do tratamento
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta a descricao do tratamento
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma instancia de TratamentoDao com base no PoolConexao
     * @return TratamentoDao
     */
    @Override
    public TratamentoDao newDao() {
        return PoolConexao.getTratamentoDao();
    }

    /**
     * Retorna uma lista de DoencaTratamento
     * @return List<DoencaTratamento>
     */
    public List<DoencaTratamento> getDoencaTratamentos() {
        if (doencaTratamentos == null) {
            this.associar(DoencaTratamento.class);
        }
        return doencaTratamentos;
    }

    /**
     * Seta uma lista de DoencaTratamento
     * @param doencaTratamentos
     */
    public void setDoencaTratamentos(List<DoencaTratamento> doencaTratamentos) {
        this.doencaTratamentos = doencaTratamentos;
    }

    /**
     * Retorna a data de cadastramento do tratamento
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastramento do tratamento
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna o id do usuario que fez o cadastro do tratamento
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro do tratamento
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
