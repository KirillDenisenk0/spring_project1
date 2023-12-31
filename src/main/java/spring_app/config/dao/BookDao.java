package spring_app.config.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring_app.config.models.Book;
import spring_app.config.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private  static JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Book> index(){
      return   jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return  jdbcTemplate.query("SELECT * FROM Book WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class)
                ).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(name,author,year) VALUES (?,?,?)", book.getName(),book.getAuthor(),book.getYear()
                );
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?,author=?, year=? WHERE id=?",updatedBook.getName(),updatedBook.getAuthor()
                ,updatedBook.getYear(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN PERSON ON Book.user_id=Person.user_id" + "WHERE Book.id=?"
        , new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?" , new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE Book SET user_id=null where id=?" ,id);
    }

    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET user_id=? WHERE id=?", selectedPerson.getId(),id );
    }
}
