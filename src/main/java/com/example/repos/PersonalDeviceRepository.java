package com.example.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.PersonalDevice;
import com.example.models.Student;

public interface PersonalDeviceRepository extends JpaRepository<PersonalDevice, Long> {

	public PersonalDevice findById(Long id);
	public PersonalDevice findByFcmtoken(String fcmtoken);
}
