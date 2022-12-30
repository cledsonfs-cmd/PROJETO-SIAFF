/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Genero;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Facade.GeneroFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe GeneroController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class GeneroController extends ModeloController<Genero> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        genero = new Genero();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Genero
     */
    @Override
    public Genero get() {
        if (genero == null) {
            novo();
        }
        return genero;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Genero objeto) {
        genero = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (genero == null) {
            this.setMenssagemRetorno("Objeto genero nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("O genero não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new GeneroFacade().remove(genero);
                if (r) {
                    this.setMenssagemRetorno("genero excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir o genero!");
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
        if (genero == null) {
            this.setMenssagemRetorno("Objeto genero nulo!");
            return false;
        } else if (genero.getDescricao() == null || genero.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Descrição inválida!");
            return false;
        } else if (genero.getIdUsuario() == 0 || !usuarioExistePorId(genero.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (genero.getIdFamilia() == 0 || !familiaExisteId(genero.getIdFamilia())) {
            this.setMenssagemRetorno("família inválida!");
            return false;
        } else if (generoExiste(genero) && !alteracao) {
            this.setMenssagemRetorno("genero já existe");
            return false;
        } else {
            boolean r = new GeneroFacade().createUpdate(genero);
            if (r) {
                this.setMenssagemRetorno("genero salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o genero!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Genero>
     * @throws Exception
     */
    @Override
    public List<Genero> getTodos() throws Exception {
        return new GeneroFacade().findAll();
    }

    /**
     * Teste se o objeto com base em outro objeto instanciado
     * @param g
     * @return boolean
     */
    public boolean generoExiste(Genero g) {
        return generoExiste(g.getDescricao());
    }

    /**
     * Teste se o objeto existe pelo nome
     * @param nome
     * @return boolean
     */
    public boolean generoExiste(String nome) {
        int valor = new GeneroFacade().findWhere("where descricao='" + nome + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto pelo id
     * @param id
     * @return Genero
     */
    public Genero localizarPorID(int id) {
        return new GeneroFacade().find(String.valueOf(id));

    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (genero == null) {
            this.setMenssagemRetorno("Objeto genero nulo!");
            return false;
        } else {
            List<Planta> ps = new ArrayList<Planta>();
            try {
                ps = genero.getPlantas();
            } catch (Exception ex) {
                Logger.getLogger(GeneroController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
