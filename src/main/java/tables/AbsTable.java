package tables;

import db.IDBConnect;
import db.MySQLConnect;

import java.sql.ResultSet;
import java.util.List;

public abstract class AbsTable implements ITable{
    protected IDBConnect dbConnector = null;
    private String tableName = "";

    public AbsTable(String tableName) {
        dbConnector = new MySQLConnect();
        this.tableName = tableName;
    }

    //CREATE TABLE tableName (column1 INT PRIMARY KEY , column2 VARCHAR(255), column3, ...);
    @Override
    public void create(List<String> columns) {
        delete();
        dbConnector.execute(String.format("CREATE TABLE %s (%s);", tableName, String.join(",", columns)));
    }
    @Override
    public void delete() {
        dbConnector.execute(String.format("drop table if exists %s;",this.tableName));
    }
    public ResultSet selectall() {
        return dbConnector.executeQuery(String.format("SELECT * FROM  %s;",this.tableName));
    }

}