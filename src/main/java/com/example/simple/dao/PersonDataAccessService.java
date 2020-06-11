package com.example.simple.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.simple.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		
		jdbcTemplate.update(
			    "INSERT INTO person (id, name) VALUES (?, ?)",
			    id, person.getName()
			);
		return 0;
	}

	@Override
	public List<Person> selectAllPeople() {
		
		final String sql = "SELECT id, name FROM person";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(id, name);	
		});
		
//		List<Person> fakeList = new ArrayList<Person>();
//		fakeList.add(new Person(UUID.randomUUID(), "FROM POSTGRES DB"));
//		return fakeList;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		return Optional.empty();
	}

	@Override
	public int deletePersonById(UUID id) {
		return 0;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		return 0;
	}

}
