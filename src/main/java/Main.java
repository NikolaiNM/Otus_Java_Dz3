import db.MySQLConnect;
import objects.Animal;
import data.AnimalFactory;
import menu.Command;
import tables.AnimalTable;
import utils.AnimalCreator;
import utils.InputIntValidator;
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
        // создание таблицы
        AnimalTable animalTable = new AnimalTable();

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
            // Вывод меню
            List<String> commandNames = new ArrayList<>();
            for (Command commandData: Command.values()) {
                commandNames.add(commandData.name());
            }
            System.out.printf("Вводи команду %s: ", String.join(" / ", commandNames));

            String input = scanner.nextLine();                  // Запись в input из терминала
            Command command = Command.fromString(input);        // Объявление переменной command для menu.Command(enum)

            // Проверка если ввели пустую команду
            if (command == null) {                               // Проверяет чтобы в команде не был нулл
                System.out.println("Неверная команда, попробуйте еще : ");
                continue;                                       // необходимо для продолжения если в command null
            }

            // переключатель проверка содержимого переменной command
            switch (command) {
                case ADD:                                   //если ввели ADD
                    try {
                        Animal newAnimal = animalCreator.createAnimalWithData("Какое животное вы хотите добавить");
                        animalTable.write(newAnimal);
                        newAnimal.say();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case LIST:
                    if (animalTable.isTableEmpty()) {
                        System.out.println("Список пуст. Добавьте животное");
                    } else {
                        System.out.println("Выберете вариант вывода списка:\n1 - Весь список\n2 - Список по типам животных");
                        String filter = scanner.nextLine();

                        switch (filter) {
                            case "1":
                                ArrayList<Animal> animals = animalTable.read();
                                for (Animal animal : animals) {
                                    System.out.println(animal);
                                }
                                break;
                            case "2":
                                System.out.printf("Введите тип животного которого хотите вывести %s: ", String.join(" / ", AnimalFactory.ANIMAL_TYPES));
                                String animalType = scanner.nextLine();

                                ArrayList<Animal> animalsByType = animalTable.read(animalType);
                                if (animalsByType.isEmpty()) {
                                    System.out.println("Животные заданного типа не найдены");
                                } else {
                                    for (Animal animal : animalsByType) {
                                        System.out.println(animal);
                                    }
                                }
                                break;
                            default:
                                System.out.println("Неверный вариант фильтра");
                                break;
                        }
                    }
                    break;

                case UPDATE:
                    if (animalTable.isTableEmpty()) {
                        System.out.println("Список пуст. Добавьте животное");
                        break;
                    }

                    boolean isIdExists = false;

                    while(!isIdExists) {
                        System.out.print("Введите id животного (только цифры): ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Введите id животного (только цифры): ");
                            scanner.next();
                        }
                        int id = scanner.nextInt();

                        if (!animalTable.isIdExists(id)) {
                            System.out.println("Животное с id " + id + " не найдено, попробуйте другой id: ");
                        } else {

                            Animal newAnimal = animalCreator.createAnimalWithData("На какое животное вы хотите изменить");
                            newAnimal.setId(id);

                            animalTable.update(newAnimal);
                            newAnimal.say();

                            isIdExists = true;
                        }
                    }
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
