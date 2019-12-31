package br.com.api.restwithspringboot.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restwithspringboot.data.vo.PersonVO;
import br.com.api.restwithspringboot.services.PersonService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler<PersonVO> assembler) {
		
		
		Sort sort = Sort.by((direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), "firstName");
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<PersonVO> persons = personService.findAll(pageable);
		persons.stream().forEach(p ->  p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toModel(persons), HttpStatus.OK);
	}
	
	@GetMapping(value = "/findPersonByName/{firstName}" ,produces = {"application/json","application/xml", "application/x-yaml"})
	public ResponseEntity<?> findPersonByName(@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler<PersonVO> assembler){
		
		Sort sort = Sort.by((direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), "firstName");
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<PersonVO> pages = this.personService.findPersonByName(firstName, pageable);
		
		pages.stream().forEach(p ->  p.add(linkTo(methodOn(PersonController.class).findPersonByName(firstName, page, limit, direction, assembler)).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toModel(pages), HttpStatus.OK);
	}
			
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = personService.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@PostMapping(produces = {"application/json","application/xml", "application/x-yaml"}, consumes = {"application/json","application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = personService.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@PutMapping(produces = {"application/json","application/xml", "application/x-yaml"}, consumes = {"application/json","application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = personService.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@PatchMapping(value = "{id}", produces = {"application/json","application/xml", "application/x-yaml"}, consumes = {"application/json","application/xml", "application/x-yaml"})
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = personService.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
