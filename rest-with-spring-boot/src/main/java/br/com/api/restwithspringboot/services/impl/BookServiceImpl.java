package br.com.api.restwithspringboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.restwithspringboot.converter.DozerConverter;
import br.com.api.restwithspringboot.data.model.Book;
import br.com.api.restwithspringboot.data.vo.BookVO;
import br.com.api.restwithspringboot.exception.ResourceNotFoundException;
import br.com.api.restwithspringboot.repository.BookRepository;
import br.com.api.restwithspringboot.services.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public BookVO findById(Long id) {
		Book Book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(Book, BookVO.class);
	}

	@Override
	public Page<BookVO> findAll(Pageable pageable) {
		 Page<Book> page = bookRepository.findAll(pageable);
		 return page.map(this::convertEntityToVO);
	}
	
	@Override
	public Page<BookVO> findBookByAuthor(String author, Pageable pageable) {
		Page<Book> page = bookRepository.findBookByAuthor(author, pageable);
		return page.map(this::convertEntityToVO);
	}

	@Override
	public BookVO create(BookVO Book) {
		Book entity = DozerConverter.parseObject(Book, Book.class);
		BookVO vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
		return vo;
	}
	
	@Override
	public BookVO update(BookVO Book) {
		Book entity = bookRepository.findById(Book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		Book p = DozerConverter.parseObject(Book, Book.class);
		
		return DozerConverter.parseObject(bookRepository.save(p), BookVO.class);
	}

	@Override
	public void delete(Long id) {
		Book entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		bookRepository.delete(entity);
	}
	
	private BookVO convertEntityToVO(Book book) {
		return DozerConverter.parseObject(book, BookVO.class);
	}

}
