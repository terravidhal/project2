package com.dojo.fullspring.starter.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.fullspring.starter.models.User;



    
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    
    List<User> findAll();
	Optional<User> findByEmail(String email);
	User findByIdIs(Long id);
    
}



