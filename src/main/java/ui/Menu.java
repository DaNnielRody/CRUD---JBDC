package ui;

import dao.PersonDAO;
import model.Person;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final PersonDAO personDAO;

    public Menu() {
        this.personDAO = new PersonDAO();
    }

    public void consumeData() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("""
                    Bem vindo ao menu.
                    1 - Criar usuário
                    2 - Ler usuários
                    3 - Atualizar usuários
                    4 - Remover usuários
                    5 - Sair do Menu""");

            String option = scanner.nextLine();
            isValid = menuOptions(option);
        }
    }

    public boolean menuOptions(String entry) {
        switch (entry) {
            case "1" -> createPerson();
            case "2" -> personDAO.readClient();
            case "3" -> updatePerson();
            case "4" -> deletePerson();
            case "5" -> {
                personDAO.closeDB();
                return false;
            }
            default -> System.out.println("Entrada inválida");
        }
        return true;
    }

    private void createPerson() {
        System.out.println("Digite o id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o nome:");
        String name = scanner.nextLine();
        System.out.println("Digite o email:");
        String email = scanner.nextLine();

        Person user = new Person(id, name, email);
        personDAO.createPerson(user);
    }

    private void updatePerson() {
        System.out.println("Digite o novo nome:");
        String name = scanner.nextLine();
        System.out.println("Digite o novo email:");
        String email = scanner.nextLine();
        System.out.println("Qual o ID do seu usuário?");
        int id = Integer.parseInt(scanner.nextLine());

        Person user = new Person(id, name, email);
        personDAO.updateClient(user);
    }

    private void deletePerson() {
        System.out.println("Qual o ID do usuário a ser deletado?");
        int id = Integer.parseInt(scanner.nextLine());

        Person user = new Person();
        user.setId(id);
        personDAO.deleteClient(user);
    }
}