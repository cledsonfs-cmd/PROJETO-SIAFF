/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Agente;
import br.gov.rn.emater.Classes.Amostra;
import br.gov.rn.emater.Classes.Analise;
import br.gov.rn.emater.Classes.Caracteristica;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaAgente;
import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.Familia;
import br.gov.rn.emater.Classes.Genero;
import br.gov.rn.emater.Classes.Parte;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Classes.Sintoma;
import br.gov.rn.emater.Classes.Tratamento;
import br.gov.rn.emater.Classes.Usuario;
import br.gov.rn.emater.Facade.AgenteFacade;
import br.gov.rn.emater.Facade.CaracteristicaFacade;
import br.gov.rn.emater.Facade.DoencaFacade;
import br.gov.rn.emater.Facade.FamiliaFacade;
import br.gov.rn.emater.Facade.GeneroFacade;
import br.gov.rn.emater.Facade.ParteFacade;
import br.gov.rn.emater.Facade.PlantaFacade;
import br.gov.rn.emater.Facade.PlantaParteFacade;
import br.gov.rn.emater.Facade.SintomaFacade;
import br.gov.rn.emater.Facade.TratamentoFacade;
import br.gov.rn.emater.Facade.UsuarioFacade;
import java.util.List;

/**
 * Classe ModeloController
 * @param <C>
 * @author cledsonfs,ururai
 * @version 1.0
 */
public abstract class ModeloController<C> {

    /**
     *
     */
    protected Agente agente = null;
    /**
     *
     */
    protected Amostra amostra = null;
    /**
     *
     */
    protected Familia familia = null;
    /**
     *
     */
    protected Sintoma sintoma = null;
    /**
     *
     */
    protected Genero genero = null;
    /**
     *
     */
    protected DoencaSintoma doencaSintoma = null;
    /**
     *
     */
    protected DoencaAgente doencaAgente = null;
    /**
     *
     */
    protected Planta planta = null;
    /**
     *
     */
    protected Parte parte = null;
    /**
     *
     */
    protected PlantaParte plantaParte = null;
    /**
     *
     */
    protected Caracteristica caracteristica = null;
    /**
     *
     */
    protected PlantaCaracteristica plantaCaracteristica = null;
    /**
     *
     */
    protected Doenca doenca = null;
    /**
     *
     */
    protected Tratamento tratamento = null;
    /**
     *
     */
    protected DoencaTratamento doencaTratamento = null;
    /**
     *
     */
    protected Usuario usuario = null;
    /**
     *
     */
    protected Analise analise = null;

    private String menssagemRetorno = "";

    /**
     * Cria um novo objeto
     * @return boolean
     */
    public abstract boolean novo();

    /**
     * Retorna um novo objeto
     * @return boolean
     */
    public abstract C get();

    /**
     * RetSeta um objeto
     * @param objeto
     */
    public abstract void set(C objeto);

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    public abstract boolean excluir() throws Exception;

    /**
     * Salva um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    public abstract boolean salvar() throws Exception;

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<C>
     * @throws Exception
     */
    public abstract List<C> getTodos() throws Exception;

    /**
     * Retorna a ultima menssagem de retorno
     * @return the menssagemRetorno
     */
    public String getMenssagemRetorno() {
        return menssagemRetorno;
    }

    /**
     * @param menssagemRetorno the menssagemRetorno to set
     */
    public void setMenssagemRetorno(String menssagemRetorno) {
        this.menssagemRetorno = menssagemRetorno;
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param idusuario
     * @return boolean
     */
    public boolean usuarioExistePorId(int idusuario) {
        Usuario u = new UsuarioFacade().find(String.valueOf(idusuario));
        if (u.getIdUsuario() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo login
     * @param login
     * @return boolean
     */
    public boolean usuarioExistePorLogin(String login) {
        Usuario u = new UsuarioController().getPorLogin(login);
        if (u.getIdUsuario() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param idagente
     * @return boolean
     */
    public boolean agenteExisteId(int idagente) {
        Agente a = new AgenteFacade().find(String.valueOf(idagente));
        if (a.getIdagente() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param iddoenca
     * @return boolean
     */
    public boolean doencaExisteId(int iddoenca) {
        Doenca d = new DoencaFacade().find(String.valueOf(iddoenca));
        if (d.getIddoenca() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean sintomaExisteId(int id) {
        Sintoma s = new SintomaFacade().find(String.valueOf(id));
        if (s.getIdsintoma() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean tratamentoExisteId(int id) {
        Tratamento t = new TratamentoFacade().find(String.valueOf(id));
        if (t.getIdtratamento() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean caracteristicaExisteId(int id) {
        Caracteristica c = new CaracteristicaFacade().find(String.valueOf(id));
        if (c.getIdcaracteristica() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean plantaExisteId(int id) {
        Planta p = new PlantaFacade().find(String.valueOf(id));
        if (p.getIdplanta() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean parteExisteId(int id) {
        Parte p = new ParteFacade().find(String.valueOf(id));
        if (p.getIdparte() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean plantaParteExisteId(int id) {
        PlantaParte pp = new PlantaParteFacade().find(String.valueOf(id));
        if (pp.getIdPlantaParte() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean familiaExisteId(int id) {
        Familia f = new FamiliaFacade().find(String.valueOf(id));
        if (f.getIdfamilia() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * testa se o ojejeto existe pelo id
     * @param id
     * @return boolean
     */
    public boolean generoExisteId(int id) {
        Genero g = new GeneroFacade().find(String.valueOf(id));
        if (g.getIdgenero() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the analise
     */
    public Analise getAnalise() {
        return analise;
    }

    /**
     * @param analise the analise to set
     */
    public void setAnalise(Analise analise) {
        this.analise = analise;
    }
}
