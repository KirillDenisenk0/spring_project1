package spring_app.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;
import spring_app.config.models.Book;
import spring_app.config.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private  static  JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
      return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String email){
        return jdbcTemplate.query("SELECT FROM Person WHERE email=?",new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public Person show(int id){

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},new BeanPropertyRowMapper<>(Person.class)
                ).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO PERSON(name,birth_year) VALUES (?,?)", person.getName(),person.getBirthYear()
                );
    }

    public void update(int id, Person updatedPerson) {
      jdbcTemplate.update("UPDATE Person SET name=?, birth_year=? WHERE id=?", updatedPerson.getName(),
              updatedPerson.getBirthYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?" , new Object[]{id},new BeanPropertyRowMapper<>(Book.class));

    }


}
