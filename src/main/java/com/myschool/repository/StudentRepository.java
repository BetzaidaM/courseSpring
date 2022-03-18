package com.myschool.repository;

import com.myschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository //ANOTACION para decir que es un repositorio, es un componente
public interface StudentRepository extends JpaRepository< Student,Long> {
    //extender el JpaRepository<clase con la qe trabaja, tipo de dato a retornar>
    //en ese caso el ID

    @Query("Select s from Student s where s.email like %?1%") //Es lenguaje JPQL,  QUERY que se ejecuta
    //metodo abstracto para ejecutar el Query
    public List<Student> getStudentsByEmail(String email); //se comento porque en realidad no iba aqui
    //DE ESTA FORMA VA A TRAER TODOS LOS ESTUDIANTES QUE CUMPLEN CON EL PARAMETRO, EN ESE CASO SE QUITA EL @QUERY


    //Obtener Student sin usar Query
    public Student getStudentById(long id);

    //Register Student PARAMS
    @Transactional
    @Modifying
    @Query(value="insert into Student (name,email,dob) values (:name,:email,:dob)",nativeQuery = true)
    public void registerStudent(String name, String email, LocalDate dob);

    //Delete
    @Transactional
    @Modifying
    @Query("delete from Student s where s.id=:id")
    public void deleteStudent(long id);

    //Update STUDENT PARAMS
    @Transactional
    @Modifying
    @Query(value="update Student s set name=:name, email=:email, dob=:dob where id=:id")
    public void updateStudent(Long id, String name, String email, LocalDate dob);

    //Register Student BODY
//    @Transactional
//    @Modifying
//    @Query(value="insert into Student S (name,email,dob) values ('S.name','S.email','S.dob')",nativeQuery = true)
//    public void register(Student student);

}
