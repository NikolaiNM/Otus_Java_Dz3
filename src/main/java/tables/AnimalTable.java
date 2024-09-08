package tables;

import db.MySQLConnect;
import objects.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalTable extends AbsTable {
    private static final String NAME = "animals";

    public AnimalTable() {
        super(NAME);
    }

    public void write(Animal animal) {
        this.dbConnector.execute(String.format("INSERT INTO %s (id,color,name,weight,type,age) " +
                        "VALUES('%s','%s','%s','%s','%s','%s')",
                NAME, animal.getId(), animal.getColor(), animal.getName(), animal.getWeight(), animal.getType(), animal.getAge()));
    }

    public void print(ResultSet rs) throws SQLException {
        // Вывод заголовка на экран
        System.out.printf("%-5s %-10s %-20s %-10s %-5s %-10s%n", "ID", "Type", "Name", "Color", "Age", "Weight");
        System.out.println("---------------------------------------------------------------");

        // Вывод данных по запросу
        while (rs.next()) {
            System.out.printf("%-5d %-10s %-20s %-10s %-5d %-10s%n",
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getInt("age"),
                    rs.getString("weight"));
        }
    }

    public ArrayList<Animal> read () throws SQLException {
        ArrayList<Animal> animal = new ArrayList<>();
        ResultSet resultSet;

        dbConnector = new MySQLConnect();
        resultSet = this.dbConnector.executeQuery(String.format("SELECT * FROM %s;", NAME));
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String color = resultSet.getString("color");
            int age = resultSet.getInt("age");
            int weight = resultSet.getInt("weight");

            Animal animals = new Animal(id, color, name, weight, type, age);
            //animals.getAge();
            animal.add(animals);

        }
        return animal;
    }
}
