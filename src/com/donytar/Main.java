package com.donytar;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        /*
        try {
            SqlWorker database = new SqlWorker("jdbc:mysql://localhost/my_db", "root", "Nathyrra");
            //database.addRow("Bobic", "Gringo Dolbig", "dog", "m");
            //database.removeRow("Rnobic", "Bilbo Sumcin");
            //database.updateName("Goshka", "Stepan Razin", "Gosha");
            //database.showAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        try {
            Runtime.getRuntime().exec("mysqldump -u root -pNathyrra my_db -r //home/donytar/my_db.sql");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
