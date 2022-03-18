package com.myschool.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="Book")
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="created_at")
    @NotNull
     private LocalDateTime createdAt;

    @Column(name="book_name")
    @NotNull
    private String bookName;

    @ManyToOne
    @JoinColumn(
            name="student_id", //nombre de la columna
            nullable = false,
            referencedColumnName = "id", //ligado al id de Student
            foreignKey = @ForeignKey(
                    name="student_book_fk" //genera la referencia del lado del libro
            ))
    private Student student;
//Constructores de book
    public Book(){

    }
    public Book(String bookName,
                LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.bookName = bookName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
