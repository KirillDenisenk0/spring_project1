package spring_app.config.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {

    private  int id;
    @NotEmpty(message = "Name of book should be not empty!")
    @Size(min = 3, max = 900, message = "Length of book should be from 3 to 900 characters")
    private String name;
    @NotEmpty(message = "Book must have author!")
    @Size(min = 1, max = 900, message = "Length of author`s name should be from 3 to 900 characters")
    private String author;

    @Min(value = 1500,message = "Year must be greater than 1500!")
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
