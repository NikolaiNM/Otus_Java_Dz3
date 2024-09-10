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
            animal.add(animals);

        }
        return animal;
    }
}
