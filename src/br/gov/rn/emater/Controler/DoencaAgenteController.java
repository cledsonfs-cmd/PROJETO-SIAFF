/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.DoencaAgente;
import br.gov.rn.emater.Facade.DoencaAgenteFacade;
import java.util.List;

/**
 * Classe DoencaAgenteController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaAgenteController extends ModeloController<DoencaAgente> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        doencaAgente = new DoencaAgente();
        return true;
    }

    /**
     * Retorna um objeto
     * @return DoencaAgente
     */
    @Override
    public DoencaAgente get() {
        if (doencaAgente == null) {
            novo();
        }
        return doencaAgente;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(DoencaAgente objeto) {
        doencaAgente = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (doencaAgente == null) {
            this.setMenssagemRetorno("Objeto DoencaAgente nulo!");
            return false;
        } else {
            boolean r = new DoencaAgenteFacade().remove(doencaAgente);
            if (r) {
                this.setMenssagemRetorno("DoencaAgente excluido com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir o DoencaAgente!");
                return false;
            }
        }
    }

    /**
     * Salva um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean salvar() throws Exception {
        return salvar(false);
    }

    /**
     * Salva/Altera um objeto implementando as regras de negocio
     * @param alteracao
     * @return boolean
     * @throws Exception
     */
    public boolean salvar(boolean alteracao) throws Exception {
        if (alteracao) {
            this.setMenssagemRetorno("Alteração não se aplica!");
            return true;
        }
        if (doencaAgente == null) {
            this.setMenssagemRetorno("Objeto DoencaAgente nulo!");
            return false;
        } else if (doencaAgente.getIdAgente() == 0 || !agenteExisteId(doencaAgente.getIdAgente())) {
            this.setMenssagemRetorno("idagente inválido!");
            return false;
        } else if (doencaAgente.getIdDoenca() == 0 || !doencaExisteId(doencaAgente.getIdDoenca())) {
            this.setMenssagemRetorno("iddoenca inválido!");
            return false;
        } else {
            boolean r = new DoencaAgenteFacade().createUpdate(doencaAgente);
            if (r) {
                this.setMenssagemRetorno("DoencaAgente salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o DoencaAgente!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<DoencaAgente>
     * @throws Exception
     */
    @Override
    public List<DoencaAgente> getTodos() throws Exception {
        return new DoencaAgenteFacade().findAll();
    }

    /**
     * Retorna uma lista de DoencaAgente com base em um id de doenca
     * @param iddoenca
     * @return List<DoencaAgente>
     * @throws Exception
     */
    public List<DoencaAgente> getTodosPorIdDoenca(int iddoenca) throws Exception {
        return new DoencaAgenteFacade().findWhere(" where iddoenca=" + iddoenca);
    }

    /**
     * Retorna uma lista de DoencaAgente com base em um id de agente
     * @param idagente
     * @return List<DoencaAgente>
     * @throws Exception
     */
    public List<DoencaAgente> getTodosPorIdAgente(int idagente) throws Exception {
        return new DoencaAgenteFacade().findWhere(" where idagente=" + idagente);
    }
}
