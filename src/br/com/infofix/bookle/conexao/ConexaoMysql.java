/**Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package br.com.infofix.bookle.conexao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/** Classe conexaoMysql compoe métodos de conexão com o banco MySql.
 * @author Kelvin Santiago
 */
public class ConexaoMysql {
    
    /**
     * Construtor não inicializa nada
     */
    public ConexaoMysql() {

    }
    
    // Instanciando variáveis de conexao
    public Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    public PreparedStatement preparestatement;
    
    /**
     * Método para abrir conexão com o banco de dados<br>
     * Com os dados:<br>
     * DriverName: com.mysql.jdbc.Driver<br>
     * URL: jdbc:mysql://localhost/bookle.
     */
    public void abrirConexao() {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String url = "jdbc:mysql://localhost/bookle";
            String username = "root";
            String password = "root";

            conexao = DriverManager.getConnection(url, username, password);
            statement = conexao.createStatement();

        } catch (Exception erro) {
            System.err.println("Error Classe: ConexaoMysql - Método: abrirConexao Exceção:  " + erro);
        }

    }

     /**
     * Método para fechar conexão com o banco de dados.
     */
    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException erroSQL) {
            System.err.println("Error Classe: ConexaoMysql - Método: fecharConexao - Exceção: " + erroSQL);
        }
    }

     /**
     * Método para executar uma consulta SQL com o banco de dados, informando 
     * como argumento uma string.
     * @param Query String - Query SQL, ex: SELECT * FROM NOMETABELA
     */
    public void executaSQL(String Query) {
        try {
            statement = conexao.createStatement();
            resultset = statement.executeQuery(Query);
        } catch (SQLException erroSQL) {
            System.err.println("Error Classe: ConexaoMysql - Método: executaSQL - Exceção: " + erroSQL);
        }
    }
    
     /**
     * Método para preparar uma Statement com o banco de dados, informando 
     * como argumento uma string.
     * @param Query String - Query SQL, ex: SELECT * FROM NOMETABELA
     */
    public void prepareStatement(String Query) {
        try {
          preparestatement = conexao.prepareStatement(Query);
        } catch (SQLException erroSQL) {
            System.err.println("Error Classe: ConexaoMysql - Método: prepareStatement - Exceção: " + erroSQL);
        }
    }

     /**
     * Método para criar uma Statement com o banco de dados.
     */
    public void createStatement() {
        try {
            statement = conexao.createStatement();
        } catch (SQLException erroSQL) {
            System.err.println("Error Classe: ConexaoMysql - Método: createStatemente - Exceção: " + erroSQL);
        }

    }

}
