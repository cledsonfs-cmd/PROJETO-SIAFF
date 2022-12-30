/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Facade;

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
import java.util.List;

/**
 * Classe: ModeloFacade
 * Data: 05/2010
 * @param <C>
 * @author cledsonfs,ururai
 * @version 1.0
 */
public abstract class ModeloFacade<C> {

    protected Agente agente = null;
    protected Analise analise = null;
    protected Amostra amostra = null;
    protected Familia familia = null;
    protected Sintoma sintoma = null;
    protected Genero genero = null;
    protected DoencaSintoma doencaSintoma = null;
    protected DoencaAgente doencaAgente = null;
    protected Planta planta = null;
    protected Parte parte = null;
    protected PlantaParte plantaParte = null;
    protected Caracteristica caracteristica = null;
    protected PlantaCaracteristica plantaCaracteristica = null;
    protected Doenca doenca = null;
    protected Tratamento tratamento = null;
    protected DoencaTratamento doencaTratamento = null;
    protected Usuario usuario = null;
    protected List<Agente> agentes = null;
    protected List<Analise> analises = null;
    protected List<Amostra> amostras = null;
    protected List<Familia> familias = null;
    protected List<Sintoma> sintomas = null;
    protected List<Genero> generos = null;
    protected List<DoencaSintoma> doencaSintomas = null;
    protected List<DoencaAgente> doencaAgentes = null;
    protected List<Planta> plantas = null;
    protected List<Parte> partes = null;
    protected List<PlantaParte> plantaPartes = null;
    protected List<Caracteristica> caracteristicas = null;
    protected List<PlantaCaracteristica> plantaCaracteristicas = null;
    protected List<Doenca> doencas = null;
    protected List<Tratamento> tratamentos = null;
    protected List<DoencaTratamento> doencaTratamentos = null;
    protected List<Usuario> usuarios = null;
    protected int registros = 0;
    protected boolean retorno = false;

    /**
     * Cria/Atualiza
     * @param object
     * @return boolean
     */
    public abstract boolean createUpdate(C object);

    /**
     * Remove
     * @param object
     * @return boolean
     */
    public abstract boolean remove(C object);

    /**
     * Localiza por id
     * @param id
     * @return boolean
     */
    public abstract C find(String id);

    /**
     * Localiza todos
     * @return List<C>
     */
    public abstract List<C> findAll();

    /**
     * Localiza com where
     * @param opc
     * @return List<C>
     */
    public abstract List<C> findWhere(String opc);

    /**
     * Conta o numero de registros
     * @return int
     */
    public abstract int count();
}
