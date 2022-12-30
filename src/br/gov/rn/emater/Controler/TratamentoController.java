/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.Tratamento;
import br.gov.rn.emater.Facade.TratamentoFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe TratamentoController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class TratamentoController extends ModeloController<Tratamento>{

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        tratamento = new Tratamento();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Tratamento
     */
    @Override
    public Tratamento get() {
        if(tratamento == null){
            novo();
        }
        return tratamento;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Tratamento objeto) {
        tratamento = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (tratamento == null) {
            this.setMenssagemRetorno("Objeto tratamento nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("A tratamento não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new TratamentoFacade().remove(tratamento);
                if (r) {
                    this.setMenssagemRetorno("tratamento excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir o tratamento!");
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
        if (tratamento == null) {
            this.setMenssagemRetorno("Objeto tratamento nulo!");
            return false;
        } else if (tratamento.getDescricao() == null || tratamento.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Descrição inválida!");
            return false;
        } else if (tratamento.getIdUsuario() == 0 || !usuarioExistePorId(tratamento.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (tratamentoExiste(tratamento) && !alteracao) {
            this.setMenssagemRetorno("tratamento já existe");
            return false;
        } else {
            boolean r = new TratamentoFacade().createUpdate(tratamento);
            if (r) {
                this.setMenssagemRetorno("tratamento salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o tratamento!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Tratamento>
     * @throws Exception
     */
    @Override
    public List<Tratamento> getTodos() throws Exception {
        return new TratamentoFacade().findAll();
    }

    /**
     * Testa se o objeto existe com base em outro objeto instanciado
     * @param tratamento
     * @return
     */
    public boolean tratamentoExiste(Tratamento tratamento) {
        return tratamentoExiste(tratamento.getDescricao());
    }

    /**
     * Testa se o objeto existe com base no nome
     * @param nome
     * @return boolean
     */
    public boolean tratamentoExiste(String nome) {
        int valor = new TratamentoFacade().findWhere("where descricao='" + nome + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto com base em um id
     * @param id
     * @return Tratamento
     */
    public Tratamento localizarPorID(int id) {
        return new TratamentoFacade().find(String.valueOf(id));
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (tratamento == null) {
            this.setMenssagemRetorno("Objeto tratamento nulo!");
            return false;
        } else {
            List<DoencaTratamento> dt = new ArrayList<DoencaTratamento>();
            try {
                dt = tratamento.getDoencaTratamentos();
            } catch (Exception ex) {
                Logger.getLogger(TratamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dt.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
