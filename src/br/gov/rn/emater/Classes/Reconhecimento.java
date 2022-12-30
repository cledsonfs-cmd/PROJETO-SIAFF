/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import javax.swing.ImageIcon;

/**
 *
 * @author cledsonfs
 */
public class Reconhecimento {

    private String indiceReconhecimento;
    private Amostra amostra;
    private ImageIcon imagem;

    /**
     * @return the indiceReconhecimento
     */
    public String getIndiceReconhecimento() {
        return indiceReconhecimento;
    }

    /**
     * @param indiceReconhecimento the indiceReconhecimento to set
     */
    public void setIndiceReconhecimento(String indiceReconhecimento) {
        this.indiceReconhecimento = indiceReconhecimento;
    }

    /**
     * @return the amostra
     */
    public Amostra getAmostra() {
        return amostra;
    }

    /**
     * @param amostra the amostra to set
     */
    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
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
}
