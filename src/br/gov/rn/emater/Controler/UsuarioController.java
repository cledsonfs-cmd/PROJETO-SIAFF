/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.rn.emater.Controler;

import br.gov.rn.emater.Classes.Usuario;
import br.gov.rn.emater.Facade.UsuarioFacade;
import java.util.List;

/**
 * Classe UsuarioController
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class UsuarioController extends ModeloController<Usuario> {

    /**
     * <html>
     * <tr><td>PERMISSOES</td></tr>
     * <tr><td>CADASTRAR_AGENTES_CAUSADORES</td></tr>
     * <tr><td>CADASTRAR_CARACTERISTICAS</td></tr>
     * <tr><td>CADASTRAR_DOENCAS</td></tr>
     * <tr><td>CADASTRAR_FAMILIA</td></tr>
     * <tr><td>CADASTRAR_GENERO</td></tr>
     * <tr><td>CADASTRAR_PARTES_DA_PLANTA</td></tr>
     * <tr><td>CADASTRAR_PLANTAS</td></tr>
     * <tr><td>CADASTRAR_SINTOMAS_DAS_DOENCAS</td></tr>
     * <tr><td>CADASTRAR_TRATAMENTOS</td></tr>
     * <tr><td>CADASTRAR_USUARIOS</td></tr>
     * </html>
     */
    public static enum Permissoes {
        CADASTRAR_AGENTES_CAUSADORES,
        CADASTRAR_CARACTERISTICAS,
        CADASTRAR_DOENCAS,
        CADASTRAR_FAMILIA,
        CADASTRAR_GENERO,
        CADASTRAR_PARTES_DA_PLANTA,
        CADASTRAR_PLANTAS,
        CADASTRAR_SINTOMAS_DAS_DOENCAS,
        CADASTRAR_TRATAMENTOS,
        CADASTRAR_USUARIOS
    }

    /**
     * Metodo responsavel pelo cadastro de usuarios
     * @param alteracao
     * @return String
     */
    public boolean salvar(boolean alteracao) {
        if (usuario == null) {
            this.setMenssagemRetorno("Objeto usuário nulo!");
            return false;
        } else if (usuario.getLogin() == null || usuario.getLogin().trim().length() == 0) {
            this.setMenssagemRetorno("Login inválido!");
            return false;
        } else if (usuario.getSenha() == null || usuario.getSenha().length() == 0) {
            this.setMenssagemRetorno("Senha inválida!");
            return false;
        } else if (usuario.getSenha().length() <= 4) {
            this.setMenssagemRetorno("A senha deve ter mais de 4 caracteres!");
            return false;
        } else if (usuarioExiste(usuario) && !alteracao) {
            this.setMenssagemRetorno("Usuário já existe");
            return false;
        } else {
            boolean r = new UsuarioFacade().createUpdate(usuario);
            if (r) {
                this.setMenssagemRetorno("Usuário salvo com sucesso");
                return true;
            } else {
                this.setMenssagemRetorno("Ocorreu um error ao persistir o usuário!");
                return false;
            }
        }
    }

    /**
     * Metodo responsável por testar se o usuario existe
     * @param usuario
     * @return
     */
    public boolean usuarioExiste(Usuario usuario) {
        List<Usuario> usuarios = new UsuarioFacade().findWhere(" where login='" + usuario.getLogin() + "' ");
        if (usuarios.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cria um novo objeto
     * @return boolean
     */
    @Override
    public boolean novo() {
        usuario = new Usuario();
        return true;
    }

    /**
     * Retorna um objeto
     * @return Usuario
     */
    @Override
    public Usuario get() {
        if (usuario == null) {
            novo();
        }
        return usuario;
    }

    /**
     * Localiza o usuario pelo login
     * @param login
     * @return Usuario
     */
    public Usuario getPorLogin(String login) {
        return new UsuarioFacade().findWhere(" where login='" + login + "'").get(0);
    }

    /**
     * Localiza o usuario pelo id
     * @param id
     * @return Usuario
     */
    public Usuario getPorId(int id) {
        return new UsuarioFacade().findWhere(" where idusuario=" + id).get(0);
    }

    /**
     * Seta um objeto
     * @param objeto
     */
    @Override
    public void set(Usuario objeto) {
        usuario = objeto;
    }

    /**
     * Exclui um objeto implementando as regras de negocio
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean excluir() throws Exception {
        if (usuario == null) {
            this.setMenssagemRetorno("Objeto usuário nulo!");
            return false;
        } else {
            if (possuiVinculo()) {
                this.setMenssagemRetorno("O usuário nao pode ser excluido por possuir vinculo em algum cadastro!");
                return false;
            } else {
                boolean r = new UsuarioFacade().remove(usuario);
                if (r) {
                    this.setMenssagemRetorno("Usuário excluido com sucesso!");
                    return true;
                } else {
                    this.setMenssagemRetorno("Houve um erro ao excluir o usuário!!");
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
        this.setMenssagemRetorno("Utilize o metodo salvar com a assinatura (boolean)!");
        return false;
    }

    /**
     * Retorna uma lista de objetos cadastrados
     * @return List<Usuario>
     * @throws Exception
     */
    @Override
    public List<Usuario> getTodos() throws Exception {
        return new UsuarioFacade().findAll();
    }

    /**
     * Metodo responsável por validar o usuário 
     * @return String
     */
    public boolean validaUsuario() {
        if (usuario == null) {
            this.setMenssagemRetorno("Dados do usuário não preenchidos!");
            return false;
        } else {
            return validaUsuario(usuario.getLogin(), usuario.getSenha());
        }
    }

    /**
     * Metodo responsável por validar o usuário com parametros
     * @param login
     * @param senha
     * @return String
     */
    public boolean validaUsuario(String login, String senha) {
        if (usuario == null) {
            List<Usuario> usuarios = new UsuarioFacade().findWhere(" where login='" + login + "'");
            if (usuarios.size() == 0) {
                this.setMenssagemRetorno("Usuario inválido");
                return false;
            } else {
                if (usuarios.get(0).getSenha().trim().equals(senha)) {
                    this.setMenssagemRetorno("Usuario e senha ok");
                    return true;
                } else {
                    this.setMenssagemRetorno("Senha inválida");
                    return false;
                }
            }
        } else {
            if (usuario.getLogin().equals(login)) {
                if (usuario.getSenha().equals(senha)) {
                    this.setMenssagemRetorno("Usuario e senha ok");
                    return true;
                } else {
                    this.setMenssagemRetorno("Senha inválida");
                    return false;
                }
            } else {
                this.setMenssagemRetorno("Usuario inválido");
                return false;
            }
        }
    }

    /**
     * Testa a permissao do usuario
     * @param opc
     * @return boolean
     */
    public boolean acesso(Permissoes opc) {
        if (usuario.isAdministrador()) {
            if (opc.equals(Permissoes.CADASTRAR_USUARIOS)) {
                return true;
            } else {
                return false;
            }
        } else if (usuario.isEspecialista()) {
            if (!opc.equals(Permissoes.CADASTRAR_USUARIOS)) {
                return true;
            } else {
                return false;
            }
        } else if (usuario.isOperador()) {
            if (!opc.equals(Permissoes.CADASTRAR_USUARIOS)) {
                return true;
            } else {
                return false;
            }
        } else if (usuario.isUsuario()) {
            this.setMenssagemRetorno("Permissões para o perfil ainda não definidas!");
            return false;
        }
        return false;
    }

    /**
     * Testa se o objeto possui algum vinculo com outro
     * @return boolean
     */
    private boolean possuiVinculo() {
        if (usuario == null) {
            this.setMenssagemRetorno("Objeto usuário nulo!");
            return false;
        } else {
            List<String> tabelas = new UsuarioFacade().getTabelasOcorrenciaUsuario(usuario);
            if (tabelas.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
