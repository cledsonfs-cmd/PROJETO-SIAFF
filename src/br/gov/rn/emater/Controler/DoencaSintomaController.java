/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.Sintoma;
import br.gov.rn.emater.Facade.DoencaFacade;
import br.gov.rn.emater.Facade.DoencaSintomaFacade;
import br.gov.rn.emater.Facade.SintomaFacade;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaSintomaController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaSintomaController extends ModeloController<DoencaSintoma> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        doencaSintoma = new DoencaSintoma();
        return true;
    }

    /**
     * Retorna um objeto
     * @return DoencaSintoma
     */
    @Override
    public DoencaSintoma get() {
        if (doencaSintoma == null) {
            novo();
        }
        return doencaSintoma;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(DoencaSintoma objeto) {
        doencaSintoma = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (doencaSintoma == null) {
            this.setMenssagemRetorno("Objeto DoencaSintoma nulo!");
            return false;
        } else {
            boolean r = new DoencaSintomaFacade().remove(doencaSintoma);
            if (r) {
                this.setMenssagemRetorno("DoencaSintoma excluido com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir o DoencaSintoma!");
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
        if (alteracao) {
            this.setMenssagemRetorno("Alteração não se aplica!");
            return true;
        }
        if (doencaSintoma == null) {
            this.setMenssagemRetorno("Objeto DoencaSintoma nulo!");
            return false;
        } else if (doencaSintoma.getIdSintoma() == 0 || !sintomaExisteId(doencaSintoma.getIdSintoma())) {
            this.setMenssagemRetorno("idsintoma inválido!");
            return false;
        } else if (doencaSintoma.getIdDoenca() == 0 || !doencaExisteId(doencaSintoma.getIdDoenca())) {
            this.setMenssagemRetorno("iddoenca inválido!");
            return false;
        } else {
            boolean r = new DoencaSintomaFacade().createUpdate(doencaSintoma);
            if (r) {
                this.setMenssagemRetorno("DoencaSintoma salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o DoencaSintoma!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<DoencaSintoma>
     * @throws Exception
     */
    @Override
    public List<DoencaSintoma> getTodos() throws Exception {
        return new DoencaSintomaFacade().findAll();
    }

    /**
     * Retorna uma lista de DoencaSintoma com base em um id de uma doenca
     * @param iddoenca
     * @return List<DoencaSintoma>
     * @throws Exception
     */
    public List<DoencaSintoma> getTodosPorIdDoenca(int iddoenca) throws Exception {
        return new DoencaSintomaFacade().findWhere(" where iddoenca=" + iddoenca);
    }

    /**
     * Retorna uma lista de Doenca com base em um id de um sintoma
     * @param idSintoma
     * @return List<Doenca>
     * @throws Exception
     */
    public List<Doenca> getDoencaIdSintoma(int idSintoma) throws Exception {
        List<Doenca> resultado = new ArrayList<Doenca>();
        List<DoencaSintoma> pc = new DoencaSintomaFacade().findWhere(" where idsintoma=" + idSintoma);
        if (pc.size() > 0) {
            String opc = "";
            for (DoencaSintoma pc1 : pc) {
                opc += pc1.getIdDoenca() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            resultado = new DoencaFacade().findWhere(" where iddoenca in (" + opc + ")");
        }
        return resultado;
    }

    /**
     * Retorna uma lista de Sintoma com base em um id de uma doenca
     * @param idDoenca
     * @return List<Sintoma>
     * @throws Exception
     */
    public List<Sintoma> getSintomaPorIdDoenca(int idDoenca) throws Exception {
        List<Sintoma> resultado = new ArrayList<Sintoma>();
        List<DoencaSintoma> pc = new DoencaSintomaFacade().findWhere(" where iddoenca=" + idDoenca);
        if (pc.size() > 0) {
            String opc = "";
            for (DoencaSintoma pc1 : pc) {
                opc += pc1.getIdSintoma() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            resultado = new SintomaFacade().findWhere(" where iddoenca in (" + opc + ")");
        }
        return resultado;
    }
}
