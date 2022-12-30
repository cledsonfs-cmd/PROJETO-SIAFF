/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Classes.Analise;
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
 *
 * @author cledsonfs
 */
public class AnaliseDao extends ModeloDao<Analise>{

    public AnaliseDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        
    }

    /**
     *
     * @return List<Analise>
     * @throws SQLException
     */
    public List<Analise> getList() throws SQLException {
        return getList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Agente>
     * @throws SQLException
     */
    @Override
    public List<Analise> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from Analise ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Analise> list = new ArrayList<Analise>();
        while (rs.next()) {
            Analise analise = new Analise();
            analise.setIdAnalise(rs.getInt("id"));
            analise.setNome(rs.getString("nome"));
            analise.setData(rs.getTimestamp("data"));
            analise.setImagem(StreamToImage(rs.getBinaryStream("imagem")));
            list.add(analise);
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
    public Analise get(String condicaoUnica) throws SQLException {
        List<Analise> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Analise at = new Analise();
            at.setIdAnalise(-1);
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
    public void associar(Analise objeto, List<Class> classes, String condicao) throws SQLException {
        
    }

    /**
     *
     * @param objeto
     * @param apenasInclusao
     * @return boolean
     * @throws SQLException
     */
    @Override
    public Boolean set(Analise objeto, boolean apenasInclusao) throws SQLException {
        Analise agente = get("where id=" + objeto.getIdAnalise());
        PreparedStatement stmt;
        if (agente == null) {
            return false;
        }
        boolean existe = agente.getIdAnalise() == objeto.getIdAnalise();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update Analise set nome=?,data=?,imagem=? where id=?");
            stmt.setString(1, objeto.getNome());
            stmt.setTimestamp(2, objeto.getData());
            stmt.setBinaryStream(3, ImagetoStream(objeto.getImagem()), ImagetoStream(objeto.getImagem()).available());
            stmt.setInt(4, objeto.getIdAnalise());
            stmt.execute();
            stmt.close();
            return false;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into Analise (id,nome,data,imagem) values (?,?,?,?)");
            stmt.setInt(1, objeto.getIdAnalise());
            stmt.setString(2, objeto.getNome());
            stmt.setTimestamp(3, objeto.getData());
            stmt.setBinaryStream(4, ImagetoStream(objeto.getImagem()), ImagetoStream(objeto.getImagem()).available());
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
    public Boolean remove(Analise objeto) throws SQLException {
        Analise agente = get("where id=" + objeto.getIdAnalise());
        if (agente == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  Analise where id=?");
            stmt.setInt(1, objeto.getIdAnalise());
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
        String sql = "select count(id) as numero from Analise ";
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
    public ImageIcon getImagemIcon(String id) {
        ImageIcon ic = null;
        try {
            ic = StreamToImage(getImagemStream(id));
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
        stmt = this.getConnection().prepareStatement("select imagem from Analise where id=?");
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
