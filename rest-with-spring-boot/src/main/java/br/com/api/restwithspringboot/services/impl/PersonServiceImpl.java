package br.com.api.restwithspringboot.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.restwithspringboot.converter.DozerConverter;
import br.com.api.restwithspringboot.data.model.Person;
import br.com.api.restwithspringboot.data.vo.PersonVO;
import br.com.api.restwithspringboot.exception.ResourceNotFoundException;
import br.com.api.restwithspringboot.repository.PersonRepository;
import br.com.api.restwithspringboot.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public PersonVO findById(Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(person, PersonVO.class);
	}

	@Override
	public Page<PersonVO> findAll(Pageable pageable) {
		Page<Person> page = personRepository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}
	
	@Override
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		Page<Person> page = this.personRepository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}

	@Override
	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	@Override
	public PersonVO update(PersonVO person) {
		Person entity = personRepository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		Person p = DozerConverter.parseObject(person, Person.class);
		
		return DozerConverter.parseObject(personRepository.save(p), PersonVO.class);
	}

	@Override
	public void delete(Long id) {
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		personRepository.delete(entity);
	}

	@Transactional
	@Override
	public PersonVO disablePerson(Long id) {
		personRepository.disablePerson(id);
		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
}
