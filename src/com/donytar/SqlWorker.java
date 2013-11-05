package com.donytar;
import java.sql.*;


/**
 * User: donytar
 * Date: 10/10/13
 * Time: 5:12 PM
 */
public class SqlWorker {
    private static final String dbClassName = "com.mysql.jdbc.Driver";

    final Connection dataBase;

    public SqlWorker(String dbLocation, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(dbClassName);
        //DriverManager.registerDriver(new Driver());
        dataBase = DriverManager.getConnection(dbLocation, user, password);

        if (dataBase == null) {
            throw new SQLException("Can't connect to database");
        }
    }



    public void showAll() throws SQLException {
        Statement stmt = dataBase.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM pet");

        //System.out.println("#");
        while (result.next()) {
            System.out.println(result.getRow() + ". " + result.getString("name")
                    + "\t" + result.getString("owner"));
        }
    }

    public void addRow(String name, String owner, String type, String sex) throws Exception {
        Statement stmt = dataBase.createStatement();

        if (haveRow(name, owner)) {
            throw new Exception("This owner already have a pet with such name");
        }

        stmt.executeUpdate(String.format(
                "INSERT INTO pet (name, owner, type, sex) " +
                "VALUES('%s', '%s', '%s', '%s')", name, owner, type, sex));
    }

    public void removeRow(String name, String owner) throws SQLException {
        Statement stmt = dataBase.createStatement();
        stmt.executeUpdate(String.format(
                "DELETE FROM pet WHERE(name = " +
                 "'%s' and owner = '%s')", name, owner));
    }

    public void updateName(String name, String owner, String newName) throws SQLException {
        Statement stmt = dataBase.createStatement();
        if (haveRow(name, owner) && !haveRow(newName, owner)) {
            stmt.executeUpdate(String.format(
                    "update pet set name = '%s' " +
                    "where name = '%s' and owner = '%s'", newName, name, owner));
        }
    }

    public boolean haveRow(String name, String owner) throws SQLException {
        Statement stmt = dataBase.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM pet");

        while (result.next()) {
            if (result.getString("name").equals(name) && result.getString("owner").equals(owner)) {
                return true;
            }
        }
        return false;
    }
}
