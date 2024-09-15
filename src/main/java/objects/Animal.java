package objects;

public class Animal implements ObjectDB {
    private int id;
    private String type, name, color;
    private int weight, age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal(String color, String name, int weight, String type, int age) {
        this.color = color;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.age = age;
    }

    public Animal(int id, String color, String name, int weight, String type, int age) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Привет! " +
                "Меня зовут " + name
                + ", я " + type
                + ". Мне " + age + " " + correctAgeStr(age)
                + ", я вешу - " + weight
                + " кг, мой цвет - " + color
                + ". id=" + id;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    private String correctAgeStr(int age) {
        if (age == 1) {
            return "год";
        } else if (age >= 2 && age <= 4) {
            return "года";
        } else {
            return "лет";
        }
    }
}
