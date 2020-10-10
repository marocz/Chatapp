package com.lit.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CustomController {
	
	@Autowired
	private ModelService service;
	
	@PostMapping("/create")
	public ResponseEntity<Model> createPerson(@RequestBody Model model) {
		Model response = service.createPerson(model);
		return new ResponseEntity<CustomController.Model>(response, HttpStatus.CREATED);
	}
	

	@GetMapping("/name")
	public ResponseEntity<Model> getPersonByName(@RequestParam String name) {
		Model response = service.getPersonByName(name);
		return new ResponseEntity<CustomController.Model>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Model>> getAllPersons() {
		List<Model> responses = service.getAllPersons();
		return new ResponseEntity<List<Model>>(responses, HttpStatus.OK);
	}
	
	
	static class Model {
		private String name;
		private String age;
		private String gender;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
	}
	
	@Service
	static class ModelServiceImpl implements ModelService {
		
		List<Model> models = new ArrayList<CustomController.Model>();
		
		public Model createPerson(Model model) {
			
			Model myModel = new Model();
			myModel.setAge(model.getAge());
			myModel.setName(model.getName());
			myModel.setGender(model.getGender());
			
			models.add(myModel);
			return myModel;
				
		}
		
		public Model getPersonByName(String name) {
			for(Model model : models) {
				if(name.equalsIgnoreCase(model.getName())) {
					return model;
				}
			}
			return null;
		}
		
		public List<Model> getAllPersons() {
			return models;
		}
		
	}
	
	static interface ModelService {
		
		public Model createPerson(Model model);
		
		public Model getPersonByName(String name);
		
		public List<Model> getAllPersons();
	}

}
