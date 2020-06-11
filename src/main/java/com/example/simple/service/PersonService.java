package com.example.simple.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.simple.dao.PersonDao;
import com.example.simple.model.Person;

@Service
public class PersonService {
	
	private final PersonDao personDao;
	
	// Qualifier lets you specify which implementation of PersonDao you want this service to use
	// Super handy if you eventually want to migrate your service to use a different PersonDao implemention
	// Such as one that connects to a MongoDB, Oracle DB, or something else
	// THIS IS THE BENEFIT OF SPRING DEPENDENCY INJECTION
	@Autowired
	public PersonService(@Qualifier("postgres") PersonDao personDao) {
		this.personDao = personDao;
	}

	public int insertPerson(Person person) {
		return personDao.insertPerson(person);
	}
	
	public List<Person> getAllPeople() {
		return personDao.selectAllPeople();
	}
	
	public Optional<Person> getPersonById(UUID id) {
		return personDao.selectPersonById(id);
	}
	
	public int deletePerson(UUID id) {
		return personDao.deletePersonById(id);
	}
	
	public int updatePerson(UUID id, Person person) {
		return personDao.updatePersonById(id, person);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
