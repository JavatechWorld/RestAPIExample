package com.rest.implementation.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.implementation.model.MovieDTO;
import com.rest.implementation.model.Movies;
import com.rest.implementation.repository.MovieRepo;


@RestController
public class RestApiController {

	@Autowired
	MovieRepo movieRepo;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Rest Api Project";
	}

	@PostMapping("/save")
	public ResponseEntity<Object> saveMovie(@RequestBody MovieDTO movie) {
		Movies mv = new Movies();
		mv.setName(movie.getName());
		mv.setYearOfRelease(movie.getYearOfRelease());
		Movies mvs = movieRepo.save(mv);
		if (mvs != null) {
			return generateOutput("Your Movies are saved now!", HttpStatus.OK, mvs);
		} else {
			return generateOutput("Your Movies are not saved!", HttpStatus.BAD_REQUEST, movie);
		}
	}
	
	@GetMapping("/getMovies")
	public ResponseEntity<Object> getAllMovies(){
		List<Movies> mv = movieRepo.findAll();
		return generateOutput("These are your movies!", HttpStatus.OK, mv);
	}

	private ResponseEntity<Object> generateOutput(String msg, HttpStatus st, Object movie) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Message", msg);
		map.put("Status", st);
		map.put("data", movie);

		return new ResponseEntity<Object>(map, st);
	}
}
