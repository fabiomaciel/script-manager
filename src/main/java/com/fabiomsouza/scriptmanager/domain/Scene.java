package com.fabiomsouza.scriptmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovieCharacter> characters;

    @JsonIgnore
    @Transient
    private MovieCharacter lastCharacter;

    public Scene() {
        this.characters = new ArrayList<>();
    }

    public Scene(String name) {
        this();
        this.name = name.trim();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharacters(ArrayList<MovieCharacter> characters) {
        this.characters = characters;
    }

    public List<MovieCharacter> getCharacters() {
        return this.characters;
    }


    public MovieCharacter getLastCharacter() {
        return this.lastCharacter;
    }

    public void addCharacter(MovieCharacter character) {
        this.lastCharacter = this.characters.stream()
                .filter(c -> c == character)
                .findFirst().orElseGet(() -> this.addNewCharacter(character));

    }

    private MovieCharacter addNewCharacter(MovieCharacter character) {
        this.characters.add(character);
        character.addScene(this);
        return character;
    }

    public Optional<MovieCharacter> retrieveCharacterByName(String name){
        return this.characters.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }
}

