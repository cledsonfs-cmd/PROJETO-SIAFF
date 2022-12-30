/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Parte;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Facade.ParteFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe ParteController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class ParteController extends ModeloController<Parte> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        parte = new Parte();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Parte
     */
    @Override
    public Parte get() {
        if (parte == null) {
            novo();
        }
        return parte;
    }
    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Parte objeto) {
        parte = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (parte == null) {
            this.setMenssagemRetorno("Objeto parte nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("A parte não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new ParteFacade().remove(parte);
                if (r) {
                    this.setMenssagemRetorno("parte excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir a parte!");
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
        if (parte == null) {
            this.setMenssagemRetorno("Objeto parte nulo!");
            return false;
        } else if (parte.getDescricao() == null || agente.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Descrição inválida!");
            return false;
        } else if (parte.getIdUsuario() == 0 || !usuarioExistePorId(parte.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (parteExiste(parte) && !alteracao) {
            this.setMenssagemRetorno("parte já existe");
            return false;
        } else {
            boolean r = new ParteFacade().createUpdate(parte);
            if (r) {
                this.setMenssagemRetorno("parte salva com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a parte!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Parte>
     * @throws Exception
     */
    @Override
    public List<Parte> getTodos() throws Exception {
        return new ParteFacade().findAll();
    }

    /**
     * testa se o ojejeto existe por outro objeto instanciado
     * @param parte
     * @return boolean
     */
    public boolean parteExiste(Parte parte) {
        return parteExiste(parte.getDescricao());
    }

    /**
     * Testa se o ojejeto existe pelo nome
     * @param nome
     * @return boolean
     */
    public boolean parteExiste(String nome) {
        int valor = new ParteFacade().findWhere("where descricao='" + nome + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto pelo id
     * @param id
     * @return Parte
     */
    public Parte localizarPorID(int id) {
        return new ParteFacade().find(String.valueOf(id));

    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (parte == null) {
            this.setMenssagemRetorno("Objeto característica nulo!");
            return false;
        } else {
            List<PlantaParte> pps = new ArrayList<PlantaParte>();
            try {
                pps = parte.getPlantaPartes();
            } catch (Exception ex) {
                Logger.getLogger(ParteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (pps.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
