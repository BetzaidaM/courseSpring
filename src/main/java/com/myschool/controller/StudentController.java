package com.myschool.controller;

import com.myschool.model.Student;
import com.myschool.representation.DiscountRO;
import com.myschool.service.ScholarshipService;
import com.myschool.service.StudentService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.List;

@RestController //esa directiva permite decirle que asi se ejecutará
@RequestMapping("/api/v1/myschool/students")  //tiene como parametro la URL de donde se estaran comunicando
public class StudentController {
    //Declarar la variable que hace referencia a la clase que se instancia
    private StudentService studentService;
    private ScholarshipService scholarshipService;

    @Autowired //Pedir que se haga instancia de StudentService
    public StudentController(StudentService studentService, ScholarshipService scholarshipService){
        this.studentService=studentService;
        this.scholarshipService=scholarshipService;
    }

    @GetMapping //si es un get, esta sera la directiva que se ejecutara
    public List<Student> getStudents(@RequestParam(required = false) String email){
        //Un controlador tiene un Map que es el que tiene toda la info del controlador
       if(email !=null){
           //Si es diferente de null traera la info
           return studentService.getStudentsByEmail(email);
       }
        return studentService.getStudents();
    }
    //OBTEJER UN SOLO ESTUDIANTE
   @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id){
        return studentService.getStudentById(id);
   }

   //INSERT PERO NO ES CON BODY
   @PostMapping
   public void registerStudent(@RequestParam("name") String name, @RequestParam("email") String email,
           @RequestParam("dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate dob){
       //System.out.println (studentService.registerStudent(student));
         studentService.registerStudent(name,email,dob);
   }
    //INSERT CON BODY
    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    //DELETE
   @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
   }
   //REMOVE CON RETURN DE ESTUDIANTES ACTUALIDOS
   @DeleteMapping("/remove/{id}")
   public List<Student> removeStudent(@PathVariable long id){
        return studentService.removeStudent(id);
   }
    //UPDATE PERO NO ES CON BODY
   @PutMapping("/{id}")
    public void updateStudent(@PathVariable long id, @RequestParam("name") String  name,
                                 @RequestParam ("email") String email, @RequestParam("dob")
                                    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)LocalDate dob){
         studentService.updateStudent(id,name,email,dob);
   }
    //UPDATE CON BODY
   @PutMapping("/update")
    public Student updStudent(@RequestBody Student student){
        return studentService.updStudent(student);
   }

   //INSERT BODY Y QUERY
//    @PostMapping("/register")
//    public void register(@RequestBody Student student){
//         studentService.register(student);
//    }


    @PostMapping("/{id}/sendtoscholarshipsystem")
    public void sendToScholarShipSystem(@PathVariable long id) throws Exception {
        scholarshipService.sendStudent(id);
    }
    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountRO>
    getStudentDiscount(@PathVariable long id) throws Exception{
        DiscountRO discountRO= scholarshipService.getStudentDiscount(id);
        return new ResponseEntity<>(discountRO, HttpStatus.OK);
    }
    @GetMapping("/{id}/discountfromcsvformat")//convertidor, para leer el formato de las comas
    //EL CONVERTIDOR SERIA UNA CLASE APARTE PARA PODER REGRESAR LA INFORMACIÓN EN EL RO QUE SE NECESITA, SERA COMO UN HANDLER
    public ResponseEntity<DiscountRO> getStudentDiscountFromCSVFormat(@PathVariable long id) throws Exception {
        DiscountRO discountRO = scholarshipService.getStudentDiscountWithCSVFormat(id);
        return new ResponseEntity<>(discountRO, HttpStatus.OK);
    }
}
