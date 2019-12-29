package br.com.api.restwithspringboot.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restwithspringboot.data.vo.BookVO;
import br.com.api.restwithspringboot.services.BookService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
	public List<BookVO> findAll() {
		List<BookVO> books = bookService.findAll();
		books.stream().forEach(p ->  p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO BookVO = bookService.findById(id);
		BookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return BookVO;
	}
	
	@PostMapping(produces = {"application/json","application/xml", "application/x-yaml"}, consumes = {"application/json","application/xml", "application/x-yaml"})
	public BookVO create(@RequestBody BookVO Book) {
		BookVO BookVO = bookService.create(Book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getKey())).withSelfRel());
		return BookVO;
	}
	
	@PutMapping(produces = {"application/json","application/xml", "application/x-yaml"}, consumes = {"application/json","application/xml", "application/x-yaml"})
	public BookVO update(@RequestBody BookVO Book) {
		BookVO BookVO = bookService.update(Book);
		BookVO.add(linkTo(methodOn(BookController.class).findById(BookVO.getKey())).withSelfRel());
		return BookVO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		bookService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
