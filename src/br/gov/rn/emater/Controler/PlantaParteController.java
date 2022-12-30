/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Facade.DoencaFacade;
import br.gov.rn.emater.Facade.PlantaParteFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe PlantaParteController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaParteController extends ModeloController<PlantaParte> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        plantaParte = new PlantaParte();
        return true;
    }

    /**
     * Retorna um objeto
     * @return PlantaParte
     */
    @Override
    public PlantaParte get() {
        if (plantaParte == null) {
            novo();
        }
        return plantaParte;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(PlantaParte objeto) {
        plantaParte = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (plantaParte == null) {
            this.setMenssagemRetorno("Objeto PlantaParte nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("O PlantaParte não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new PlantaParteFacade().remove(plantaParte);
                if (r) {
                    this.setMenssagemRetorno("PlantaParte excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir o PlantaParte!");
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
        if (plantaParte == null) {
            this.setMenssagemRetorno("Objeto PlantaParte nulo!");
            return false;
        } else if (plantaParte.getIdParte() == 0 || !parteExisteId(plantaParte.getIdParte())) {
            this.setMenssagemRetorno("idparte inválido!");
            return false;
        } else if (plantaParte.getIdPlanta() == 0 || !plantaExisteId(plantaParte.getIdPlanta())) {
            this.setMenssagemRetorno("idplanta inválido!");
            return false;
        } else if (plantaParteExiste(plantaParte) && !alteracao) {
            this.setMenssagemRetorno("PlantaParte já existe");
            return false;
        } else {
            boolean r = new PlantaParteFacade().createUpdate(plantaParte);
            if (r) {
                this.setMenssagemRetorno("PlantaParte salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o PlantaParte!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<PlantaParte>
     * @throws Exception
     */
    @Override
    public List<PlantaParte> getTodos() throws Exception {
        return new PlantaParteFacade().findAll();
    }

    /**
     * Retorna uma lista de objetos cadastrados com base em um id de planta
     * @param idPlanta
     * @return List<PlantaParte>
     */
    public List<PlantaParte> getPlantaPartePorIdPlanta(int idPlanta) {
        return getPlantaPartePorIdPlantaIdParte(idPlanta, -1);
    }

    /**
     * Retorna uma lista de objetos cadastrados com base em um id de parte
     * @param idParte
     * @return List<PlantaParte>
     */
    public List<PlantaParte> getPlantaPartePorIdParte(int idParte) {
        return getPlantaPartePorIdPlantaIdParte(-1, idParte);
    }

    /**
     * Retorna um id da plantaparte com base em um id de planta
     * @param idPlanta
     * @return int
     */
    public int getIdPorIdPlanta(int idPlanta) {
        return getIdPorIdPlantaIdParte(idPlanta, -1);
    }

    /**
     * Retorna um id da plantaparte com base em um id de parte
     * @param idParte
     * @return int
     */
    public int getIdPorIdParte(int idParte) {
        return getIdPorIdPlantaIdParte(-1, idParte);
    }

    /**
     * Retorna um id da plantaparte com base em um id de planta e id parte
     * @param idPlanta
     * @param idParte
     * @return int
     */
    public int getIdPorIdPlantaIdParte(int idPlanta, int idParte) {
        List<PlantaParte> pptemps = getPlantaPartePorIdPlantaIdParte(idPlanta, idParte);
        if (pptemps.size() == 0) {
            return -1;
        } else {
            return pptemps.get(0).getIdPlantaParte();
        }
    }

    /**
     * Retorna um id da plantaparte com base em um id de planta e id parte
     * @param idPlanta
     * @param idParte
     * @return List<PlantaParte>
     */
    public List<PlantaParte> getPlantaPartePorIdPlantaIdParte(int idPlanta, int idParte) {
        String opc = "";
        if (idPlanta == -1) {
            opc = " where idparte=" + idParte;
        } else if (idParte == -1) {
            opc = " where idplanta=" + idPlanta;
        } else {
            opc = " where idplanta=" + idPlanta + " and idparte=" + idParte;
        }
        List<PlantaParte> pptemps = new PlantaParteFacade().findWhere(opc);
        return pptemps;
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (plantaParte == null) {
            this.setMenssagemRetorno("Objeto PlantaParte nulo!");
            return false;
        } else {
            List<Doenca> doencas = new ArrayList<Doenca>();
            try {
                doencas = new DoencaFacade().findWhere(" where idplantaparte=" + plantaParte.getIdPlantaParte());
            } catch (Exception ex) {
                Logger.getLogger(PlantaParteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (doencas.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Testa se a PlantaParte existe com base em um outro objeto instanciado
     * @param pp
     * @return boolean
     */
    public boolean plantaParteExiste(PlantaParte pp) {
        int r = getIdPorIdPlantaIdParte(pp.getIdPlanta(), pp.getIdParte());
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }
}
