package com.example.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class RegistrationController {

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String renderFirstPage(@RequestParam Map<String,Object> model,Model viewModel) {
		System.out.println("/ hit");
		return "index";
	}
	
}
