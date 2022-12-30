/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Amostra;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.DoencaAgente;
import br.gov.rn.emater.Classes.DoencaSintoma;
import br.gov.rn.emater.Classes.DoencaTratamento;
import br.gov.rn.emater.Classes.PlantaParte;
import br.gov.rn.emater.Facade.AmostraFacade;
import br.gov.rn.emater.Facade.DoencaAgenteFacade;
import br.gov.rn.emater.Facade.DoencaFacade;
import br.gov.rn.emater.Facade.DoencaSintomaFacade;
import br.gov.rn.emater.Facade.DoencaTratamentoFacade;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DoencaController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class DoencaController extends ModeloController<Doenca> {

    private boolean sucesso = true;
    private DoencaAgenteController doencaAgenteController = new DoencaAgenteController();
    private DoencaSintomaController doencaSintomaController = new DoencaSintomaController();
    private DoencaTratamentoControler doencaTratamentoControler = new DoencaTratamentoControler();
    private AmostraController amostraControler = new AmostraController();

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        doenca = new Doenca();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Doenca
     */
    @Override
    public Doenca get() {
        if (doenca == null) {
            novo();
        }
        return doenca;
    }

    /**
     * seta um objeto
     * @param objeto
     */
    @Override
    public void set(Doenca objeto) {
        doenca = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (doenca == null) {
            this.setMenssagemRetorno("Objeto doenca nulo!");
            return false;
        } else {
            /* if (possuiVinculo()) {
            this.setMenssagemRetorno("A doenca não pode ser excluído por possuir vinculo em algum cadastro!");
            return false;
            } else {*/
            sucesso = true;
            List<DoencaAgente> doencaAgentes = doenca.getDoencaAgentes();
            for (DoencaAgente dEAgente : doencaAgentes) {
                doencaAgenteController.set(dEAgente);
                sucesso = sucesso && doencaAgenteController.excluir();
                if (!sucesso) {
                    this.setMenssagemRetorno(doencaAgenteController.getMenssagemRetorno());
                    return false;
                }
            }

            List<DoencaSintoma> doencaSintomas = doenca.getDoencaSintomas();
            for (DoencaSintoma dESintoma : doencaSintomas) {
                doencaSintomaController.set(dESintoma);
                sucesso = sucesso && doencaSintomaController.excluir();
                if (!sucesso) {
                    this.setMenssagemRetorno(doencaSintomaController.getMenssagemRetorno());
                    return false;
                }
            }

            List<DoencaTratamento> doencaTratamentos = doenca.getDoencaTratamentos();
            for (DoencaTratamento dETratamento : doencaTratamentos) {
                doencaTratamentoControler.set(dETratamento);
                sucesso = sucesso && doencaTratamentoControler.excluir();
                if (!sucesso) {
                    this.setMenssagemRetorno(doencaTratamentoControler.getMenssagemRetorno());
                    return false;
                }
            }

            List<Amostra> amostras = doenca.getAmostras();
            for (Amostra eAmostra : amostras) {
                amostraControler.set(eAmostra);
                sucesso = sucesso && amostraControler.excluir();
                if (!sucesso) {
                    this.setMenssagemRetorno(amostraControler.getMenssagemRetorno());
                    return false;
                }
            }

            sucesso = new DoencaFacade().remove(doenca);
            if (sucesso) {
                this.setMenssagemRetorno("doenca excluida com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir a doenca!!");
                return false;
            }
            //}
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
        if (doenca == null) {
            this.setMenssagemRetorno("Objeto doenca nulo!");
            return false;
        } else if (doenca.getDescricao() == null || doenca.getDescricao().trim().length() == 0) {
            this.setMenssagemRetorno("Nome popular inválido!");
            return false;
        } else if (doenca.getNomecientifico() == null || doenca.getNomecientifico().length() == 0) {
            this.setMenssagemRetorno("Nome científico inválido!");
            return false;
        } else if (doenca.getIdUsuario() == 0 || !usuarioExistePorId(doenca.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido!");
            return false;
        } else if (doencaExiste(doenca) && !alteracao) {
            this.setMenssagemRetorno("Doenca já existe");
            return false;
        } else if (doenca.getIdPlantaParte() == 0 || !plantaParteExisteId(doenca.getIdPlantaParte())) {
            this.setMenssagemRetorno("Idplantaparte inválido!");
            return false;
        } else {
            if (alteracao) {
                int cont = 0;
                if (sucesso) {
                    List<DoencaAgente> doencaAgentes = new DoencaAgenteFacade().findWhere(" where iddoenca=" + doenca.getIddoenca());
                    cont = doencaAgentes.size();
                    for (int i = 0; i < cont; i++) {
                        doencaAgenteController.set(doencaAgentes.get(i));
                        sucesso = sucesso && doencaAgenteController.excluir();
                        if (!sucesso) {
                            this.setMenssagemRetorno(doencaAgenteController.getMenssagemRetorno());
                            return false;
                        }
                    }
                }
                if (sucesso) {
                    List<DoencaSintoma> doencaSintomas = new DoencaSintomaFacade().findWhere(" where iddoenca=" + doenca.getIddoenca());
                    cont = doencaSintomas.size();
                    for (int i = 0; i < cont; i++) {
                        doencaSintomaController.set(doencaSintomas.get(i));
                        sucesso = sucesso && doencaSintomaController.excluir();
                        if (!sucesso) {
                            this.setMenssagemRetorno(doencaSintomaController.getMenssagemRetorno());
                            return false;
                        }
                    }
                }
                if (sucesso) {
                    List<DoencaTratamento> doencaTratamentos = new DoencaTratamentoFacade().findWhere(" where iddoenca=" + doenca.getIddoenca());
                    cont = doencaTratamentos.size();
                    for (int i = 0; i < cont; i++) {
                        doencaTratamentoControler.set(doencaTratamentos.get(i));
                        sucesso = sucesso && doencaTratamentoControler.excluir();
                        if (!sucesso) {
                            this.setMenssagemRetorno(doencaTratamentoControler.getMenssagemRetorno());
                            return false;
                        }
                    }
                }
                if (sucesso) {
                    List<Amostra> amostras = new AmostraFacade().findWhere(" where iddoenca=" + doenca.getIddoenca());
                    cont = amostras.size();
                    for (int i = 0; i < cont; i++) {
                        amostraControler.set(amostras.get(i));
                        sucesso = sucesso && amostraControler.excluir();
                        if (!sucesso) {
                            this.setMenssagemRetorno(amostraControler.getMenssagemRetorno());
                            return false;
                        }
                    }
                }
            }

            sucesso = new DoencaFacade().createUpdate(doenca);
            if (!sucesso) {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a doenca!");
                return false;
            }

            int ultimoIdDoenca = new DoencaFacade().getUltimoId();

            List<DoencaAgente> doencaAgentes = doenca.getDoencaAgentes();
            for (DoencaAgente dSAgente : doencaAgentes) {
                dSAgente.setIdDoenca(ultimoIdDoenca);
                doencaAgenteController.set(dSAgente);
                sucesso = sucesso && doencaAgenteController.salvar(alteracao);
                if (!sucesso) {
                    this.setMenssagemRetorno(amostraControler.getMenssagemRetorno());
                    return false;
                }
            }

            if (sucesso) {
                List<DoencaSintoma> doencaSintomas = doenca.getDoencaSintomas();
                for (DoencaSintoma dSSintoma : doencaSintomas) {
                    dSSintoma.setIdDoenca(ultimoIdDoenca);
                    doencaSintomaController.set(dSSintoma);
                    sucesso = sucesso && doencaSintomaController.salvar(alteracao);
                    if (!sucesso) {
                        this.setMenssagemRetorno(doencaSintomaController.getMenssagemRetorno());
                        return false;
                    }
                }
            }

            if (sucesso) {
                List<DoencaTratamento> doencaTratamentos = doenca.getDoencaTratamentos();
                for (DoencaTratamento dSTratamento : doencaTratamentos) {
                    dSTratamento.setIdDoenca(ultimoIdDoenca);
                    doencaTratamentoControler.set(dSTratamento);
                    sucesso = sucesso && doencaTratamentoControler.salvar(alteracao);
                    if (!sucesso) {
                        this.setMenssagemRetorno(doencaTratamentoControler.getMenssagemRetorno());
                        return false;
                    }
                }
            }

            if (sucesso) {
                List<Amostra> amostras = doenca.getAmostras();
                for (Amostra sAmostra : amostras) {
                    sAmostra.setIdDoenca(ultimoIdDoenca);
                    sAmostra.setIdUsuario(doenca.getIdUsuario());
                    amostraControler.set(sAmostra);
                    sucesso = sucesso && amostraControler.salvar();
                    if (!sucesso) {
                        this.setMenssagemRetorno(amostraControler.getMenssagemRetorno());
                        return false;
                    }
                }
            }
            this.setMenssagemRetorno("doenca salvo com sucesso");
            return true;
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Doenca>
     * @throws Exception
     */
    @Override
    public List<Doenca> getTodos() throws Exception {
        return new DoencaFacade().findAll();
    }

    /**
     * Testa se uma doenca existe com base no nome cientifico e no nome popular
     * @param nomePopular
     * @param nomeCientifico
     * @return
     */
    public boolean doencaExiste(String nomePopular, String nomeCientifico) {
        int valor = new DoencaFacade().findWhere("where descricao='" + nomePopular + "' or nomecientifico='" + nomeCientifico + "'").size();
        if (valor > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Localiza o objeto com base em um id fornecido
     * @param id
     * @return Doenca
     */
    public Doenca localizarPorID(int id) {
        return new DoencaFacade().find(String.valueOf(id));
    }

    /**
     * Localiza uma doenca com base no nome cientifico e no nome popular
     * @param nomePopular 
     * @param nomeCientifico
     * @return Doenca
     */
    public Doenca localizarPorNomeCientificoENomePopular(String nomePopular, String nomeCientifico) {
        List<Doenca> doencasEncontradas = new DoencaFacade().findWhere("where descricao='" + nomePopular + "' or nomecientifico='" + nomeCientifico + "'");
        if (doencasEncontradas.size() == 0) {
            return null;
        } else if (doencasEncontradas.size() > 1) {
            return null;
        } else {
            return doencasEncontradas.get(0);
        }
    }

    /**
     * Retorna uma lista de doencas com base em um id de planta
     * @param idPlanta
     * @return List<Doenca>
     */
    public List<Doenca> getDoencasPorIdPlanta(int idPlanta) {
        List<Doenca> doencas = new ArrayList<Doenca>();
        List<PlantaParte> plantaPartes = new PlantaParteController().getPlantaPartePorIdPlanta(idPlanta);
        if (plantaPartes.size() > 0) {
            String opc = "";
            for (PlantaParte p : plantaPartes) {
                opc += p.getIdPlantaParte() + ",";
            }
            opc = opc.substring(0, opc.length() - 1);
            doencas = new DoencaFacade().findWhere(" where idplantaparte in (" + opc + ")");
        }
        return doencas;
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    private boolean possuiVinculo() {
        if (doenca == null) {
            this.setMenssagemRetorno("Objeto doenca nulo!");
            return false;
        } else {
            List<String> tabelas = new DoencaFacade().getTabelasOcorrenciaDoenca(doenca);
            if (tabelas.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Testa se o obejeto existe com base em um objeto instanciado
     * @param doenca
     * @return boolean
     */
    public boolean doencaExiste(Doenca doenca) {
        return doencaExiste(doenca.getDescricao(), doenca.getNomecientifico());
    }
}
