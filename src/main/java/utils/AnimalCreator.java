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
            System.out.print("Какое животное вы хотите добавить cat / dog / duck : ");
            type = scanner.nextLine().trim().toUpperCase();
            if ("CAT".equals(type) || "DOG".equals(type) || "DUCK".equals(type)) {
                rightType = true;
            } else {
                System.out.println("Неизвестное животное, попробуйте еще раз");
            }
        } while (!rightType);

        System.out.println("Как зовут животное?");
        String name = scanner.nextLine().trim();

        int age = validator.getValidInput("Сколько ему лет?", "Возраст должен", 1, 20);

        int weight = validator.getValidInput("Сколько оно весит?", "Вес должен", 1, 100);

        System.out.println("Какого цвета животное?");
        String color = scanner.nextLine().trim();

        return AnimalFactory.createAnimal(type, name, age, weight, color);
    }
}