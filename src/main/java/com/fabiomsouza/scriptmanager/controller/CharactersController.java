package com.fabiomsouza.scriptmanager.controller;

import com.fabiomsouza.scriptmanager.DefaultResponse;
import com.fabiomsouza.scriptmanager.domain.MovieCharacter;
import com.fabiomsouza.scriptmanager.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharactersController {

	@Autowired
	CharactersService service;


	@RequestMapping
	public List<MovieCharacter> getAll(){
		return service.getAllCharacters();
	}

	@RequestMapping("/{id}")
	public MovieCharacter get(@PathVariable Long id){
		return service.getCharacter(id);
	}

    @ExceptionHandler(Exception.class)
    public DefaultResponse except(Exception e){
        return new DefaultResponse("Unexpected error");
    }


}
