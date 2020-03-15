package lab3.ws;

import lab3.exception.PersonServiceException;
import lab3.model.Person;
import lab3.dao.PostgreSQLDAO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

import static lab3.utils.ValidationUtils.validateId;
import static lab3.utils.ValidationUtils.validateParams;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getAllPersons")
    public List<Person> getAllPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAllPersons();
    }

    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons(@WebParam(name = "name") String name,
                                   @WebParam(name = "surname") String surname,
                                   @WebParam(name = "age") Integer age,
                                   @WebParam(name = "phone") Integer phone,
                                   @WebParam(name = "gender") String gender) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getPersons(name, surname, age, phone, gender);
    }

    @WebMethod(operationName = "addPerson")
    public long addPerson(@WebParam(name = "name") String name,
                          @WebParam(name = "surname") String surname,
                          @WebParam(name = "age") Integer age,
                          @WebParam(name = "phone") Integer phone,
                          @WebParam(name = "gender") String gender) throws PersonServiceException {
        validateParams(name, surname, age, phone, gender);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.addPerson(name, surname, age, phone, gender);
    }

    @WebMethod(operationName = "updatePerson")
    public long updatePerson(@WebParam(name = "id") Long id,
                             @WebParam(name = "name") String name,
                             @WebParam(name = "surname") String surname,
                             @WebParam(name = "age") Integer age,
                             @WebParam(name = "phone") Integer phone,
                             @WebParam(name = "gender") String gender) throws PersonServiceException {
        validateId(id);
        validateParams(name, surname, age, phone, gender);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updatePerson(id, name, surname, age, phone, gender);
    }

    @WebMethod(operationName = "deletePerson")
    public long deletePerson(@WebParam(name = "id") Long id) throws PersonServiceException {
        validateId(id);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deletePerson(id);
    }
}