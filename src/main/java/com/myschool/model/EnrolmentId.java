package com.myschool.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable //quiere decir que sera una clase embebida
public class EnrolmentId implements Serializable {
    //debe implementar el serializable para que no de error
    @Column(name="student_id")
    private Long studentId;
    @Column(name="course_id")
    private long courseId;

    public EnrolmentId(){
        //CONSTRUCTOR
    }
    public EnrolmentId(long studentId, long courseId){
        this.studentId=studentId;
        this.courseId=courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
