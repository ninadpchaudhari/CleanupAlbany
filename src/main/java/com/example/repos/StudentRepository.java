/**
 * 
 */
package com.example.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Student;

/**
 * @author NC731749
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByEmail(String email);
}
