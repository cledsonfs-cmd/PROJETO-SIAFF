package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.DoencaDao;
import java.sql.Timestamp;
import java.util.List;

/**
 * Classe Doenca de Plantas
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Doenca extends Modelo<DoencaDao> {

    private int iddoenca;
    private String descricao;
    private String nomecientifico;
    private Timestamp datacadastro;
    private int idPlantaParte;
    private int idUsuario;
    private PlantaParte plantaParte;
    private List<DoencaTratamento> doencaTratamentos;
    private List<Amostra> amostras;
    private List<DoencaAgente> doencaAgentes;
    private List<DoencaSintoma> doencaSintomas;
    private Usuario usuario;

    /**
     * Retorna o id da doenca
     * @return int
     */
    public int getIddoenca() {
        return iddoenca;
    }

    /**
     * Seta o id da doenca
     * @param iddoenca
     */
    public void setIddoenca(int iddoenca) {
        this.iddoenca = iddoenca;
    }

    /**
     * Retorna o nome popular da doenca
     * @return String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Seta o nome popular da doenca
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o nome científico da doenca
     * @return String
     */
    public String getNomecientifico() {
        return nomecientifico;
    }

    /**
     * Seta o nome científico da doenca
     * @param nomecientifico
     */
    public void setNomecientifico(String nomecientifico) {
        this.nomecientifico = nomecientifico;
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
     * Retorna uma lista de amostras
     * @return List<Amostra>
     */
    public List<Amostra> getAmostras() {
        if(amostras == null){
            this.associar(Amostra.class);
        }
        return amostras;
    }

    /**
     * Seta uma lista de amostras
     * @param amostras
     */
    public void setAmostras(List<Amostra> amostras) {
        this.amostras = amostras;
    }

    /**
     * Retorna uma lista de doencaagente
     * @return List<DoencaAgente>
     */
    public List<DoencaAgente> getDoencaAgentes() {
        if(this.doencaAgentes==null){
            this.associar(DoencaAgente.class);
        }
        return doencaAgentes;
    }

    /**
     * Seta uma lista de doencaagente
     * @param doencaAgentes
     */
    public void setDoencaAgentes(List<DoencaAgente> doencaAgentes) {
        this.doencaAgentes = doencaAgentes;
    }

    /**
     * Retorna uma lista de plantaparte
     * @return PlantaParte
     */
    public PlantaParte getPlantaParte() {
        if(this.plantaParte == null){
            this.associar(PlantaParte.class);
        }
        return plantaParte;
    }

    /**
     * Seta uma lista de plantaparte
     * @param plantaParte
     */
    public void setPlantaParte(PlantaParte plantaParte) {
        this.plantaParte = plantaParte;
    }

    /**
     * Retorna uma instancia do DoencaDao com base no PoolConexao
     * @return DoencaDao
     */
    @Override
    public DoencaDao newDao() {
        return PoolConexao.getDoencaDao();
    }

    /**
     * Retorna uma lista de doencatratamento
     * @return List<DoencaTratamento>
     */
    public List<DoencaTratamento> getDoencaTratamentos() {
        if(this.doencaTratamentos==null){
            this.associar(DoencaTratamento.class);
        }
        return doencaTratamentos;
    }

    /**
     * Seta uma lista de doencatratamento
     * @param doencaTratamentos
     */
    public void setDoencaTratamentos(List<DoencaTratamento> doencaTratamentos) {
        this.doencaTratamentos = doencaTratamentos;
    }
  

    /**
     * Retorna uma lista de doencasintoma
     * @return List<DoencaSintoma>
     */
    public List<DoencaSintoma> getDoencaSintomas() {
        if(this.doencaSintomas==null){
            this.associar(DoencaSintoma.class);
        }
        return doencaSintomas;
    }

    /**
     * Seta uma lista de doencasintoma
     * @param doencaSintomas
     */
    public void setDoencaSintomas(List<DoencaSintoma> doencaSintomas) {
        this.doencaSintomas = doencaSintomas;
    }

    /**
     * Retorna a data de cadastro da doenca
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro da doenca
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna o id do usuario que fez o cadastro da doenca
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Retorna o id do usuario que fez o cadastro da doenca
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
        if(usuario == null){
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
 
