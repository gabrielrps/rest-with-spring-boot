package br.com.api.restwithspringboot.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import br.com.api.restwithspringboot.data.vo.PersonVO;

public interface PersonService {
	
	public PersonVO findById(Long id);

	public Page<PersonVO> findAll(Pageable pageable);
	
	public PersonVO create(PersonVO person);
	
	public PersonVO update(PersonVO person);
	
	public void delete(Long id);
	
	public PersonVO disablePerson(Long id);
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable);
	
}
