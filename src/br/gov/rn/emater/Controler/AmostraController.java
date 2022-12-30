/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Amostra;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Dao.AmostraDao;
import br.gov.rn.emater.Facade.AmostraFacade;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import javax.swing.ImageIcon;

/**
 * Classe AmostraController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class AmostraController extends ModeloController<Amostra> {

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        amostra = new Amostra();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Amostra
     */
    @Override
    public Amostra get() {
        if (amostra == null) {
            novo();
        }
        return amostra;
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Amostra objeto) {
        amostra = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (amostra == null) {
            this.setMenssagemRetorno("Objeto amostra nulo!");
            return false;
        } else {
            boolean r = new AmostraFacade().remove(amostra);
            if (r) {
                this.setMenssagemRetorno("Amostra excluida com sucesso!");
                return true;
            } else {
                this.setMenssagemRetorno("Houve um erro ao excluir a amostra!!");
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
        if (amostra == null) {
            this.setMenssagemRetorno("Objeto amostra nulo!");
            return false;
        } else if (amostra.getIdUsuario() == 0 || !this.usuarioExistePorId(amostra.getIdUsuario())) {
            this.setMenssagemRetorno("Idusuario inválido inválido!");
            return false;
        } else {
            boolean r = new AmostraFacade().createUpdate(amostra);
            if (r) {
                this.setMenssagemRetorno("Amostra salva com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir a amostra!");
                return false;
            }
        }
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Amostra>
     * @throws Exception
     */
    @Override
    public List<Amostra> getTodos() throws Exception {
        return new AmostraFacade().findAll();
    }

    /**
     * Retorna uma lista de amostras que tenha o id da doenca fornecido
     * @param idDoenca
     * @return List<Amostra>
     * @throws Exception
     */
    public List<Amostra> getTodosCadastradasDaDoenca(int idDoenca) throws Exception {
        return new AmostraFacade().findWhere(" where iddoenca=" + idDoenca);
    }

    public TreeMap<String, BufferedImage> getBufferedImagesAll() {
        TreeMap<String, BufferedImage> bufferedImages = new TreeMap<String, BufferedImage>();
        List<Amostra> amostras = new AmostraFacade().findAll();
        for (Amostra amostra : amostras) {
            bufferedImages.put(amostra.getDoenca().getNomecientifico().trim() + "-" + amostra.getIdamostra(), ImageIconBufferedImage(amostra.getImagem()));
        }
        return bufferedImages;
    }

    public BufferedImage ImageIconBufferedImage(ImageIcon i) {
        //BufferedImage bi = new BufferedImage(8, 8, BufferedImage.TYPE_INT_BGR);
        BufferedImage bi = new BufferedImage(i.getIconWidth(), i.getIconWidth(), BufferedImage.TYPE_INT_BGR);
        Graphics g = bi.createGraphics();
        g.drawImage(i.getImage(), 0, 0, null);
        return bi;
    }

    public List<InputStream> getListaInputStreamImagens() throws SQLException {
        return new AmostraFacade().getInputStream();
    }

    public Amostra find(String id) throws Exception {
        return new AmostraFacade().find(id);
    }

    public List<Amostra> getTodosPorIdPlanta(String idPlanta) throws Exception {
        return new AmostraFacade().findPorIdPlanta(idPlanta);
    }

    public InputStream getImageStream(String idAmostra) throws SQLException {
        return new AmostraDao().getImagemStream(idAmostra);
    }

    public InputStream getImageToStream(ImageIcon i) {
        return new AmostraDao().ImagetoStream(i);
    }
}
