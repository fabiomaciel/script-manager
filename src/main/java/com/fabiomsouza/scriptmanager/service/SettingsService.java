package com.fabiomsouza.scriptmanager.service;

import com.fabiomsouza.scriptmanager.domain.MovieCharacter;
import com.fabiomsouza.scriptmanager.domain.Scene;
import com.fabiomsouza.scriptmanager.repository.SceneRepository;
import com.fabiomsouza.scriptmanager.repository.WordCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {

    private final SceneRepository sceneRepository;
    private final WordCountRepository wordCountRepository;

    private final CharactersService charactersService;

    @Autowired
    SettingsService(SceneRepository repository,
                    WordCountRepository wordCountRepository,
                    CharactersService charactersService){
        this.sceneRepository = repository;
        this.wordCountRepository = wordCountRepository;
        this.charactersService = charactersService;
    }


    public Scene getSetting(Long id){
        Scene setting = sceneRepository.findOne(id);
        for(MovieCharacter character: setting.getCharacters())
            charactersService.fillWordCount(character);

        return setting;
    }

    public List<Scene> getAllSettings(){
        List<Scene> settings = sceneRepository.findAll();
        for(Scene setting : settings) {
            for (MovieCharacter character : setting.getCharacters()) {
                charactersService.fillWordCount(character);
            }
        }

        return settings;
    }




}
