/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Familia;
import br.gov.rn.emater.Classes.Genero;
import br.gov.rn.emater.Facade.FamiliaFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe FamiliaController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class FamiliaController extends ModeloController<Familia> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        familia = new Familia();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Familia
     */
    @Override
    public Familia get() {
        if (familia == null) {
            novo();
        }
        return familia;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Familia objeto) {
        familia = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (familia == null) {
            this.setMenssagemRetorno("Objeto familia nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("A familia não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new FamiliaFacade().remove(familia);
                if (r) {
                    this.setMenssagemRetorno("familia excluida com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir a familia!");
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
        if (familia == null) {
            this.setMenssagemRetorno("Objeto familia nulo!");
            return false;
        } else if (familia.getDescricao() == null || familia.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Descrição inválida!");
            return false;
        } else if (familia.getIdUsuario() == 0 || !usuarioExistePorId(familia.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (familiaExiste(familia) && !alteracao) {
            this.setMenssagemRetorno("familia já existe");
            return false;
        } else {
            boolean r = new FamiliaFacade().createUpdate(familia);
            if (r) {
                this.setMenssagemRetorno("familia salva com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a familia!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Familia>
     * @throws Exception
     */
    @Override
    public List<Familia> getTodos() throws Exception {
        return new FamiliaFacade().findAll();
    }

    /**
     * Testa se o objeto existe com base em outro objeto instanciado
     * @param f
     * @return boolean
     */
    public boolean familiaExiste(Familia f) {
        return familiaExiste(f.getDescricao());
    }

    /**
     * Testa se o objeto existe com base no nome do objeto
     * @param nome
     * @return boolean
     */
    public boolean familiaExiste(String nome) {
        int valor = new FamiliaFacade().findWhere("where descricao='" + nome + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto pelo ids
     * @param id
     * @return Familia
     */
    public Familia localizarPorID(int id) {
        return new FamiliaFacade().find(String.valueOf(id));
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (familia == null) {
            this.setMenssagemRetorno("Objeto familia nulo!");
            return false;
        } else {
            List<Genero> g = new ArrayList<Genero>();
            try {
                g = familia.getGeneros();
            } catch (Exception ex) {
                Logger.getLogger(GeneroController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (g.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
