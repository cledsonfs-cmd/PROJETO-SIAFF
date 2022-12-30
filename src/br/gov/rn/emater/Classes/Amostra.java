package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.AmostraDao;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

/**
 * Classe Amostra de Doenças
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Amostra extends Modelo<AmostraDao> {

    private int idamostra;
    private Timestamp datacadastro;
    private ImageIcon imagem;
    private int idDoenca;
    private int idUsuario;
    private Doenca doenca;
    private Usuario usuario;

    /**
     * Retorna o id da amostra
     * @return int
     */
    public int getIdamostra() {
        return idamostra;
    }

    public String getIdamostraString() {
        return String.valueOf(idamostra);
    }

    /**
     * Seta o id da amostra
     * @param idamostra
     */
    public void setIdamostra(int idamostra) {
        this.idamostra = idamostra;
    }

    /**
     * Retorna a data de cadastro da amostra
     * @return Timestamp
     */
    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    /**
     * Seta a data de cadastro da amostra
     * @param datacadastro
     */
    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    /**
     * Retorna uma imagem da amostra
     * @return ImageIcon
     */
    public ImageIcon getImagem() {
        return imagem;
    }

    public ImageIcon getImagem(int size) {
        return  new ImageIcon(imagem.getImage().getScaledInstance(size, -1, 100));
    }

    /**
     * Seta uma imagem da amostra
     * @param imagem
     */
    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
        //this.imagem = new ImageIcon(imagem.getImage().getScaledInstance(8, -1, 100));
    }

    /**
     * Retorna o id da doenca
     * @return int
     */
    public int getIdDoenca() {
        return idDoenca;
    }

    /**
     * Seta o id da doenca
     * @param idDoenca
     */
    public void setIdDoenca(int idDoenca) {
        this.idDoenca = idDoenca;
    }

    /**
     * Retorna um objeto Doenca
     * @return Doenca
     */
    public Doenca getDoenca() {
        if (doenca == null) {
            this.associar(Doenca.class);
        }
        return doenca;
    }

    /**
     * Seta um objeto Doenca
     * @param doenca
     */
    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    /**
     * Retorna uma instancia do AmostraDao com base no PoolConexao
     * @return AmostraDao
     */
    @Override
    public AmostraDao newDao() {
        return PoolConexao.getAmostraDao();
    }

    /**
     * Retorna o id do usuario que fez o cadastro da amostra
     * @return int
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Seta o id do usuario que fez o cadastro da amostra
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
}
