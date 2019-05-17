package com.logindemo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	RegistrationRepo repo;

	@GetMapping("/user")
	public List<RegistrationEntity> getAllNotes() {
		
		List<RegistrationEntity> registrationList=(List<RegistrationEntity>) repo.findAll();
		LOGGER.info(registrationList.toString());
		return registrationList;
	}

	@PostMapping("/adduser")
	public RegistrationEntity createNote(@Valid @RequestBody RegistrationEntity entityclass) {
		RegistrationEntity saveEntry=repo.save(entityclass);
		LOGGER.info(saveEntry.toString());

		return saveEntry ;
	}

	@GetMapping("/user/{id}")
	public Optional<RegistrationEntity> getById(@PathVariable(value = "id") Long id) {
		return repo.findById(id);
	
	}
	
		@PutMapping("/user/{id}")
		public ResponseEntity<Object> updateStudent(@RequestBody RegistrationEntity entityClass, @PathVariable long id) {

			Optional<RegistrationEntity> studentOptional = repo.findById(id);

			if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();
			repo.save(entityClass);
			return ResponseEntity.noContent().build();
		}
	

	@DeleteMapping("/user/{id}")
		public void deleteStudent(@PathVariable long id) {
		repo.deleteById(id);
	}

}
