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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restwithspringboot.data.vo.BookVO;
import br.com.api.restwithspringboot.services.BookService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;
	
	@GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Sort sort = Sort.by((direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), "launchDate");
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<BookVO> books = bookService.findAll(pageable);
		
		books.stream().forEach(p ->  p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toModel(books), HttpStatus.OK);
	}
	
	@GetMapping(value="/findBookByAuthor/{author}", produces = {"application/json","application/xml", "application/x-yaml"})
	public ResponseEntity<?> findBookByAuthor(
			@PathVariable("author") String author,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Sort sort = Sort.by((direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC), "launchDate");
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<BookVO> books = bookService.findBookByAuthor(author, pageable);
		
		books.stream().forEach(p ->  p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		
		return new ResponseEntity<>(assembler.toModel(books), HttpStatus.OK);
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
