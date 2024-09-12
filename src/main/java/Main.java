import db.MySQLConnect;
import objects.Animal;
import data.AnimalFactory;
import menu.Command;
import tables.AnimalTable;
import utils.AnimalCreator;
import utils.InputIntValidator;

//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // создаем сканер
    private static Scanner scanner = new Scanner(System.in);
    private static InputIntValidator validator = new InputIntValidator(scanner);
    private static AnimalCreator animalCreator = new AnimalCreator(scanner, validator); // Создаем экземпляр нового класса

    public static void main(String[] args) throws SQLException {



        System.out.print("Привет! Вводи команду Add / List / Exit : ");       // Приветствие

        AnimalTable animalTable = new AnimalTable();

        // создание таблицы
        List<String> columnsAnimalTable = new ArrayList<>();
        columnsAnimalTable.add("id INT AUTO_INCREMENT PRIMARY KEY");
        columnsAnimalTable.add("type VARCHAR(20)");
        columnsAnimalTable.add("name VARCHAR(20)");
        columnsAnimalTable.add("color VARCHAR(20)");
        columnsAnimalTable.add("age INT");
        columnsAnimalTable.add("weight INT");

        animalTable.create(columnsAnimalTable);

        // цикл пока не выберем exit
        while (true) {

            String input = scanner.nextLine();                  // Запись в input из терминала
            Command command = Command.fromString(input);        // Объявление переменной command для menu.Command(enum)

            // Проверка если ввели пустую команду
            if (command == null) {                               // Проверяет чтобы в команде не был нулл
                System.out.print("Неверная команда, попробуйте еще : ");
                continue;                                       // необходимо для продолжения если в command null
            }

            // переключатель проверка содержимого переменной command
            switch (command) {
                case ADD:                                   //если ввели ADD
                    try {
                        Animal newAnimal = animalCreator.createAnimalWithData();
                        animalTable.write(newAnimal);
                        newAnimal.say();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.print("Вводи команду Add / List / Exit : ");
                    break;

                case LIST:
                    ArrayList<Animal> an = animalTable.read();
                    if (an != null && !an.isEmpty()) {
                        for (Animal animal : an) {
                            System.out.println(animal);
                        }
                    } else {
                        System.out.println("Список пуст");
                    }
                    System.out.print("Вводи команду Add / List / Exit : ");
                    break;

                case UPDATE:
                    System.out.println("Введите id животного: ");
                    int id =scanner.nextInt();

                    Animal newAnimal = animalCreator.createAnimalWithData();
                    newAnimal.setId(id);

                    animalTable.update(newAnimal);

                    break;

                case EXIT:                                  //если ввели EXIT
                    System.out.println("Выход");
                    // закрыть сканер
                    scanner.close();
                    MySQLConnect.close();
                    System.exit(0);                 // правильный выход
                default:                                  // любое другое значение
                    System.out.println("Не верная команда");
            }
        }
    }
}
