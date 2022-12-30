/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Dao.AnaliseDao;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

/**
 *
 * @author cledsonfs
 */
public class Analise  extends Modelo<AnaliseDao> {

    private int idAnalise;
    private Timestamp data;
    private ImageIcon imagem;
    private String nome;
    
    @Override
    public AnaliseDao newDao() {
        return PoolConexao.getAnaliseDao();
    }

    /**
     * @return the idAnalise
     */
    public int getIdAnalise() {
        return idAnalise;
    }

    /**
     * @param idAnalise the idAnalise to set
     */
    public void setIdAnalise(int idAnalise) {
        this.idAnalise = idAnalise;
    }

    /**
     * @return the data
     */
    public Timestamp getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Timestamp data) {
        this.data = data;
    }

    /**
     * @return the imagem
     */
    public ImageIcon getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
