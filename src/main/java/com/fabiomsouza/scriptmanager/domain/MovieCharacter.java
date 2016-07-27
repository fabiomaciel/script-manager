package com.fabiomsouza.scriptmanager.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    @Transient
    private List<WordCount> wordCounts;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters")
    private List<Scene> sceneList;

    public MovieCharacter(){
        this.wordCounts = new ArrayList<>();
        this.sceneList = new ArrayList<>();
    }

    public MovieCharacter(String heading){
        this();
        this.name = heading.trim();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Scene> getSceneList() {
        return sceneList;
    }

    public void setSceneList(List<Scene> sceneList) {
        this.sceneList = sceneList;
    }

    public List<WordCount> getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(List<WordCount> wordCounts) {
        this.wordCounts = wordCounts;
    }

    public void addWord(String word){
        WordCount wordCount = retrieveWord(word);
        if(wordCount == null) {
            WordCount w = new WordCount(word);
            w.setCharacter(this);
            this.wordCounts.add(w);
        }else{
            wordCount.addCount();
        }
    }

    public WordCount retrieveWord(String word){
        return this.wordCounts.stream()
                .filter(w -> w.getWord().equals(word))
                .findFirst().orElseGet(() -> null);
    }

    public void addScene(Scene scene){
        this.sceneList.stream()
                .filter(s -> s == scene)
                .findFirst().orElse(addNewScene(scene));
    }

    private Scene addNewScene(Scene scene){
        this.sceneList.add(scene);
        return scene;
    }

}
