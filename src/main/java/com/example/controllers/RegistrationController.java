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
import org.springframework.web.bind.annotation.RestController;

import com.example.models.PersonalDevice;
import com.example.models.Student;
import com.example.service.RegistrationService;
import com.example.service.StudentService;



@RestController
public class RegistrationController {
	
	
	
	@Autowired
	RegistrationService regService;

	@PostMapping(value="/registerPersonDevice")
	@ResponseBody
	public PersonalDevice updateRestaurantProfile(@RequestParam Map<String, Object> model, ModelMap otherModel){
		System.out.println("Got a hit on register personal device");
		String fcmtoken  = (String) model.get("fcmtoken");
		PersonalDevice pd = regService.registerDevice(fcmtoken);
		System.out.println("registered device ");
		return pd;
	}
	
	/*
	 * https://stackoverflow.com/questions/30558784/spring-rest-controller-return-specific-fields
	 * @ResponseBody
	public Map<String, Object> getGame(@PathVariable("id") long id, String include) {

    Game game = service.loadGame(id);
    // check the `include` parameter and create a map containing only the required attributes
    Map<String, Object> gameMap = service.convertGameToMap(game, include);

    return gameMap;

}
	 */
	 
}
