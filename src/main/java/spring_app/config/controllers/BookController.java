package spring_app.config.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring_app.config.dao.BookDao;
import spring_app.config.dao.PersonDao;
import spring_app.config.models.Book;
import spring_app.config.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDao bookDao;
    private PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.show(id));

        Optional<Person> bookOwner = bookDao.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDao.index());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/new";
        }else {
            bookDao.save(book);
            return "redirect:books";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id ){
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "books/edit";
        } else {
            bookDao.update(id,book);
            return "redirect:books";
        }
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id ){
        bookDao.delete(id);
        return "redirect:books";
    }


    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDao.release(id);
        return "redirect:books/"+id;
    }


    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        bookDao.assign(id,selectedPerson);
        return "redirect/books/"+id;
    }
}
