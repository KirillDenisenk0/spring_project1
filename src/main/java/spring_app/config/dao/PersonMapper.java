package spring_app.config.dao;

import org.springframework.jdbc.core.RowMapper;
import spring_app.config.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setName(rs.getString("name"));
        person.setBirthYear(rs.getInt("birth_year"));

        return  person ;
    }
}
