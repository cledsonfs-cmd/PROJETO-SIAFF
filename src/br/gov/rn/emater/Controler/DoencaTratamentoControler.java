/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.Tratamento;
import br.gov.rn.emater.Facade.DoencaFacade;
import br.gov.rn.emater.Facade.DoencaTratamentoFacade;
import br.gov.rn.emater.Facade.TratamentoFacade;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaTratamentoControler
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaTratamentoControler extends ModeloController<DoencaTratamento> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        if (doencaTratamento == null) {
            novo();
        }
        return true;
    }

    /**
     * Retorna um objeto
     * @return DoencaTratamento
     */
    @Override
    public DoencaTratamento get() {
        return doencaTratamento;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(DoencaTratamento objeto) {
        doencaTratamento = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (doencaTratamento == null) {
            this.setMenssagemRetorno("Objeto DoencaTratamento nulo!");
            return false;
        } else {
            boolean r = new DoencaTratamentoFacade().remove(doencaTratamento);
            if (r) {
                this.setMenssagemRetorno("DoencaTratamento excluido com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir o DoencaTratamento!");
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
        if (doencaTratamento == null) {
            this.setMenssagemRetorno("Objeto DoencaTratamento nulo!");
            return false;
        } else if (doencaTratamento.getIdTratamento() == 0 || !tratamentoExisteId(doencaTratamento.getIdTratamento())) {
            this.setMenssagemRetorno("idsintoma inválido!");
            return false;
        } else if (doencaTratamento.getIdDoenca() == 0 || !doencaExisteId(doencaTratamento.getIdDoenca())) {
            this.setMenssagemRetorno("iddoenca inválido!");
            return false;
        } else {
            boolean r = new DoencaTratamentoFacade().createUpdate(doencaTratamento);
            if (r) {
                this.setMenssagemRetorno("DoencaTratamento salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o DoencaTratamento!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<DoencaTratamento>
     * @throws Exception
     */
    @Override
    public List<DoencaTratamento> getTodos() throws Exception {
        return new DoencaTratamentoFacade().findAll();
    }

    /**
     * Retorna uma lista de DoencaTratamento com base em um id doenca
     * @param iddoenca
     * @return List<DoencaTratamento>
     * @throws Exception
     */
    public List<DoencaTratamento> getTodosPorIdDoenca(int iddoenca) throws Exception {
        return new DoencaTratamentoFacade().findWhere(" where iddoenca=" + iddoenca);
    }

    /**
     * Retorna uma lista de Doenca com base em um id tratamento
     * @param idTratamento
     * @return List<Doenca>
     * @throws Exception
     */
    public List<Doenca> getDoencaIdTratamento(int idTratamento) throws Exception {
        List<Doenca> resultado = new ArrayList<Doenca>();
        List<DoencaTratamento> pc = new DoencaTratamentoFacade().findWhere(" where idtratamento=" + idTratamento);
        if (pc.size() > 0) {
            String opc = "";
            for (DoencaTratamento pc1 : pc) {
                opc += pc1.getIdDoenca() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            resultado = new DoencaFacade().findWhere(" where iddoenca in (" + opc + ")");
        }
        return resultado;
    }

    /**
     * Retorna uma lista de Tratamento com base em um id doenca
     * @param idDoenca
     * @return List<Tratamento>
     * @throws Exception
     */
    public List<Tratamento> getTratamentoPorIdDoenca(int idDoenca) throws Exception {
        List<Tratamento> resultado = new ArrayList<Tratamento>();
        List<DoencaTratamento> pc = new DoencaTratamentoFacade().findWhere(" where iddoenca=" + idDoenca);
        if (pc.size() > 0) {
            String opc = "";
            for (DoencaTratamento pc1 : pc) {
                opc += pc1.getIdDoenca() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            resultado = new TratamentoFacade().findWhere(" where idtratamento in (" + opc + ")");
        }
        return resultado;
    }
}
