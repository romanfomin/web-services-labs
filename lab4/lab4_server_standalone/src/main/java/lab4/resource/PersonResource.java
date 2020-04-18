package lab4.resource;

import lab4.model.Person;
import lab4.dao.PostgreSQLDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
                                   @QueryParam("surname") String surname,
                                   @QueryParam("age") Integer age,
                                   @QueryParam("phone") Integer phone,
                                   @QueryParam("gender") String gender) {
        List<Person> persons = new PostgreSQLDAO().getPersons(name, surname, age, phone, gender);
        return persons;
    }
}