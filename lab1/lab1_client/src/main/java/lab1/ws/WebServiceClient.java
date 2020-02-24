package lab1.ws;

import lab1.ws.generated.Person;
import lab1.ws.generated.PersonService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    private static final String WSDL_URL = "http://localhost:8080/PersonService?wsdl";                          // standalone
//    private static final String WSDL_URL = "http://localhost:8080/lab1_j2ee_war_exploded/PersonService?wsdl"; // j2ee

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL(WSDL_URL);
        PersonService personService = new PersonService(url);

        List<Person> persons = personService.getPersonWebServicePort().getPersons("Владимир", null, null, null, null);
        for (Person person : persons) {
            System.out.println("name: " + person.getName() +
                    ", surname: " + person.getSurname() + ", age: " + person.getAge());
        }
        System.out.println("Total persons: " + persons.size());
    }
}