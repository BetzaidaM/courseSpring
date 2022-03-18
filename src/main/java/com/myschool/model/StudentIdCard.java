package com.myschool.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="StudentIdCard") //esto le indica que es una tabla que almacena info
@Table(name="student_id_card",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "student_id_card_number_unique",
                columnNames ="card_numbers"
        )
    }
) //dar info de que especificaciones tendra la tabla
public class StudentIdCard {

    @Id
    @GeneratedValue(strategy = IDENTITY) //generar valores con el identity, aumentan solos
    @Column(name="id")
    private long id;
    @Column(name="card_numbers")
    @Size(max=15)
    private String cardNumber;

    //Generar referencia a Student
    @OneToOne(cascade = CascadeType.ALL) //relacion desde aqui hasta student y especificar como hace la relacion
    @JoinColumn(
            name="student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="student_id_fk"
            )
    )//dar la referencia de como se hace la union, name es el nombre de la columna que lo almacena
    private Student student;

    //constructores de la clase card
    public StudentIdCard(String cardNumber){this.cardNumber=cardNumber;}

    public StudentIdCard(String cardNumber, Student student){
        this.cardNumber=cardNumber;
        this.student=student;
    }
    public StudentIdCard(){
         //constructor vacio
    }
//metodos de la clase student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
