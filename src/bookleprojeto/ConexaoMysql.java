package bookleprojeto;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoMysql {

    public ConexaoMysql() {

    }

    public Connection conexao;
    public Statement statement;
    public ResultSet resultset;

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
            System.err.println("Falha ao conectar ao banco! Erro: " + erro);
        }

    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException erroSQL) {
            System.err.println("Erro Classe Conexão SQL " + erroSQL);
        }
    }

    public void executaSQL(String Query) {
        try {
            statement = conexao.createStatement();
            resultset = statement.executeQuery(Query);
        } catch (SQLException erroSQL) {
            System.err.println("Erro Classe Execura SQL " + erroSQL);
        }
    }
    
     public void prepareStatement(String Query) {
        try {
            PreparedStatement preparestatement = conexao.prepareStatement(Query);
        } catch (SQLException erroSQL) {
            System.err.println("Erro Classe PrepareStatement " + erroSQL);
        }
    }
}
