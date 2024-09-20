package data;

import objects.Animal;
import animals.pets.Cat;
import animals.pets.Dog;
import animals.birds.Duck;

import java.util.Arrays;
import java.util.List;

public class AnimalFactory {
    public static Animal createAnimal(String type, String name, int age, int weight, String color) {
        switch (type) {
            case "CAT":
                return new Cat(color, name, weight, type, age);
            case "DOG":
                return new Dog(color, name, weight, type, age);
            case "DUCK":
                return new Duck(color, name, weight, type, age);
            default:
                throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        }
    }

    public static final List<String> ANIMAL_TYPES = Arrays.asList("CAT", "DOG", "DUCK");
}