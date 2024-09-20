package tables;

import db.MySQLConnect;
import objects.Animal;

import java.sql.PreparedStatement;
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

    public ArrayList<Animal> read() throws SQLException {
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

    public ArrayList<Animal> read(String animalType) throws SQLException {
        String query = "SELECT * FROM " + NAME + " WHERE type = ?";
        try (PreparedStatement statement = MySQLConnect.getConnection().prepareStatement(query)) {
            statement.setString(1, animalType);
            try (ResultSet resultSet = statement.executeQuery()) {
                return getAnimalsFromResultSet(resultSet);
            }
        }
    }


    public boolean isIdExists(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM animals WHERE id = ?";
        try (PreparedStatement statement = MySQLConnect.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }
    }

    public boolean isTableEmpty() throws SQLException {
        ArrayList<Animal> animals = read();
        return animals == null || animals.isEmpty();
    }


    public void update(Animal animal) {
        this.dbConnector.execute(String.format(
                "UPDATE %s SET type='%s', name='%s', color='%s', age=%d, weight=%d WHERE id=%d;",
                NAME,
                animal.getType(),
                animal.getName(),
                animal.getColor(),
                animal.getAge(),
                animal.getWeight(),
                animal.getId()
        ));
    }

    private ArrayList<Animal> getAnimalsFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            String color = resultSet.getString("color");
            int age = resultSet.getInt("age");
            int weight = resultSet.getInt("weight");

            Animal animal = new Animal(id, color, name, weight, type, age);
            animals.add(animal);
        }
        return animals;
    }
}
