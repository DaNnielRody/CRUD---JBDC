package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private Connection connection;

    public ConnectionFactory() {
         /*
        Para nos conectarmos, acessamos o drive, informamos o tipo de banco que a api vai acessar e a porta onde ela est� rodando.
        No meu caso, ela roda na porta 3306 do meu localhost e o nome do banco de dados.
        Logo a seguir, vem o nome do usu�rio e a senha.
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "123");
            if(connection != null){
                System.out.println("Database connected");
            }
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
