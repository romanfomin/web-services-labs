package lab3.utils;

import lab3.exception.PersonServiceException;
import lab3.exception.PersonServiceFault;

public final class ValidationUtils {

    public static void validateParams(String name, String surname, Integer age,
                                      Integer phone, String gender) throws PersonServiceException {
        if (name == null || name.isEmpty())
            throw new PersonServiceException("Invalid name!", PersonServiceFault.defaultInstance());
        if (surname == null || surname.isEmpty())
            throw new PersonServiceException("Invalid surname!", PersonServiceFault.defaultInstance());
        if (age == null || age <= 0)
            throw new PersonServiceException("Invalid age!", PersonServiceFault.defaultInstance());
        if (phone == null || phone <= 100000)
            throw new PersonServiceException("Invalid phone!", PersonServiceFault.defaultInstance());
        if (gender == null || gender.isEmpty())
            throw new PersonServiceException("Invalid gender!", PersonServiceFault.defaultInstance());
    }

    public static void validateId(Long id) throws PersonServiceException {
        if (id == null || id <= 0)
            throw new PersonServiceException("Invalid id!", PersonServiceFault.defaultInstance());
    }
}
