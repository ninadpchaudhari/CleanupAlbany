/**
 * 
 */
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Student;
import com.example.repos.StudentRepository;

/**
 * @author NC731749
 *
 */
@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepo;
	
	/*
	 * Creates a new Student
	 */
	public Student createStudent(String firstName, String lastName, String email) {
		return new Student(firstName,lastName,email);
		
	}
	/*
	 * Saves the student to DB
	 */
	public Student saveStudent(Student st) {
		return studentRepo.save(st);
	}
	
	public Student findByEmail(String email) {
		return studentRepo.findByEmail(email);
	}
}
