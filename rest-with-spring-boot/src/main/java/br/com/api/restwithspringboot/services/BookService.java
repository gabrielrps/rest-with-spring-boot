package br.com.api.restwithspringboot.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.api.restwithspringboot.data.vo.BookVO;

public interface BookService {
	
	public BookVO findById(Long id);

	public Page<BookVO> findAll(Pageable pageable);
	
	public BookVO create(BookVO person);
	
	public BookVO update(BookVO person);
	
	public void delete(Long id);
	
	public Page<BookVO> findBookByAuthor(String author, Pageable pageable);

}
