package com.fabiomsouza.scriptmanager.repository;

import com.fabiomsouza.scriptmanager.domain.MovieCharacter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieCharacterRepository extends CrudRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAll();
}
