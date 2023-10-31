package spring_app.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring_app.config.dao.PersonDao;
import spring_app.config.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDao.getPersonByFullName(person.getName()).isPresent()){
            errors.rejectValue("name", "","Person with this name is already exists!");
        }

       /* if(personDao.show(person.getEmail()).isPresent()){
            errors.rejectValue("email","", "This email is already exist!");
        }*/

    }
}
