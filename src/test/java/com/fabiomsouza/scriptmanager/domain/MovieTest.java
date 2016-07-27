package com.fabiomsouza.scriptmanager.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class MovieTest {


    Movie movie;

    @Before
    public void NewMovie() {
        this.movie = new Movie();
    }

    @Test
    public void newMovieTest() {
        assertThat("Movie should have a sceneList property",
                movie.getSceneList(), CoreMatchers.anything());
    }

    @Test
    public void sceneListTest() {
        assertThat("Movie should hava a sceneList property of type List",
                movie.getSceneList(), CoreMatchers.instanceOf(List.class));

        movie.addScene("X-WING");
        assertThat("Movie should be able to add a new Scene by given name",
                movie.getSceneList().get(0), CoreMatchers.instanceOf(Scene.class));

        movie.addScene("JAKU");
        assertThat("Movie should be able to add Scenes to list by given name",
                movie.getSceneList().size(), CoreMatchers.equalTo(2));

        movie.addScene("X-WING");
        movie.addScene("JAKU");
        assertThat("Movie should not add a Scene if the name is already added",
                movie.getSceneList().size(), CoreMatchers.equalTo(2));

        assertThat("Should be able to get the last added scene",
                movie.getLastScene(), CoreMatchers.instanceOf(Scene.class));

    }

    @Test
    public void characterListTest(){
        assertThat("Movie should hava a characterList property of type List",
                movie.getCharacters(), CoreMatchers.instanceOf(List.class));

        movie.addCharacter("THREEPIO");
        assertThat("Movie should be able to add a new MovieCharacter by given name",
                movie.getCharacters().get(0), CoreMatchers.instanceOf(MovieCharacter.class));

        movie.addCharacter("ANAKIN");
        assertThat("Movie should be able to add MovieCharacters to list by given name",
                movie.getCharacters().size(), CoreMatchers.equalTo(2));

        movie.addCharacter("ANAKIN");
        movie.addCharacter("THREEPIO");
        assertThat("Movie should not add a MovieCharavter if the name is already added",
                movie.getCharacters().size(), CoreMatchers.equalTo(2));

    }

    @Test
    public void getSceneTest() {
        String name = "X-WING";
        movie.addScene(name);
        assertThat("Shoul return a scene by given name",
                movie.retrieveSceneByName(name).get().getName(),
                CoreMatchers.equalTo(name));

    }

    @Test
    public void isCharacterTest() {
        assertThat("Should return true if the string starts with 22 spaces",
                movie.isCharacter("                      THREEPIO"), CoreMatchers.equalTo(true));

        assertThat("Should return false if the string not starts with 22 spaces",
                movie.isCharacter("          THREEPIO"), CoreMatchers.equalTo(false));
    }

    @Test
    public void isDialogueTest() {
        assertThat("Should return true if the string starts with 10 spaces",
                movie.isDialogue("          hi there"), CoreMatchers.equalTo(true));

        assertThat("Should return false if the string not starts with 10 spaces",
                movie.isDialogue("hello there"), CoreMatchers.equalTo(false));
    }


    @Test
    public void isSceneTest() {
        assertThat("Should return true if the string starts with INT.",
                movie.isScene("INT. LUKE'S X-WING FIGHTER"), CoreMatchers.equalTo(true));

        assertThat("Should return true if the string starts with INT./EXT.",
                movie.isScene("INT./EXT. LUKE'S X-WING FIGHTER"), CoreMatchers.equalTo(true));

        assertThat("Should return true if the string starts with EXT.",
                movie.isScene("INT./EXT. LUKE'S X-WING FIGHTER"), CoreMatchers.equalTo(true));

        assertThat("Should return false if the string not starts with 10 spaces",
                movie.isScene("hello there"), CoreMatchers.equalTo(false));
    }

    @Test
    public void addLineTest() {
        movie.addLine("INT. LUKE'S X-WING - FIGHTER");
        assertThat("Should add a scene to list if scene pattern is in given line",
                movie.getSceneList().size(), CoreMatchers.equalTo(1));

        assertThat("The last scene added shoud be the correct name 1",
                movie.getLastScene().getName(), CoreMatchers.equalTo("LUKE'S X-WING"));

        movie.addLine("INT./EXT. DARTH VADER FIRST APPEAR - LUKE'S X-WING - FIGHTER");
        assertThat("The last scene added shoud be the correct name 2",
                movie.getLastScene().getName(), CoreMatchers.equalTo("DARTH VADER FIRST APPEAR"));

        movie.addLine("                      THREEPIO");
        assertThat("Should add a character to last scene if character pattern is in given line",
                movie.getLastScene().getCharacters().size(), CoreMatchers.equalTo(1));

        movie.addLine("          hello");
        assertThat("Shoud have a WordCount with hello word on last Character of last Scene",
                movie.getLastScene().getLastCharacter().retrieveWord("hello"),
                CoreMatchers.instanceOf(WordCount.class));
    }

    @Test
    public void addLineWordTest() {
        movie.addLine("INT. LUKE'S X-WING - FIGHTER");
        movie.addLine("                      THREEPIO");
        movie.addLine("          i'm here I'm Here threepio");
        movie.addLine("              i'm");

        assertThat("Shoud have a WordCount with i'm word and count 2 on last Character of last Scene",
                movie.getLastScene().getLastCharacter().retrieveWord("i'm").getCount(),
                CoreMatchers.equalTo(2L));

        assertThat("Shoud have a WordCount with here word and count 2 on last Character of last Scene",
                movie.getLastScene().getLastCharacter().retrieveWord("here").getCount(),
                CoreMatchers.equalTo(2L));

        assertThat("Shoud have a WordCount with threepio word and count 2 on last Character of last Scene",
                movie.getLastScene().getLastCharacter().retrieveWord("threepio").getCount(),
                CoreMatchers.equalTo(1L));

        assertThat("Shoud not have a word r2",
                movie.getLastScene().getLastCharacter().retrieveWord("hello"),
                CoreMatchers.equalTo(null));
    }

    @Test
    public void addLineWordTest2() {
        movie.addLine("INT. LUKE'S X-WING - FIGHTER");
        movie.addLine("                      THREEPIO");
        movie.addLine("          I see, sir");
        movie.addLine("                      LUKE");
        movie.addLine("          Uh, you can call me Luke.");
        movie.addLine("                      THREEPIO");
        movie.addLine("          I see, sir Luke");
        movie.addLine("                      LUKE");
        movie.addLine("               (laughing)");
        movie.addLine("          Just Luke");

        MovieCharacter threepio = movie.retrieveCharacterByName("THREEPIO").get();
        MovieCharacter luke = movie.retrieveCharacterByName("LUKE").get();

        assertThat("Movie should have 2 characters",
                movie.getCharacters().size(), CoreMatchers.equalTo(2));
        assertThat("Movie should have a threepio character",
                movie.retrieveCharacterByName("THREEPIO").get().getName(), CoreMatchers.equalTo("THREEPIO"));

        assertThat("Movie should have a luke character",
                movie.retrieveCharacterByName("LUKE").get().getName(), CoreMatchers.equalTo("LUKE"));

        assertThat("Character THREEPIO should have 2 i words",
                threepio.retrieveWord("i").getCount(), CoreMatchers.equalTo(2L));

        assertThat("Character THREEPIO should have 2 see words",
                threepio.retrieveWord("see").getCount(), CoreMatchers.equalTo(2L));

        assertThat("Character THREEPIO should have 2 sir words",
                threepio.retrieveWord("sir").getCount(), CoreMatchers.equalTo(2L));

        assertThat("Character THREEPIO should have 1 luke words",
                threepio.retrieveWord("luke").getCount(), CoreMatchers.equalTo(1L));


        assertThat("Character LUKE should have 2 luke words",
                luke.retrieveWord("luke").getCount(), CoreMatchers.equalTo(2L));

        assertThat("Character LUKE should have 1 luke words",
                luke.retrieveWord("uh").getCount(), CoreMatchers.equalTo(1L));

        assertThat("Character LUKE should have 1 luke words",
                luke.retrieveWord("you").getCount(), CoreMatchers.equalTo(1L));

        assertThat("Character LUKE should have 1 luke words",
                luke.retrieveWord("can").getCount(), CoreMatchers.equalTo(1L));

        assertThat("Character LUKE should have 1 luke words",
                luke.retrieveWord("call").getCount(), CoreMatchers.equalTo(1L));

        assertThat("Character LUKE should have 1 luke words",
                luke.retrieveWord("me").getCount(), CoreMatchers.equalTo(1L));

        assertThat("Character LUKE should have 1 luke words",
                luke.retrieveWord("just").getCount(), CoreMatchers.equalTo(1L));


    }


    @Test
    public void addLineWordTest3() {
        movie.addLine("INT. LUKE'S X-WING - FIGHTER");
        movie.addLine("                      THREEPIO");
        movie.addLine("          Artoo-Detoo! It's you! It's you!");

        MovieCharacter threepio = movie.retrieveCharacterByName("THREEPIO").get();

        assertThat("Character THREEPIO should have 2 it's words",
                threepio.retrieveWord("it's").getCount(), CoreMatchers.equalTo(2L));

        assertThat("Character THREEPIO should have 2 you words",
                threepio.retrieveWord("you").getCount(), CoreMatchers.equalTo(2L));

        assertThat("Character THREEPIO should have 1 artoo words",
                threepio.retrieveWord("artoo").getCount(), CoreMatchers.equalTo(1L));

        assertThat("Character THREEPIO should have 1 detoo word",
                threepio.retrieveWord("detoo").getCount(), CoreMatchers.equalTo(1L));
    }

}
