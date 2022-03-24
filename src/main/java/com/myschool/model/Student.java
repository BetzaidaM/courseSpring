package com.myschool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Student") //Le dice a springboot que es una clase que sera una BD
@Table(name="student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_email_unique",
                        columnNames ="email"
                )}) //instruccion a Hybernate para generar la tabla y asignar nombre
public class Student {
    @Id //es usada tanto en metodos como en campos, no es de TYPE es con METHOD, FIELD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //se ira generando el ID de 1 en 1
    @JsonProperty("id")
    private long id;

    @Column(name="first_name",nullable = false)
    @Size(max=100)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name="last_name")
    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @Column(name="email")
    @NotNull
    @JsonProperty("email")
    private String email;

    @Column(name="age")
    @NotNull
    @Min(value=4)
    @Max(value = 70)
    @JsonProperty("age")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM") //se usa para formatear fecha
    private int age;
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();//Libros disponibles en la tabla

    @OneToOne(
            mappedBy = "student", //quien hace referencia es el student, o sea esta del otro lado, nombre variable que se debe usar para referenciarlo
            orphanRemoval = true, //si se eliminan datos, se eliminan los que no tienen clase padre (Student. Se elimina si se queda sin relaciones
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE} //en que momentos se mantiene la integridad, si se elimina un studiante se elimina su card
    )//hacer referencia al card
    //al estar el OneTooOne en ambas se refiere a que ya existe la conexion bidireccional
    private StudentIdCard  studentIdCard;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "student"
    )
    private List<Enrolment> enrolments = new ArrayList<>(); //tendra la lista de los cursos a los que esta registrado

    //Constructores requeridos por la clase
    public Student(String firstName, String lastName, String email, Integer age){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.age=age;
    }

    public Student(){

    }
    //constructores clase card
    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //methods
    public void addBook(Book book) {
       for(Book book1 : this.books){
           System.out.println(book1.toString());
       }
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        }
    }
    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }
    public List<Enrolment> getEnrolments() {
        return enrolments;
    }
    public void addEnrolment(Enrolment enrolment) {
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);//añade a la lista un nuevo enrolment
        }
    }
    public List<Book> getBooks() {
        return books;
    }}
