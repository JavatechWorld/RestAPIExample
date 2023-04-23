package com.rest.implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.implementation.model.Movies;

public interface MovieRepo extends JpaRepository<Movies, Integer> {

}
