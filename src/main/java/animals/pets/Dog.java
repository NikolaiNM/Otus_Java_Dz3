package animals.pets;

import objects.Animal;

public class Dog extends Animal{
    // конструктор
    public Dog(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    // переопределяем метод
    @Override
    public void say() {
        System.out.println("Гав");
    }
}
