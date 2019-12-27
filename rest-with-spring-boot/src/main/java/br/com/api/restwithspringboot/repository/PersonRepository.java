package br.com.api.restwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.restwithspringboot.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
