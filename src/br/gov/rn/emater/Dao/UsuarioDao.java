/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Dao;

import br.gov.rn.emater.Apoio.PoolConexao;
import br.gov.rn.emater.Classes.Amostra;
import br.gov.rn.emater.Classes.Doenca;
import br.gov.rn.emater.Classes.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe UsuarioDao
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class UsuarioDao extends ModeloDao<Usuario> {

    public UsuarioDao() {
        this.abrirConexao();
    }

    /**
     * Inclui associacoes
     */
    @Override
    public void incluirAssociacoes() {
        this.associacoes = new ArrayList<Class>();
        this.associacoes.add(Amostra.class);
        this.associacoes.add(Doenca.class);
    }

    /**
     *
     * @return List<Usuario>
     * @throws SQLException
     */
    public List<Usuario> getList() throws SQLException {
        return addList(null);
    }

    /**
     *
     * @param condicao
     * @return List<Usuario>
     * @throws SQLException
     */
    @Override
    public List<Usuario> getList(String condicao) throws SQLException {
        PreparedStatement stmt;
        String sql = "select * from usuario ";
        if (condicao != null) {
            sql += condicao;
        }
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Usuario> list = new ArrayList<Usuario>();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idusuario"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setAdministrador(rs.getBoolean("administrador"));
            usuario.setOperador(rs.getBoolean("operador"));
            usuario.setUsuario(rs.getBoolean("usuario"));
            usuario.setEspecialista(rs.getBoolean("especialista"));
            usuario.setDatacadastro(rs.getTimestamp("datacadastro"));
            list.add(usuario);
        }
        rs.close();
        stmt.close();
        return list;
    }

    /**
     *
     * @param condicaoUnica
     * @return Usuario
     * @throws SQLException
     */
    @Override
    public Usuario get(String condicaoUnica) throws SQLException {
        List<Usuario> list = getList(condicaoUnica);
        if (list.size() > 1) {
            System.out.println("Na CONDICAO UNICA, mais de um registro encontrado!");
            return null;
        } else if (list.size() == 0) {
            Usuario ut = new Usuario();
            ut.setIdUsuario(-1);
            return ut;
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
    public void associar(Usuario objeto, List<Class> classes, String condicao) throws SQLException {
        for (Class classe : classes) {
            if (classe.equals(Amostra.class)) {
                objeto.setAmostras(PoolConexao.getAmostraDao().getList(" where idusuario=" + objeto.getIdUsuario()));
            } else if (classe.equals(Doenca.class)) {
                objeto.setDoencas(PoolConexao.getDoencaDao().getList(" where idusuario=" + objeto.getIdUsuario()));
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
    public Boolean set(Usuario objeto, boolean apenasInclusao) throws SQLException {
        Usuario usuario = get("where idusuario=" + objeto.getIdUsuario());
        PreparedStatement stmt;
        if (usuario == null) {
            return false;
        }
        boolean existe = usuario.getIdUsuario() == objeto.getIdUsuario();
        if (existe && (!apenasInclusao)) {
            stmt = this.getConnection().prepareStatement("update usuario set login=?,senha=?,administrador=?,operador=?,usuario=?,especialista=?,datacadastro=? where idusuario=?");
            stmt.setString(1, objeto.getLogin());
            stmt.setString(2, objeto.getSenha());
            stmt.setBoolean(3, objeto.isAdministrador());
            stmt.setBoolean(4, objeto.isOperador());
            stmt.setBoolean(5, objeto.isUsuario());
            stmt.setBoolean(6, objeto.isEspecialista());
            stmt.setTimestamp(7, objeto.getDatacadastro());
            stmt.setInt(8, objeto.getIdUsuario());
            stmt.execute();
            stmt.close();
            return true;
        } else if (!existe) {
            stmt = this.getConnection().prepareStatement("insert into usuario (login,senha,administrador,operador,usuario,especialista,datacadastro) values (?,?,?,?,?,?,?)");
            stmt.setString(1, objeto.getLogin());
            stmt.setString(2, objeto.getSenha());
            stmt.setBoolean(3, objeto.isAdministrador());
            stmt.setBoolean(4, objeto.isOperador());
            stmt.setBoolean(5, objeto.isUsuario());
            stmt.setBoolean(6, objeto.isEspecialista());
            stmt.setTimestamp(7, objeto.getDatacadastro());
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
    public Boolean remove(Usuario objeto) throws SQLException {
        Usuario usuario = get("where idusuario=" + objeto.getIdUsuario());
        if (usuario == null) {
            return false;
        } else {
            PreparedStatement stmt = this.getConnection().prepareStatement("delete from  Usuario where idusuario=?");
            stmt.setInt(1, objeto.getIdUsuario());
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
        String sql = "select count(idusuario) as numero from Usuario ";
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
     *
     * @param usuario
     * @return List<String>
     * @throws SQLException
     */
    public List<String> getOcorrenciasDeUsuarioEmTabelas(Usuario usuario) throws SQLException {
        PreparedStatement stmt;
        String sql = "select idusuario, 'Agente' as tabela from agente where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Amostra' as tabela from amostra where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Caracteristica' as tabela from caracteristica where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Doenca' as tabela from doenca where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Familia' as tabela from familia where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Genero' as tabela from genero where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Parte' as tabela from parte where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Planta' as tabela from planta where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Sintoma' as tabela from sintoma where idusuario="+usuario.getIdUsuario()+" "
                + "union "
                + "select idusuario, 'Tratamento' as tabela from tratamento where idusuario="+usuario.getIdUsuario()+" ";
        stmt = this.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            String linha = rs.getString("tabela");
            list.add(linha);
        }
        rs.close();
        stmt.close();
        return list;
    }
}
