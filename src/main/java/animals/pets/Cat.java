package animals.pets;
import objects.Animal;
//import object.Animal;

public class Cat extends Animal{
    // конструктор
    public Cat(String color, String name, int weight, String type, int age) {
        super(color, name, weight, type, age);
    }

    // переопределяем метод
    @Override
    public void say() {
        System.out.println("Мяу");
    }
}
