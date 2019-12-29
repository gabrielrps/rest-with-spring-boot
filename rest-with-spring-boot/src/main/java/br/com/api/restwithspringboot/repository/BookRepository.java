package br.com.api.restwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.restwithspringboot.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
