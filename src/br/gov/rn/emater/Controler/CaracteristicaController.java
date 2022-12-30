/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Caracteristica;
import br.gov.rn.emater.Classes.PlantaCaracteristica;
import br.gov.rn.emater.Facade.CaracteristicaFacade;
import br.gov.rn.emater.Facade.PlantaCaracteristicaFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe CaracteristicaController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class CaracteristicaController extends ModeloController<Caracteristica> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        caracteristica = new Caracteristica();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Caracteristica
     */
    @Override
    public Caracteristica get() {
        if (caracteristica == null) {
            novo();
        }
        return caracteristica;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Caracteristica objeto) {
        caracteristica = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (caracteristica == null) {
            this.setMenssagemRetorno("Objeto caractrística nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("A caractrística não pode ser excluído por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new CaracteristicaFacade().remove(caracteristica);
                if (r) {
                    this.setMenssagemRetorno("Característica excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir a característica!");
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
        if (caracteristica == null) {
            this.setMenssagemRetorno("Objeto característica nulo!");
            return false;
        } else if (caracteristica.getDescricao() == null || caracteristica.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Descrição inválida!");
            return false;
        } else if (caracteristica.getIdUsuario() == 0 || !usuarioExistePorId(caracteristica.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (caracteristicaExiste(caracteristica) && !alteracao) {
            this.setMenssagemRetorno("Característica já existe");
            return false;
        } else {
            boolean r = new CaracteristicaFacade().createUpdate(caracteristica);
            if (r) {
                this.setMenssagemRetorno("Característica salva com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a característica!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Caracteristica>
     * @throws Exception
     */
    @Override
    public List<Caracteristica> getTodos() throws Exception {
        return new CaracteristicaFacade().findAll();
    }

    /**
     * Teste se o objeto existe com base em um outro objeto fornecido
     * @param c
     * @return boolean
     */
    public boolean caracteristicaExiste(Caracteristica c) {
        int valor = new CaracteristicaFacade().findWhere("where descricao='" + c.getDescricao() + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto pelo id
     * @param id
     * @return Caracteristica
     */
    public Caracteristica localizarPorID(int id) {
        return new CaracteristicaFacade().find(String.valueOf(id));
    }

    /**
     * Retorna uma lista de caracteristicas com base em um id de uma planta
     * @param idPlanta
     * @return List<Caracteristica>
     */
    public List<Caracteristica> getCaracteristicasPorIdPlanta(int idPlanta) {
        List<PlantaCaracteristica> pcs = new PlantaCaracteristicaFacade().findWhere(" where idplanta=" + idPlanta);
        String opc = "";
        for (PlantaCaracteristica pc : pcs) {
            opc += pc.getIdCaracteristica() + ",";
        }
        opc = opc.substring(0, opc.length() - 1);
        return new CaracteristicaFacade().findWhere(" where idcaracteristica in (" + opc + ") order by idcaracteristica");
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    public boolean possuiVinculo() {
        if (caracteristica == null) {
            this.setMenssagemRetorno("Objeto característica nulo!");
            return false;
        } else {
            List<PlantaCaracteristica> pcs = new ArrayList<PlantaCaracteristica>();
            try {
                pcs = caracteristica.getPlantaCaracteristicas();
            } catch (Exception ex) {
                Logger.getLogger(CaracteristicaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (pcs.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
