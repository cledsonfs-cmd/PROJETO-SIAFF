/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.Sintoma;
import br.gov.rn.emater.Facade.SintomaFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe SintomaController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class SintomaController extends ModeloController<Sintoma> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        sintoma = new Sintoma();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Sintoma
     */
    @Override
    public Sintoma get() {
        if (sintoma == null) {
            novo();
        }
        return sintoma;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Sintoma objeto) {
        sintoma = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (sintoma == null) {
            this.setMenssagemRetorno("Objeto sintoma nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("A sintoma não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new SintomaFacade().remove(sintoma);
                if (r) {
                    this.setMenssagemRetorno("sintoma excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir o sintoma!");
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
        if (sintoma == null) {
            this.setMenssagemRetorno("Objeto sintoma nulo!");
            return false;
        } else if (sintoma.getDescricao() == null || sintoma.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Descrição inválida!");
            return false;
        } else if (sintoma.getIdUsuario() == 0 || !usuarioExistePorId(sintoma.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (sintomaExiste(sintoma) && !alteracao) {
            this.setMenssagemRetorno("sintoma já existe");
            return false;
        } else {
            boolean r = new SintomaFacade().createUpdate(sintoma);
            if (r) {
                this.setMenssagemRetorno("sintoma salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o sintoma!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Sintoma>
     * @throws Exception
     */
    @Override
    public List<Sintoma> getTodos() throws Exception {
        return new SintomaFacade().findAll();
    }

    /**
     * Testa se o objeto existe com base em um outro objeto instanciado
     * @param sintoma
     * @return
     */
    public boolean sintomaExiste(Sintoma sintoma) {
        return sintomaExiste(sintoma.getDescricao());
    }

    /**
     * Testa se o objeto existe com base em um nome
     * @param nome
     * @return boolean
     */
    public boolean sintomaExiste(String nome) {
        int valor = new SintomaFacade().findWhere("where descricao='" + nome + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza um objeto com base em um id fornecido
     * @param id
     * @return Sintoma
     */
    public Sintoma localizarPorID(int id) {
        return new SintomaFacade().find(String.valueOf(id));
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (sintoma == null) {
            this.setMenssagemRetorno("Objeto sintoma nulo!");
            return false;
        } else {
            List<DoencaSintoma> ds = new ArrayList<DoencaSintoma>();
            try {
                ds = sintoma.getDoencaSintoma();
            } catch (Exception ex) {
                Logger.getLogger(SintomaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ds.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
