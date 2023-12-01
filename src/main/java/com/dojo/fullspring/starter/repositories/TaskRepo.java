package com.dojo.fullspring.starter.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dojo.fullspring.starter.models.Task;




public interface TaskRepo extends CrudRepository<Task, Long> {
	
    // this method retrieves all the books from the database
    // on implemente ici les methodes qui ne sont pas dans le CrudRepository
    List<Task> findAll();
    Optional<Task> findById(Long id);

  


}
