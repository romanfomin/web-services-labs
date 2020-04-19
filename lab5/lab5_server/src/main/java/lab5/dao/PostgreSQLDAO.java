package lab5.dao;

import lab5.model.Person;
import lab5.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static lab5.utils.SQLDAOUtils.getQuoted;
import static lab5.utils.SQLDAOUtils.resultSetToPersonList;

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

    public Long addPerson(String name, String surname, Integer age, Integer phone, String gender) {
        Long id = null;
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "INSERT INTO persons (name, surname, age, phone, gender) " +
                            "VALUES (" + getQuoted(name) + ", " + getQuoted(surname) + ", " + age + ", " +
                            phone + ", " + getQuoted(gender) + ") RETURNING id");
            if (rs.next()) {
                id = rs.getLong("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public Long updatePerson(Long id, String name, String surname, Integer age, Integer phone, String gender) {
        Long returnId = null;
        try (Connection connection = ConnectionUtils.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "UPDATE persons SET name = " + getQuoted(name) + ", surname = " + getQuoted(surname) +
                            ", age = " + age + ", phone = " + phone + ", gender = " + getQuoted(gender) +
                            " WHERE id = " + id + " RETURNING id");
            if (rs.next()) {
                returnId = rs.getLong("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnId;
    }

    public Long deletePerson(Long id) {
        Long returnId = null;
        try (Connection connection = ConnectionUtils.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM persons WHERE id = " + id + " RETURNING id");
            if (rs.next()) {
                returnId = rs.getLong("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnId;
    }
}