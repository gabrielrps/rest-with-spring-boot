package br.com.api.restwithspringboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.restwithspringboot.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	
	@Query("SELECT b FROM Book b WHERE b.author LIKE LOWER(CONCAT('%', :author, '%'))")
	public Page<Book> findBookByAuthor(@Param("author") String author, Pageable pageable);

}
