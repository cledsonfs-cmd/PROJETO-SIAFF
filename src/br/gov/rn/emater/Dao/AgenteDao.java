/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Agente;
import br.gov.rn.emater.Classes.DoencaAgente;
import br.gov.rn.emater.Classes.Usuario;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Classe AgenteDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class AgenteDao extends ModeloDao<Agente> {

    public AgenteDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(DoencaAgente.class);
        this.associacoes.add(Usuario.class);
    }

    /**
     *
     * @return List<Agente>
     * @throws SQLException
     */
    public List<Agente> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Agente>
     * @throws SQLException
     */
    @Override
    public List<Agente> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from Agente ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Agente> list = new ArrayList<Agente>();
        while (rs.next()) {
            Agente agente = new Agente();
            agente.setIdagente(rs.getInt("idagente"));
            agente.setIdUsuario(rs.getInt("idusuario"));
            agente.setDescricao(rs.getString("descricao"));
            agente.setNomecientifico(rs.getString("nomecientifico"));
            agente.setDatacadastro(rs.getTimestamp("datacadastro"));
            agente.setImagem(StreamToImage(rs.getBinaryStream("imagem")));
            list.add(agente);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Agente
     * @throws SQLException
     */
    @Override
    public Agente get(String condicaoUnica) throws SQLException {
        List<Agente> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Agente at = new Agente();
            at.setIdagente(-1);
            return at;
        } else {
            return list.get(0);
        }
    }

    /**
     * Associar
     * @param objeto
     * @param classes
     * @param condicao
     * @throws SQLException
     */
    @Override
    public void associar(Agente objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(DoencaAgente.class)) {
                objeto.setDoencasAgentes(PoolConexao.getDoencaAgenteDao().getList(" where idagente=" + objeto.getIdagente()));
            } else if (classe.equals(Usuario.class)) {
                objeto.setUsuario(PoolConexao.getUsuarioDao().get(" where idusuario=" + objeto.getIdUsuario()));
            }
        }
    }

    /**
     *
     * @param objeto
     * @param apenasInclusao
     * @return boolean
     * @throws SQLException
     */
    @Override
    public Boolean set(Agente objeto, boolean apenasInclusao) throws SQLException {
        Agente agente = get("where idagente=" + objeto.getIdagente());
        PreparedStatement stmt;
        if (agente == null) {
            return false;
        }
        boolean existe = agente.getIdagente() == objeto.getIdagente();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update Agente set descricao=?,nomecientifico=?,datacadastro=?,idusuario=?,imagem=? where idagente=?");
            stmt.setString(1, objeto.getDescricao());
            stmt.setString(2, objeto.getNomecientifico());
            stmt.setTimestamp(3, objeto.getDatacadastro());
            stmt.setInt(4, objeto.getIdUsuario());
            stmt.setBinaryStream(5, ImagetoStream(objeto.getImagem()), ImagetoStream(objeto.getImagem()).available());
            stmt.setInt(6, objeto.getIdagente());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into Agente (descricao,nomecientifico,datacadastro,idusuario,imagem) values (?,?,?,?,?)");
            stmt.setString(1, objeto.getDescricao());
            stmt.setString(2, objeto.getNomecientifico());
            stmt.setTimestamp(3, objeto.getDatacadastro());
            stmt.setInt(4, objeto.getIdUsuario());
            stmt.setBinaryStream(5, ImagetoStream(objeto.getImagem()), ImagetoStream(objeto.getImagem()).available());
            stmt.execute();
            stmt.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param objeto
     * @return boolean
     * @throws SQLException
     */
    @Override
    public Boolean remove(Agente objeto) throws SQLException {
        Agente agente = get("where idagente=" + objeto.getIdagente());
        if (agente == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  Agente where idagente=?");
            stmt.setInt(1, objeto.getIdagente());
            stmt.execute();
            stmt.close();
            return true;
        }
    }

    /**
     * Conta registros
     * @return int
     * @throws SQLException
     */
    @Override
    public int count() throws SQLException {
        int numeroRegistros = 0;
        PreparedStatement stmt;
        String sql = "select count(idagente) as numero from Agente ";
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            numeroRegistros = rs.getInt("numero");
        }
        rs.close();
        stmt.close();
        return numeroRegistros;
    }

    /**
     * Metodo responsavel por retornar um ImageIcon com base em no id da amostra
     * @param idAmostra
     * @return
     */
    public ImageIcon getImagemIcon(String idAmostra) {
        ImageIcon ic = null;
        try {
            ic = StreamToImage(getImagemStream(idAmostra));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ic;
    }

     /**
     * Metodo responsavel por retornar o stream de imagem com base em no id da amostra
      * @param idAmostra
      * @return
     * @throws SQLException
     */
    public InputStream getImagemStream(String idAmostra) throws SQLException {
        InputStream imagem = null;
        PreparedStatement stmt;
        stmt = this.getConnection().prepareStatement("select imagem from Amostra where idamostra=?");
        stmt.setString(1, idAmostra);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            imagem = rs.getBinaryStream(1);
        }
        rs.close();
        stmt.close();
        return imagem;
    }

    /**
     * Metodo responsavel por converter um InputStream em ImageIcon
     * @param input
     * @return
     */
    private ImageIcon StreamToImage(InputStream input) {
        ImageIcon imagem = null;
        if (input == null) {
            return imagem;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] rb = new byte[1024];
        int ch = 0;
        try {
            while ((ch = input.read(rb)) != -1) {
                output.write(rb, 0, ch);
            }
            byte[] b = output.toByteArray();
            input.close();
            output.close();
            imagem = new ImageIcon(b);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return imagem;
    }

    /**
     * Metodo responsavel pela conversao de ImageIcom para ByteArrayInputStream
     * @param w
     * @return
     */
    public ByteArrayInputStream ImagetoStream(ImageIcon w) {
        Image x = w.getImage();
        ByteArrayInputStream inStream = null;
        BufferedImage bImage = new BufferedImage(x.getWidth(null), x.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bImage.getGraphics();
        bg.drawImage(x, 0, 0, null);
        bg.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "JPG", out);
            byte[] buf = toByteArray(bImage.getWidth(), bImage.getHeight(), bImage);
            inStream = new ByteArrayInputStream(buf);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inStream;
    }

    /**
     * Metodo reponsavel por retornar um array de baytes
     * @param width
     * @param height
     * @param imageBuff
     * @return
     * @throws java.io.IOException
     */
    private byte[] toByteArray(int width, int height, BufferedImage imageBuff) throws java.io.IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bi.getGraphics().drawImage(imageBuff, 0, 0, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
        param.setQuality(1.0f, false);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(bi);
        return out.toByteArray();
    }
}
