package com.fabiomsouza.scriptmanager.service;

import com.fabiomsouza.scriptmanager.domain.MovieCharacter;
import com.fabiomsouza.scriptmanager.repository.MovieCharacterRepository;
import com.fabiomsouza.scriptmanager.repository.WordCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersService {

    private final MovieCharacterRepository movieCharacterRepository;
    private final WordCountRepository wordCountRepository;

    @Autowired
    CharactersService(MovieCharacterRepository repository,
                      WordCountRepository wordCountRepository){
        this.movieCharacterRepository = repository;
        this.wordCountRepository = wordCountRepository;
    }


    public MovieCharacter getCharacter(Long id){
        MovieCharacter character = this.movieCharacterRepository.findOne(id);
        fillWordCount(character);
        return character;
    }

    public List<MovieCharacter> getAllCharacters(){
        List<MovieCharacter> characters = this.movieCharacterRepository.findAll();

        for(MovieCharacter character: characters){
            fillWordCount(character);
        }
        return characters;
    }

    public MovieCharacter fillWordCount(MovieCharacter character){
        character.setWordCounts(this.wordCountRepository.
                findTop10ByCharacterIdOrderByCountDesc(character.getId()));
        return character;
    }


}
