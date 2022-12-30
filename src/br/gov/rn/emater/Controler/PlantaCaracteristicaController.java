/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Caracteristica;
import br.gov.rn.emater.Classes.Planta;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import br.gov.rn.emater.Facade.CaracteristicaFacade;
import br.gov.rn.emater.Facade.PlantaCaracteristicaFacade;
import br.gov.rn.emater.Facade.PlantaFacade;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe PlantaCaracteristicaController
 * Data: 05/2010
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PlantaCaracteristicaController extends ModeloController<PlantaCaracteristica> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        plantaCaracteristica = new PlantaCaracteristica();
        return true;
    }

    /**
     * Retorna um objeto
     * @return PlantaCaracteristica
     */
    @Override
    public PlantaCaracteristica get() {
        if (plantaCaracteristica == null) {
            novo();
        }
        return plantaCaracteristica;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(PlantaCaracteristica objeto) {
        plantaCaracteristica = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (plantaCaracteristica == null) {
            this.setMenssagemRetorno("Objeto PlantaCaracteristica nulo!");
            return false;
        } else {
            boolean r = new PlantaCaracteristicaFacade().remove(plantaCaracteristica);
            if (r) {
                this.setMenssagemRetorno("PlantaCaracteristica excluido com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir o PlantaCaracteristica!");
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
        if (plantaCaracteristica == null) {
            this.setMenssagemRetorno("Objeto PlantaCaracteristica nulo!");
            return false;
        } else if (plantaCaracteristica.getIdCaracteristica() == 0 || !caracteristicaExisteId(plantaCaracteristica.getIdCaracteristica())) {
            this.setMenssagemRetorno("idcaracteristica inválido!");
            return false;
        } else if (plantaCaracteristica.getIdPlanta() == 0 || !plantaExisteId(plantaCaracteristica.getIdPlanta())) {
            this.setMenssagemRetorno("idplanta inválido!");
            return false;
        } else {
            boolean r = new PlantaCaracteristicaFacade().createUpdate(plantaCaracteristica);
            if (r) {
                this.setMenssagemRetorno("PlantaCaracteristica salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o PlantaCaracteristica!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<PlantaCaracteristica>
     * @throws Exception
     */
    @Override
    public List<PlantaCaracteristica> getTodos() throws Exception {
        return new PlantaCaracteristicaFacade().findAll();
    }

    /**
     * Retorna uma lista de plantas com base em um id de caracteristica
     * @param idCaracteristica
     * @return List<Planta>
     * @throws Exception
     */
    public List<Planta> getPlantaPorIdCaracteristica(int idCaracteristica) throws Exception {
        List<Planta> resultado = new ArrayList<Planta>();
        List<PlantaCaracteristica> pc = new PlantaCaracteristicaFacade().findWhere(" where idcaracteristica=" + idCaracteristica);
        if (pc.size() > 0) {
            String opc = "";
            for (PlantaCaracteristica pc1 : pc) {
                opc += pc1.getIdPlanta() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            resultado = new PlantaFacade().findWhere(" where idplanta in (" + opc + ")");
        }
        return resultado;
    }

    /**
     * Retorna uma lista de caracteristicas com base em um id de planta
     * @param idPlanta
     * @return List<Caracteristica>
     * @throws Exception
     */
    public List<Caracteristica> getCaracteristicaPorIdPlanta(int idPlanta) throws Exception {
        List<Caracteristica> resultado = new ArrayList<Caracteristica>();
        List<PlantaCaracteristica> pc = new PlantaCaracteristicaFacade().findWhere(" where idplanta=" + idPlanta);
        if (pc.size() > 0) {
            String opc = "";
            for (PlantaCaracteristica pc1 : pc) {
                opc += pc1.getIdCaracteristica() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            resultado = new CaracteristicaFacade().findWhere(" where idcaracteristica in (" + opc + ")");
        }
        return resultado;
    }
}
