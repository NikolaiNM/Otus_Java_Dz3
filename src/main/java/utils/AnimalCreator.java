package utils;

import data.AnimalFactory;
import objects.Animal;

import java.util.Scanner;

public class AnimalCreator {

    private Scanner scanner;
    private InputIntValidator validator;

    public AnimalCreator(Scanner scanner, InputIntValidator validator) {
        this.scanner = scanner;
        this.validator = validator;
    }

    public Animal createAnimalWithData() {
        String type;
        boolean rightType = false;
        do {
            System.out.printf("Какое животное вы хотите добавить %s: ", String.join(" / ", AnimalFactory.ANIMAL_TYPES));
            type = scanner.next().trim().toUpperCase();
            if (AnimalFactory.ANIMAL_TYPES.contains(type)) {
                rightType = true;
            } else {
                System.out.println("Неизвестное животное, попробуйте еще раз");
            }
        } while (!rightType);

        System.out.println("Как зовут животное?");
        String name = scanner.next().trim();

        int age = validator.getValidInput("Сколько ему лет?", "Возраст должен", 1, 20);

        int weight = validator.getValidInput("Сколько оно весит?", "Вес должен", 1, 100);

        System.out.println("Какого цвета животное?");
        String color = scanner.nextLine().trim();

        return AnimalFactory.createAnimal(type, name, age, weight, color);
    }
}