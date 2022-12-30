/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Facade.PlantaCaracteristicaFacade;
import br.gov.rn.emater.Facade.PlantaFacade;
import br.gov.rn.emater.Facade.PlantaParteFacade;
import java.util.List;

/**
 * Classe PlantaController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaController extends ModeloController<Planta> {

    private boolean sucesso = true;
    private PlantaParteController plantaParteController = new PlantaParteController();
    private PlantaCaracteristicaController plantaCaracteristicaController = new PlantaCaracteristicaController();

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        planta = new Planta();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Planta
     */
    @Override
    public Planta get() {
        if (planta == null) {
            novo();
        }
        return planta;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Planta objeto) {
        planta = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (planta == null) {
            this.setMenssagemRetorno("Objeto planta nulo!");
            return false;
        } else {
            sucesso = true;

            List<PlantaParte> plantaPartes = planta.getPlantaParte();
            for (PlantaParte pEParte : plantaPartes) {
                plantaParteController.set(pEParte);
                sucesso = sucesso && plantaParteController.excluir();
                if (!sucesso) {
                    this.setMenssagemRetorno(plantaParteController.getMenssagemRetorno());
                    return false;
                }
            }

            if (sucesso) {
                List<PlantaCaracteristica> plantaCaracteristicas = planta.getPlantaCaracteristicas();
                for (PlantaCaracteristica pECaracteristica : plantaCaracteristicas) {
                    plantaCaracteristicaController.set(pECaracteristica);
                    sucesso = sucesso && plantaCaracteristicaController.excluir();
                    if (!sucesso) {
                        this.setMenssagemRetorno(plantaCaracteristicaController.getMenssagemRetorno());
                        return false;
                    }
                }
            }

            sucesso = new PlantaFacade().remove(planta);

            if (sucesso) {
                this.setMenssagemRetorno("planta excluida com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir a planta!");
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
        if (planta == null) {
            this.setMenssagemRetorno("Objeto planta nulo!");
            return false;
        } else if (planta.getNomecientifico() == null || planta.getNomecientifico().trim().length() == 0) {
            this.setMenssagemRetorno("nome cientifico inválido!");
            return false;
        } else if (planta.getNomepopular() == null || planta.getNomepopular().trim().length() == 0) {
            this.setMenssagemRetorno("nome popular inválido!");
            return false;
        } else if (planta.getIdUsuario() == 0 && !usuarioExistePorId(planta.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (planta.getIdGenero() == 0 && !generoExisteId(planta.getIdGenero())) {
            this.setMenssagemRetorno("gênero inválido!");
            return false;
        } else if (plantaExiste(planta) && !alteracao) {
            this.setMenssagemRetorno("Característica já existe");
            return false;
        } else {

            if (alteracao) {
                int cont = 0;
                if (sucesso) {
                    List<PlantaCaracteristica> pcs = new PlantaCaracteristicaFacade().findWhere(" where idplanta=" + planta.getIdplanta());
                    cont = pcs.size();
                    for (int i = 0; i < cont; i++) {
                        plantaCaracteristicaController.set(pcs.get(i));
                        sucesso = sucesso && plantaCaracteristicaController.excluir();
                        if (!sucesso) {
                            this.setMenssagemRetorno(plantaCaracteristicaController.getMenssagemRetorno());
                            return false;
                        }
                    }
                }
                if (sucesso) {
                    List<PlantaParte> pps = new PlantaParteFacade().findWhere(" where idplanta=" + planta.getIdplanta());
                    cont = pps.size();
                    for (int i = 0; i < cont; i++) {
                        plantaParteController.set(pps.get(i));
                        sucesso = sucesso && plantaParteController.excluir();
                        if (!sucesso) {
                            this.setMenssagemRetorno(plantaParteController.getMenssagemRetorno());
                            return false;
                        }
                    }
                }
            }

            sucesso = new PlantaFacade().createUpdate(planta);
            if (!sucesso) {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a planta!");
                return false;
            }
            int ultimoIdPlanta = new PlantaFacade().getUltimoId();

            List<PlantaParte> plantaPartes = planta.getPlantaParte();
            for (PlantaParte pSParte : plantaPartes) {
                pSParte.setIdPlanta(ultimoIdPlanta);
                plantaParteController.set(pSParte);
                sucesso = sucesso && plantaParteController.salvar();
                if (!sucesso) {
                    this.setMenssagemRetorno(plantaParteController.getMenssagemRetorno());
                    return false;
                }

            }

            List<PlantaCaracteristica> plantaCaracteristicas = planta.getPlantaCaracteristicas();
            for (PlantaCaracteristica pSCaracteristica : plantaCaracteristicas) {
                pSCaracteristica.setIdPlanta(ultimoIdPlanta);
                plantaCaracteristicaController.set(pSCaracteristica);
                sucesso = sucesso && plantaCaracteristicaController.salvar();
                if (!sucesso) {
                    this.setMenssagemRetorno(plantaCaracteristicaController.getMenssagemRetorno());
                    return false;
                }
            }
            this.setMenssagemRetorno("planta salva com sucesso");
            return true;
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Planta>
     * @throws Exception
     */
    @Override
    public List<Planta> getTodos() throws Exception {
        return new PlantaFacade().findAll();
    }

    /**
     * Localiza o objeto com base em um id fornecido
     * @param id
     * @return Planta
     */
    public Planta localizarPorID(int id) {
        return new PlantaFacade().find(String.valueOf(id));
    }

    /**
     * Testa se um objeto existe com base em um outro instanciado
     * @param p
     * @return boolea
     */
    public boolean plantaExiste(Planta p) {
        return plantaExiste(p.getNomepopular(), p.getNomecientifico());
    }

    /**
     * Testa se uma planta existe com base em um nome popular e um nome cientifico
     * @param nomePopular
     * @param nomeCientifico
     * @return boolea
     */
    public boolean plantaExiste(String nomePopular, String nomeCientifico) {
        Planta pTemp = localizarPorNomeCientificoENomePopular(nomePopular, nomeCientifico);
        if (pTemp == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retorna uma planta com base me um nome popular e um nome cientifica
     * @param nomePopular
     * @param nomeCientifico
     * @return Planta
     */
    public Planta localizarPorNomeCientificoENomePopular(String nomePopular, String nomeCientifico) {
        List<Planta> plantas = new PlantaFacade().findWhere("where nomepopular='" + nomePopular + "' or nomecientifico='" + nomeCientifico + "'");
        if (plantas.size() == 0) {
            return null;
        } else if (plantas.size() > 1) {
            return null;
        } else {
            return plantas.get(0);
        }
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    private boolean possuiVinculo() {
        if (planta == null) {
            this.setMenssagemRetorno("Objeto planta nulo!");
            return false;
        } else {
            List<String> tabelas = new PlantaFacade().getTabelasOcorrenciaPlanta(planta);
            if (tabelas.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de plantas ordenado po nome cientifico
     */
    public List<Planta> getTodosOrdenadoPorNomeCientifico(){
        return new PlantaFacade().findWhere(" order by nomecientifico");
    }
}
