package com.example.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.Student;
import com.example.service.RegistrationService;
import com.example.service.StudentService;



@Controller
public class RegistrationController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	RegistrationService regService;

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	//@PostMapping
	public String renderFirstPage(@RequestParam Map<String,Object> model,Model viewModel) {
		System.out.println("/hit");
		Student st = studentService.createStudent("abc", "def", "abc@def.com");
		studentService.saveStudent(st);
		viewModel.addAttribute("ID", st.getId());
		return "index";
	}
	
	@PostMapping(value="/registerPersonDevice")
	@ResponseBody
	public ResponseEntity<?> updateRestaurantProfile(@RequestParam Map<String, Object> model, ModelMap otherModel){
		System.out.println("Got a hit on register personal device");
		String fcmtoken  = (String) model.get("fcmtoken");
		if(regService.registerDevice(fcmtoken) == null) return ResponseEntity.badRequest().build();
		System.out.println("registered device ");
		return ResponseEntity.accepted().build();
	}
}
