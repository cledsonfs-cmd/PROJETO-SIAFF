/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Analise;
import br.gov.rn.emater.Dao.AnaliseDao;
import br.gov.rn.emater.Facade.AgenteFacade;
import br.gov.rn.emater.Facade.AnaliseFacade;
import java.io.InputStream;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author cledsonfs
 */
public class AnaliseController extends ModeloController<Analise> {

    @Override
    public boolean novo() {
        analise = new Analise();
        return true;
    }

    @Override
    public Analise get() {
        if (analise == null) {
            novo();
        }
        return analise;
    }

    @Override
    public void set(Analise objeto) {
        analise = objeto;
    }

    @Override
    public boolean excluir() throws Exception {
        if (agente == null) {
            this.setMenssagemRetorno("Objeto agente nulo!");
            return false;
        } else {
            boolean r = new AgenteFacade().remove(agente);
            if (r) {
                this.setMenssagemRetorno("Agente excluido com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir o agente!!");
                return false;
            }

        }
    }

    @Override
    public boolean salvar() throws Exception {
        return salvar(true);
    }

    /**
     * Salva/Altera um objeto implementando as regras de negocio
     * @param alteracao
     * @return boolean
     * @throws Exception
     */
    public boolean salvar(boolean alteracao) throws Exception {
        if (analise == null) {
            this.setMenssagemRetorno("Objeto analise nulo!");
            return false;
        } else if (analise.getNome() == null || analise.getNome().trim().length() == 0) {
            this.setMenssagemRetorno("Nome inválido!");
            return false;
        } else if (analise.getIdAnalise() == 0) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else {
            boolean r = new AnaliseFacade().createUpdate(analise);
            if (r) {
                this.setMenssagemRetorno("Analise salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a analise!");
                return false;
            }
        }
    }

    @Override
    public List<Analise> getTodos() throws Exception {
        return new AnaliseFacade().findAll();
    }

    public Analise find(String id){
        return new AnaliseFacade().find(id);
    }

    public InputStream getImageToStream(ImageIcon i) {
        return new AnaliseDao().ImagetoStream(i);
    }
}
