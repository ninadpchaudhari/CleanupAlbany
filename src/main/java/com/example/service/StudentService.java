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
	
	public Student findByEmail(String email) {
		return studentRepo.findByEmail(email);
	}
}
