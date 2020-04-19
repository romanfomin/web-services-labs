package lab5.resource;

import lab5.model.Person;
import lab5.dao.PostgreSQLDAO;

import javax.ws.rs.*;
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
        return new PostgreSQLDAO().getPersons(name, surname, age, phone, gender);
    }

    @POST
    public Long addPerson(Person person){
        return new PostgreSQLDAO().addPerson(person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
    }

    @PUT
    public Long updatePerson(Long id, Person person){
        return new PostgreSQLDAO().updatePerson(id, person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
    }

    @DELETE
    public Long deletePerson(Long id){
        return new PostgreSQLDAO().deletePerson(id);
    }
}