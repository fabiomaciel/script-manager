package com.fabiomsouza.scriptmanager.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie {

    private final Pattern scenePattern = Pattern
            .compile("(?<=INT./EXT. |INT. |EXT.)(.*?)(?= -|$)");
    private final Pattern characterPattern = Pattern
            .compile("(^\\s{22}\\w)");
    private final Pattern dialoguePattern = Pattern
            .compile("(^\\s{10}\\w)");

    private List<Scene> sceneList;
    private Scene lastScene;
    private ArrayList<MovieCharacter> characters;
    private MovieCharacter lastCharacter;

    public Movie() {
        this.sceneList = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public List<Scene> getSceneList() {
        return sceneList;
    }

    public ArrayList<MovieCharacter> getCharacters() {
        return characters;
    }

    public void addLine(String line) {
        if (isScene(line)) {
            Matcher m = scenePattern.matcher(line);
            m.find();
            addScene(m.group(1));
        } else if (isCharacter(line)) {
            if (getSceneList().size() > 0) {
                this.getLastScene().addCharacter(this.addCharacter(line.toUpperCase().trim()));
                this.lastCharacter = this.getLastScene().getLastCharacter();
            }
        } else if (isDialogue(line)) {
            if (getCharacters().size() > 0) {
                line = line.replaceAll("[\\p{P}&&[^']]", " ");
                String[] words = line.toLowerCase().split(" ");
                for (String word : words) {
                    if (!word.isEmpty())
                        this.getLastScene().getLastCharacter().addWord(word);
                }
            }
        }

    }

    protected void addScene(String sceneName) {
        retrieveSceneByName(sceneName)
                .orElseGet(() -> this.addNewScene(sceneName));
    }

    public Optional<Scene> retrieveSceneByName(String sceneName) {
        return this.sceneList.stream()
                .filter(c -> c.getName().equals(sceneName))
                .findFirst();
    }

    public Optional<MovieCharacter> retrieveCharacterByName(String name) {
        return this.characters.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    private Scene addNewScene(String characterName) {
        Scene scene = new Scene(characterName);
        this.sceneList.add(scene);
        this.lastScene = scene;
        return scene;
    }

    protected boolean isCharacter(String line) {
        return characterPattern.matcher(line).find();
    }

    protected boolean isDialogue(String line) {
        return dialoguePattern.matcher(line).find();
    }

    protected boolean isScene(String line) {
        return scenePattern.matcher(line).find();
    }

    public Scene getLastScene() {
        return lastScene;
    }

    public MovieCharacter getLastCharacter() {
        return this.lastCharacter;
    }

    public MovieCharacter addCharacter(String characterName) {
        this.lastCharacter = this.characters.stream()
                .filter(c -> c.getName().equals(characterName))
                .findFirst().orElseGet(() -> this.addNewCharacter(characterName));
        return this.lastCharacter;
    }


    private MovieCharacter addNewCharacter(String characterName) {
        MovieCharacter character = new MovieCharacter(characterName);
        this.characters.add(character);
        return character;
    }
}
