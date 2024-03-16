package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Person;

public class PersonDAO {

    private final ConnectionFactory connection;

    private PreparedStatement statement;

    public PersonDAO(){
        this.connection = new ConnectionFactory();
    }

    public void createPerson(Person user){

        String query = "INSERT INTO Person (id,name,email) VALUES (?, ?, ?)";
        try{
            statement = connection.getConnection().prepareStatement(query);

            //adicionando os dados
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());

            //para executar a inserção de usuário, podemos apenas utilizar o execute update
            statement.executeUpdate();

            System.out.println("Tabelas inseridas com sucesso.");
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void readClient(){

        List<Person> user = new ArrayList<>();
        String query = "SELECT id, name, email FROM Person";
        try{
            statement = connection.getConnection().prepareStatement(query);
            // adicionando o ResultSet que serve como um ponteiro verificador de linhas, preparado para executar a consulta requisitada query dentro do statement.
            ResultSet lines = statement.executeQuery();
            //se há próxima linha, ele vai setando os valores e adicionando na lista de usuários para ser mostrada ao final do método.
            while(lines.next()){
                Person people = new Person();
                people.setId(lines.getInt("id"));
                people.setName(lines.getString("name"));
                people.setEmail(lines.getString("email"));
                user.add(people);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        for (Person eachUser: user){
            System.out.println(eachUser);
        }
    }

    public void updateClient(Person user){
        String query = "UPDATE Person SET name=?, email=? WHERE id=?";

        try{
            statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());

            statement.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void deleteClient(Person user){
        String query = "DELETE FROM Person WHERE id = ?";

        try{
            statement = connection.getConnection().prepareStatement(query);

            statement.setInt(1, user.getId());

            statement.executeUpdate();
            System.out.println("Usuário deletado com sucesso.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void closeDB(){
        connection.closeConnection();
        try{
            statement.close();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
