package br.com.api.restwithspringboot.services;

import java.util.List;

import br.com.api.restwithspringboot.data.vo.BookVO;

public interface BookService {
	
	public BookVO findById(Long id);

	public List<BookVO> findAll();
	
	public BookVO create(BookVO person);
	
	public BookVO update(BookVO person);
	
	public void delete(Long id);

}
