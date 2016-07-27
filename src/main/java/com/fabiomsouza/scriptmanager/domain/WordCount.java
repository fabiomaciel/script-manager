package com.fabiomsouza.scriptmanager.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class WordCount {


    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String word;
    private Long count;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "character_id")
    private MovieCharacter character;
    

    public WordCount(){}

    public WordCount(String word){
        this.word = word;
        this.count = 1L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public MovieCharacter getCharacter() {
        return character;
    }

    public void setCharacter(MovieCharacter character) {
        this.character = character;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void addCount(){
        this.count++;
    }

}
