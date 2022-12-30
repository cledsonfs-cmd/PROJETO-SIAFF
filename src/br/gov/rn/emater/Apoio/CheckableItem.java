package br.gov.rn.emater.Apoio;

/** 
 * Classe responsável por montar uma lista de itens para serem selecionados
 * @version 1.0
 * @author  Cledsonfs,Ururai
 */
public class CheckableItem {

    private String str;
    private boolean isSelected;

    /**
     * @param str 
     */
    public CheckableItem(String str) {
        this.str = str;
        isSelected = false;
    }

    /**     
     * Seleciona o item
     * @param b
     */
    public void setSelected(boolean b) {
        isSelected = b;
    }

    /**
     * Testa se o item esta selecionado
     * @return  boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * saida da classe em String
     * @return String
     */
    public String toString() {
        return str;
    }
}
