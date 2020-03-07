/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Airi
 */
public class A2LoanSystemConfig {
    public static void main(String[] args){
        try (OutputStream output = new FileOutputStream("loanSystemConfig.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "jdbc:mysql://localhost:3306/LibraryRepo?serverTimezone=UTC");
            prop.setProperty("db.user", "root");
            prop.setProperty("db.password", "root1234");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
