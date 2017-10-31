package br.com.serverstart.server.repository;

import br.com.serverstart.server.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRespository extends PagingAndSortingRepository<Person, Long> {

}