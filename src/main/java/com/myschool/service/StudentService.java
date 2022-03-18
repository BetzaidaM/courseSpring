package com.myschool.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.myschool.model.Student;
import com.myschool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service //NOTACION SERVICE, compontente service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired //Crea la instancia del StudentRepository ya no se hace el new... construye y desconstruye
    //los objetos, la clas en realidad no se preocupa por saber de donde tiene la info
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents(){
        //interfaz que trabaja con estudiantes y solito nos genera el metodo para traer info
        return studentRepository.findAll();
    }
    public List<Student> getStudentsByEmail(String email){
        //hace referencia a repository y trae el metodo que se quiere
        return studentRepository.getStudentsByEmail(email);
    }
    public Student getStudentById(long id){

        return studentRepository.getStudentById(id);
    }

    //INSERT CON PARAMETROS
    public void registerStudent(String name, String email, LocalDate dob){
       // System.out.println (studentRepository.registerStudent(student));
         studentRepository.registerStudent(name,email,dob);
    }
    //Delete sin return
    public void deleteStudent(long id){
        studentRepository.deleteStudent(id);
    }
    //Delete con return de estudiantes actualizados
    public List<Student> removeStudent(long id){
         studentRepository.deleteById(id);
        return getStudents();
    }
    //UPDATE CON PARAMETROS
    public void updateStudent(Long id, String name, String email, LocalDate dob){
         studentRepository.updateStudent(id,name,email,dob);
    }

    //INSERT CON BODY
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    //UPDATE CON BODY
    public Student updStudent(Student student){
        return studentRepository.save(student);
    }

}
