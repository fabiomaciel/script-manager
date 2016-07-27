package com.fabiomsouza.scriptmanager.service;

import com.fabiomsouza.scriptmanager.domain.Movie;
import com.fabiomsouza.scriptmanager.domain.MovieCharacter;
import com.fabiomsouza.scriptmanager.repository.MovieCharacterRepository;
import com.fabiomsouza.scriptmanager.repository.SceneRepository;
import com.fabiomsouza.scriptmanager.repository.WordCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptService {

    SceneRepository sceneRepository;
    MovieCharacterRepository movieCharacterRepository;
    WordCountRepository wordCountRepository;

    @Autowired
    public ScriptService(
            SceneRepository sceneRepository,
            MovieCharacterRepository movieCharacterRepository,
            WordCountRepository wordCountRepository) {

        this.sceneRepository = sceneRepository;
        this.movieCharacterRepository = movieCharacterRepository;
        this.wordCountRepository = wordCountRepository;

    }

    public boolean add(String script) {
        if (sceneRepository.findOne(1L) != null) return false;
        Movie movie = new Movie();
        String[] lines = script.split("\r\n");
        for (String line : lines)
            movie.addLine(line);

        sceneRepository.save(movie.getSceneList());

        for(MovieCharacter character : movie.getCharacters())
            wordCountRepository.save(character.getWordCounts());
        return true;

    }

}
