package com.myschool.model;

import com.sun.istack.NotNull;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="Course")
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="Id")
    private long id;
    @Column(name="name")
    @NotNull
    private String name;
    @Column(name="department")
    @NotNull
    private String department;

    //relacion creada para enrolment
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    private List<Enrolment> enrolments = new ArrayList<>();


    //constructores
    public Course(){

    }
    public Course(String name, String department){
        this.name=name;
        this.department=department;
    }
//getter,setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
