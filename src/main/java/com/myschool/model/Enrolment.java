package com.myschool.model;

import com.sun.istack.NotNull;
import javafx.util.converter.LocalDateStringConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Enrolment")
@Table(name="enrolment")
public class Enrolment {
    @EmbeddedId //especificar que es un ID embebido
    private EnrolmentId id; //se traen las columnas de la clase EnrolmentId para que sean usadas aqui

    @ManyToOne //relacion para las otras dos tablas
    @MapsId("studentId")//id del que va a mapear, se encuentra el nombre de la variable que hace referencia en EnrolmentId
    @JoinColumn(
            name="student_id",
            foreignKey = @ForeignKey(
                    name="enrolment_student_id_fk"
            )
    )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name="course_id",
            foreignKey = @ForeignKey(
                    name="enrolment_course_id_fk"
            )
    )
    private Course course;

    @Column(name="created_at")
    @NotNull
    private LocalDateTime createdAt;

    public Enrolment(){

    }
    public Enrolment(EnrolmentId id, Student student, Course course, LocalDateTime createdAt){
        this.id=id;
        this.student=student;
        this.course=course;
        this.createdAt=createdAt;
    }
    public Enrolment(Student student,Course course, LocalDateTime createdAt){
        this.student=student;
        this.course=course;
        this.createdAt=createdAt;
    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
