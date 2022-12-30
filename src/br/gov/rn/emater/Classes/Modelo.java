/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Classes;

import br.gov.rn.emater.Dao.ModeloDao;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe Modelo para as classes base
 * Data: 05/2010
 * @param <D>
 * @author cledsonfs,ururai
 * @version 1.0
 */
public abstract class Modelo<D extends ModeloDao> {

    private D dao;

    /**
     *
     * @return
     */
    public abstract D newDao();

    /**
     * Retorna a classe <i>Dao</i>. Na extensão retornar� <i>&lt;classe&gt;Dao dao</i>.
     * Exemplo <i>public PedidoDao dao getDao() {...</i>
     * Caso não exista a classe <i>Dao</i>, chamar� o método {@link #newDao newDao()}.
     *
     * @return
     */
    public D getDao() {
        if (dao == null) {
            dao = newDao();
        }
        return dao;
    }

    /**
     * Atualiza a classe Dao. Na extensão atualizará a classe Dao.
     * Exemplo <i>dao= (PedidoDao) objectDao;</i>
     *
     * @param objectDao
     */
    public final void setDao(D objectDao) {
        dao = objectDao;
    }

    /**
     * Faz no" objeto" associação a todas as classes associadas. Não faz em cascata.<br>
     * Obtém a <i>&lt;classe&gt;Dao</i> utilizando {@link #getDao getDao()}.
     *
     * @throws SQLException
     */
    public final void associar() throws SQLException {
        getDao().associar(this, "");
    }

    /**
     * Faz no "objeto" associação a todas as classes associadas conforme <i>condicao</i>.
     * Detalhes do parâmetro <i>condicao</i> ver na classe <@link br.com.bonor.dao.Dao Dao}.
     * Obtém a <i>&lt;classe&gt;Dao</i> utilizando {@link #getDao getDao()}.
     * Não faz em cascata.<br>
     *
     * @param condicao
     */
    public final void associar(String condicao) {
        try {
            getDao().associar(this, condicao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Faz no "objeto" associação  a "classe" especificada. Não faz em cascata.<br>
     * Obtém a <i>&lt;classe&gt;Dao</i> utilizando {@link #getDao getDao()}.
     *
     * @param    classe  Classe "vizinha" do objeto que será instanciada e associada.
     **/
    public final void associar(Class classe) {
        try {
            getDao().associar(this, classe, "");
        } catch (SQLException ex) {
            System.out.println("Erro em Cgvp.associar: "
                    + this.getClass().getName() + " - Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Faz no" objeto" associação a todas as classes associadas conforme <i>condicao</i>.
     * Detalhes do parâmetro <i>condicao</i> ver na classe <@link br.com.bonor.dao.Dao Dao}.
     * Não faz em cascata.<br>
     * Obtém a <i>&lt;classe&gt;Dao</i> utilizando {@link #getDao getDao()}.
     *
     * @param    classe  Classe "vizinha" do objeto que será instanciada e associada.
     * @param    condicao Condição para associação.
     * @throws SQLException
     *
     **/
    public final void associar(Class classe, String condicao) throws SQLException {
        getDao().associar(this, classe, condicao);
    }

    /**
     * Faz associação  as classes especificadas em <i>List&lt;Class&gt;</i>.
     * Não faz em cascata.
     * Obtém a <i>&lt;classe&gt;Dao</i> utilizando {@link #getDao getDao()}.
     *
     * @param    classes  Um array de classes "vizinhas" que serão instanciadas e associadas.
     * @throws SQLException
     **/
    public final void associar(List<Class> classes) throws SQLException {
        getDao().associar(this, classes, "");
    }

    /**
     * Faz associação  as classes especificadas em <i>List&lt;Class&gt;</i>.
     * Não faz em cascata.
     * Detalhes do parâmetro <i>condicao</i> ver na classe <@link br.com.bonor.dao.Dao Dao}.
     * Obtém a <i>&lt;classe&gt;Dao</i> utilizando {@link #getDao getDao()}.
     *
     * @param    classes  Um array de classes "vizinhas" que serão instanciadas e associadas.
     * @param    condicao Condição para associação.
     * @throws SQLException
     *
     **/
    public final void associar(List<Class> classes, String condicao) throws SQLException {
        getDao().associar(this, classes, condicao);
    }
}
