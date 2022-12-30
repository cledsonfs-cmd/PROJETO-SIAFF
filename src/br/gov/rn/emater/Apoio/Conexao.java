package br.gov.rn.emater.Apoio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * Classe responsável por fazer a conexão com o banco de dados
 * @author cledsonfs,ururai
 * @version 1.0
 */
public class Conexao {

    private String path = "config/";
    private Properties properties;
    private String URL = "";
    private String usuario = "";
    private String senha = "";
    
    /**
     *
     */
    public Conexao() {
    }

    public Properties getProperties() {
        Properties config = new Properties();
        try {
            //System.out.println("passe 2.1.1.1.1 -> "+path+tipoClasse.getSimpleName()+".properties");
            File ff = new File(path + "conexao.properties");
            //System.out.println("passe 2.1.1.1.2 -> "+ff.getAbsoluteFile());
            FileInputStream ffis = new FileInputStream(ff.getAbsoluteFile());
            //System.out.println("passe 2.1.1.1.3");
            config.load(ffis);
            //System.out.println("passe 2.1.1.1.4");
            return config;
        } catch (FileNotFoundException ex) {
            //ex.printStackTrace();
            System.err.println("*** ERRO: Nao localizou o arquivo " + path + "conexao.properties");
            File x = new File(".");
            System.err.println("*** AbsolutePath: " + x.getAbsolutePath());
            System.err.println("*** AbsoluteFile: " + x.getAbsoluteFile());
            System.err.println("*** Name: " + x.getName());
            System.err.println("*** Parent: " + x.getParent());
            String[] aa = x.list();
            for (int k = 0; k < aa.length; k++) {
                System.err.println(k + ": " + aa[k]);
            }
            return null;
        } catch (IOException ex) {
            //ex.printStackTrace();
            return null;
        }
    }
    /**
     * Metodo responsavel por retornar uma conexao
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        properties = getProperties();
        URL = properties.getProperty("URL");
        usuario = properties.getProperty("usuario");
        senha = properties.getProperty("senha");
        Class.forName("org.postgresql.Driver");
        Connection cc = DriverManager.getConnection(URL, usuario, senha);
        URL = cc.getMetaData().getURL();
        return cc;
    }

    /**
     * Metodo responsável por retornar a url de conexão
     * @return String
     */
    public String getURL() {
        return URL;
    }
}
