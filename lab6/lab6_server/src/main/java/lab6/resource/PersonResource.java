package lab6.resource;

import lab6.exception.PersonServiceException;
import lab6.model.Person;
import lab6.dao.PostgreSQLDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static lab6.utils.ValidationUtils.validateId;
import static lab6.utils.ValidationUtils.validateParams;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    @GET
    public List<Person> getPersons(@QueryParam("name") String name,
                                   @QueryParam("surname") String surname,
                                   @QueryParam("age") Integer age,
                                   @QueryParam("phone") Integer phone,
                                   @QueryParam("gender") String gender) throws PersonServiceException {
        validateParams(name, surname, age, phone, gender);
        return new PostgreSQLDAO().getPersons(name, surname, age, phone, gender);
    }

    @POST
    public Long addPerson(Person person) throws PersonServiceException {
        validateParams(person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
        return new PostgreSQLDAO().addPerson(person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
    }

    @PUT
    public Long updatePerson(Long id, Person person) throws PersonServiceException {
        validateId(id);
        validateParams(person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
        return new PostgreSQLDAO().updatePerson(id, person.getName(), person.getSurname(),
                person.getAge(), person.getPhone(), person.getGender());
    }

    @DELETE
    public Long deletePerson(Long id) throws PersonServiceException {
        validateId(id);
        return new PostgreSQLDAO().deletePerson(id);
    }
}