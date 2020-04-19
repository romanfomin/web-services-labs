package lab6.utils;


import lab6.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SQLDAOUtils {

    public static String getQuoted(String o) {
        return o == null ? o : ("'" + o + "'");
    }

    public static List<Person> resultSetToPersonList(ResultSet rs) throws SQLException {
        List<Person> persons = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String resName = rs.getString("name");
            String resSurname = rs.getString("surname");
            int resAge = rs.getInt("age");
            int resPhone = rs.getInt("phone");
            String resGender = rs.getString("gender");

            Person person = new Person(id, resName, resSurname, resAge, resPhone, resGender);
            persons.add(person);
        }
        return persons;
    }
}
