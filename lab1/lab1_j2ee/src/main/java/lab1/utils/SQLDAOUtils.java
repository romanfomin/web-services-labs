package lab1.utils;

import lab1.model.Person;

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
            String resName = rs.getString("name");
            String resSurname = rs.getString("surname");
            int resAge = rs.getInt("age");
            int resPhone = rs.getInt("phone");
            String resGender = rs.getString("gender");

            Person person = new Person(resName, resSurname, resAge, resPhone, resGender);
            persons.add(person);
        }
        return persons;
    }
}
