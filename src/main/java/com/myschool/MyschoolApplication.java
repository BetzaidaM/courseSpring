package com.myschool;

import com.github.javafaker.Faker;
import com.myschool.model.*;
import com.myschool.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyschoolApplication.class, args);
	}
	@Bean //sabe que debe ejecutar esto primero y que debe traer una instancia del StudentRepository
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			// String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
			String email = "test@hotmail.com";
			Student student = new Student(
					firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));
			student.addBook(
					new Book("Clean Code", LocalDateTime.now().minusDays(4)));
			student.addBook(
					new Book("Think and Grow Rich", LocalDateTime.now()));
			student.addBook(
					new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));
			StudentIdCard studentIdCard = new StudentIdCard(
					"123456789",
					student);
			student.setStudentIdCard(studentIdCard);
			student.addEnrolment(new Enrolment(
					new EnrolmentId(1L, 1L),
					student,
					new Course("Computer Science", "IT"),
					LocalDateTime.now()
			));
			student.addEnrolment(new Enrolment(
					new EnrolmentId(1L, 2L),
					student,
					new Course("Spring Data JPA", "IT"),
					LocalDateTime.now().minusDays(18)
			));
			student.addEnrolment(new Enrolment(
					new EnrolmentId(1L, 2L),
					student,
					new Course("Spring Data JPA", "IT"),
					LocalDateTime.now().minusDays(18)
			));

			studentRepository.save(student);
			studentRepository.findById(1L)
					.ifPresent(s -> {//es un if y ejecuta lo que esta en la funcion si es true, si no, en este caso no hace nada
						System.out.println("fetch book lazy...");
						List<Book> books = student.getBooks(); //mandar llamar la lista de libros
						books.forEach(book -> {
							System.out.println(
									s.getFirstName() + " borrowed " + book.getBookName());
						});
					});
		};
	}

}
