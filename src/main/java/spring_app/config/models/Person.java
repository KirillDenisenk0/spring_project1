package spring_app.config.models;

import jakarta.validation.constraints.*;

public class Person {
    private  int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 0, max = 100,message = "Name should be between 0 and 30")
    private String name;
    @Min(value = 1900,message = "Year of birth should be greater than 1900")
    @Pattern(regexp = "\\d{4}")
    private int birthYear;

    public Person(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
