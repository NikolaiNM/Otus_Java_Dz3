package animals.birds;

import objects.Animal;

public class Duck extends Animal implements Flying {
    // конструктор
    public Duck(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    // реализуем все методы интерфейса
    @Override
    public void fly() {
        System.out.println("Я лечу");
    }

    // переопределяем метод
    @Override
    public void say() {
        System.out.println("Кря");
    }


}
