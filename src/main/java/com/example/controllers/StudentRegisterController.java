package com.example.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.Student;
import com.example.service.GPSDataService;
import com.example.service.StudentService;
@Controller
public class StudentRegisterController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	GPSDataService gps;
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	//@PostMapping
	public String renderFirstPage(@RequestParam Map<String,Object> model,Model viewModel) {
		System.out.println("/hit");
		Student st = studentService.createStudent("abc", "def", "abc@def.com");
		studentService.saveStudent(st);
		viewModel.addAttribute("ID", st.getId());
		return "index";
	}
	
	@GetMapping("/testMSSQL")
	public String testSql(@RequestParam Map<String,Object> model,Model viewModel) {
		gps.updateTruckLocations();
		viewModel.addAttribute("ID","2");
		return "index";
	}
}
