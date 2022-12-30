/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Apoio;

import br.gov.rn.emater.Dao.AgenteDao;
import br.gov.rn.emater.Dao.AmostraDao;
import br.gov.rn.emater.Dao.AnaliseDao;
import br.gov.rn.emater.Dao.CaracteristicaDao;
import br.gov.rn.emater.Dao.DoencaAgenteDao;
import br.gov.rn.emater.Dao.DoencaDao;
import br.gov.rn.emater.Dao.DoencaSintomaDao;
import br.gov.rn.emater.Dao.DoencaTratamentoDao;
import br.gov.rn.emater.Dao.FamiliaDao;
import br.gov.rn.emater.Dao.GeneroDao;
import br.gov.rn.emater.Dao.ParteDao;
import br.gov.rn.emater.Dao.PlantaCaracteristicaDao;
import br.gov.rn.emater.Dao.PlantaDao;
import br.gov.rn.emater.Dao.PlantaParteDao;
import br.gov.rn.emater.Dao.SintomaDao;
import br.gov.rn.emater.Dao.TratamentoDao;
import br.gov.rn.emater.Dao.UsuarioDao;

/**
 * Classe responsável por gerenciar os Daos do sistema
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class PoolConexao {

    private static AgenteDao agenteDao;
    private static AmostraDao amostraDao;
    private static CaracteristicaDao caracteristicaDao;
    private static DoencaAgenteDao doencaAgenteDao;
    private static DoencaSintomaDao doencaSintomaDao;
    private static DoencaTratamentoDao doencaTratamentoDao;
    private static PlantaCaracteristicaDao plantaCaracteristicaDao;
    private static DoencaDao doencaDao;
    private static FamiliaDao familiaDao;
    private static GeneroDao generoDao;
    private static ParteDao parteDao;
    private static PlantaDao plantaDao;
    private static SintomaDao sintomaDao;
    private static PlantaParteDao plantaParteDao;
    private static TratamentoDao tratamentoDao;
    private static UsuarioDao usuarioDao;
    private static AnaliseDao analiseDao;

    /**
     * Retorna uma instancia do objeto AgenteDao
     * @return
     */
    public static AnaliseDao getAnaliseDao() {
        if (analiseDao == null) {
            analiseDao = new AnaliseDao();
        }
        return analiseDao;
    }
    /**
     * Retorna uma instancia do objeto AgenteDao
     * @return
     */
    public static AgenteDao getAgenteDao() {
        if (agenteDao == null) {
            agenteDao = new AgenteDao();
        }
        return agenteDao;
    }

    /**
     * Retorna uma instancia do objeto FamiliaDao
     * @return
     */
    public static FamiliaDao getFamiliaDao() {
        if (familiaDao == null) {
            familiaDao = new FamiliaDao();
        }
        return familiaDao;
    }

    /**
     * Retorna uma instancia do objeto AmostraDao
     * @return
     */
    public static AmostraDao getAmostraDao() {
        if (amostraDao == null) {
            amostraDao = new AmostraDao();
        }
        return amostraDao;
    }

    /**
     * Retorna uma instancia do objeto CaracteristicaDao
     * @return
     */
    public static CaracteristicaDao getCaracteristicaDao() {
        if (caracteristicaDao == null) {
            caracteristicaDao = new CaracteristicaDao();
        }
        return caracteristicaDao;
    }

    /**
     * Retorna uma instancia do objeto DoencaAgenteDao
     * @return
     */
    public static DoencaAgenteDao getDoencaAgenteDao() {
        if (doencaAgenteDao == null) {
            doencaAgenteDao = new DoencaAgenteDao();
        }
        return doencaAgenteDao;
    }

    /**
     * Retorna uma instancia do objeto DoencaDao
     * @return
     */
    public static DoencaDao getDoencaDao() {
        if (doencaDao == null) {
            doencaDao = new DoencaDao();
        }
        return doencaDao;
    }

    /**
     * Retorna uma instancia do objeto GeneroDao
     * @return
     */
    public static GeneroDao getGeneroDao() {
        if (generoDao == null) {
            generoDao = new GeneroDao();
        }
        return generoDao;
    }

    /**
     * Retorna uma instancia do objeto ParteDao
     * @return
     */
    public static ParteDao getParteDao() {
        if (parteDao == null) {
            parteDao = new ParteDao();
        }
        return parteDao;
    }

    /**
     * Retorna uma instancia do objeto PlantaDao
     * @return
     */
    public static PlantaDao getPlantaDao() {
        if (plantaDao == null) {
            plantaDao = new PlantaDao();
        }
        return plantaDao;
    }

    /**
     * Retorna uma instancia do objeto SintomaDao
     * @return
     */
    public static SintomaDao getSintomaDao() {
        if (sintomaDao == null) {
            sintomaDao = new SintomaDao();
        }
        return sintomaDao;
    }

    /**
     * Retorna uma instancia do objeto PlantaParteDao
     * @return
     */
    public static PlantaParteDao getPlantaParteDao() {
        if (plantaParteDao == null) {
            plantaParteDao = new PlantaParteDao();
        }
        return plantaParteDao;
    }

    /**
     * Retorna uma instancia do objeto TratamentoDao
     * @return
     */
    public static TratamentoDao getTratamentoDao() {
        if (tratamentoDao == null) {
            tratamentoDao = new TratamentoDao();
        }
        return tratamentoDao;
    }

    /**
     * Retorna uma instancia do objeto DoencaSintomaDao
     * @return
     */
    public static DoencaSintomaDao getDoencaSintomaDao() {
        if (doencaSintomaDao == null) {
            doencaSintomaDao = new DoencaSintomaDao();
        }
        return doencaSintomaDao;
    }

    /**
     * Retorna uma instancia do objeto DoencaTratamentoDao
     * @return
     */
    public static DoencaTratamentoDao getDoencaTratamentoDao() {
        if (doencaTratamentoDao == null) {
            doencaTratamentoDao = new DoencaTratamentoDao();
        }
        return doencaTratamentoDao;
    }

    /**
     * Retorna uma instancia do objeto PlantaCaracteristicaDao
     * @return
     */
    public static PlantaCaracteristicaDao getPlantaCaracteristicaDao() {
        if (plantaCaracteristicaDao == null) {
            plantaCaracteristicaDao = new PlantaCaracteristicaDao();
        }
        return plantaCaracteristicaDao;
    }

    /**
     * Retorna uma instancia do objeto UsuarioDao
     * @return
     */
    public static UsuarioDao getUsuarioDao() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDao();
        }
        return usuarioDao;
    }
}
