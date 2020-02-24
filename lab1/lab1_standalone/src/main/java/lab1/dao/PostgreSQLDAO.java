package lab1.dao;

import lab1.model.Person;
import lab1.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lab1.utils.SQLDAOUtils.getQuoted;
import static lab1.utils.SQLDAOUtils.resultSetToPersonList;

public class PostgreSQLDAO {

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");

            persons = resultSetToPersonList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public List<Person> getPersons(String name, String surname, Integer age, Integer phone, String gender) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from persons p " +
                        "where (" + getQuoted(name) + " is NULL or " + getQuoted(name) + " =p.name) AND " +
                        "(" + getQuoted(surname) + " is NULL or " + getQuoted(surname) + " =p.surname) AND " +
                        "(" + age + " is NULL or " + age + " =p.age) AND " +
                        "(" + phone + " is NULL or " + phone + " =p.phone) AND " +
                        "(" + getQuoted(gender) + " is NULL or " + getQuoted(gender) + " =p.gender)");

            persons = resultSetToPersonList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }


}