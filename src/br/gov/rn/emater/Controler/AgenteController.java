/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Agente;
import br.gov.rn.emater.Classes.DoencaAgente;
import br.gov.rn.emater.Facade.AgenteFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe AgenteController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class AgenteController extends ModeloController<Agente> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        agente = new Agente();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Agente
     */
    @Override
    public Agente get() {
        if (agente == null) {
            novo();
        }
        return agente;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Agente objeto) {
        agente = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (agente == null) {
            this.setMenssagemRetorno("Objeto agente nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("O agente não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new AgenteFacade().remove(agente);
                if (r) {
                    this.setMenssagemRetorno("Agente excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir o agente!!");
                    return false;
                }
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
        if (agente == null) {
            this.setMenssagemRetorno("Objeto agente nulo!");
            return false;
        } else if (agente.getDescricao() == null || agente.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Nome popular inválido!");
            return false;
        } else if (agente.getNomecientifico() == null || agente.getNomecientifico().length() == 0) {
            this.setMenssagemRetorno("Nome científico inválido!");
            return false;
        } else if (agente.getIdUsuario() == 0 || !usuarioExistePorId(agente.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (agenteExiste(agente) && !alteracao) {
            this.setMenssagemRetorno("Agente já existe");
            return false;
        } else {
            boolean r = new AgenteFacade().createUpdate(agente);
            if (r) {
                this.setMenssagemRetorno("Agente salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o agente!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Agente>
     * @throws Exception
     */
    @Override
    public List<Agente> getTodos() throws Exception {
        return new AgenteFacade().findAll();
    }

    /**
     * Testa se o objeto existe a partir de um objeto instanciado
     * @param agente
     * @return boolean
     */
    public boolean agenteExiste(Agente agente) {
        int valor = new AgenteFacade().findWhere("where descricao='" + agente.getDescricao() + "' or nomecientifico='" + agente.getNomecientifico() + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto pelo id
     * @param id
     * @return Agente
     */
    public Agente localizarPorID(int id) {
        return new AgenteFacade().find(String.valueOf(id));
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (agente == null) {
            this.setMenssagemRetorno("Objeto agente nulo!");
            return false;
        } else {
            List<DoencaAgente> doencaAgentes = new ArrayList<DoencaAgente>();
            try {
                doencaAgentes = new DoencaAgenteController().getTodosPorIdAgente(agente.getIdagente());
            } catch (Exception ex) {
                Logger.getLogger(AgenteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (doencaAgentes.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
