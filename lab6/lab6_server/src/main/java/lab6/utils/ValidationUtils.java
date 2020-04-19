package lab6.utils;


import lab6.exception.PersonServiceException;

public final class ValidationUtils {

    public static void validateParams(String name, String surname, Integer age,
                                      Integer phone, String gender) throws PersonServiceException {
        if (name == null || name.isEmpty())
            throw new PersonServiceException("Invalid name!");
        if (surname == null || surname.isEmpty())
            throw new PersonServiceException("Invalid surname!");
        if (age == null || age <= 0)
            throw new PersonServiceException("Invalid age!");
        if (phone == null || phone <= 100000)
            throw new PersonServiceException("Invalid phone!");
        if (gender == null || gender.isEmpty())
            throw new PersonServiceException("Invalid gender!");
    }

    public static void validateId(Long id) throws PersonServiceException {
        if (id == null || id <= 0)
            throw new PersonServiceException("Invalid id!");
    }
}
