package br.com.api.restwithspringboot.services;

import java.util.List;

import br.com.api.restwithspringboot.data.vo.PersonVO;

public interface PersonService {
	
	public PersonVO findById(Long id);

	public List<PersonVO> findAll();
	
	public PersonVO create(PersonVO person);
	
	public PersonVO update(PersonVO person);
	
	public void delete(Long id);

}
