package com.fabiomsouza.scriptmanager.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class MovieCharacterTest {

	@Test
	public void newCharacterTest(){

		MovieCharacter movieCharacter = new MovieCharacter("                      THREEPIO");
		assertThat("The character should have name and name should be a String",
				movieCharacter.getName(), CoreMatchers.instanceOf(String.class));

		assertThat("The character should have wordCounts and name should be a List",
				movieCharacter.getWordCounts(), CoreMatchers.instanceOf(List.class));

	}

	@Test
	public void characterNameTest(){
		MovieCharacter movieCharacter = new MovieCharacter("                      THREEPIO");

		assertThat("The name should be THREEPIO",
				movieCharacter.getName(), CoreMatchers.equalTo("THREEPIO"));

		MovieCharacter movieCharacter2 = new MovieCharacter("                      LUKE");
		assertThat("The name should be the string given on contructor",
				movieCharacter2.getName(), CoreMatchers.equalTo("LUKE"));
	}

	@Test
	public void characterWordCountTest(){
		MovieCharacter movieCharacter = new MovieCharacter("THREEPIO");
		movieCharacter.addWord("hello");

		assertThat("The character should have a list of wordCount with size 1",
				movieCharacter.getWordCounts().size(), CoreMatchers.equalTo(1));

		movieCharacter.addWord("hello");
		assertThat("Words repeated should not be added",
				movieCharacter.getWordCounts().size(), CoreMatchers.equalTo(1));

		movieCharacter.addWord("hi");
		assertThat("The character should have a list of wordCount with size 2",
				movieCharacter.getWordCounts().size(), CoreMatchers.equalTo(2));

	}

	@Test
	public void characterWordCountParentTest(){
		MovieCharacter movieCharacter = new MovieCharacter("THREEPIO");
		movieCharacter.addWord("hello");

		assertThat("The wordCount added should have character field with given character",
				movieCharacter.retrieveWord("hello").getCharacter(), CoreMatchers.equalTo(movieCharacter));
	}

	@Test
	public void characterWordCountNameTest(){
		MovieCharacter movieCharacter = new MovieCharacter("THREEPIO");
		movieCharacter.addWord("hello");
		assertThat("Should retrieve a wordCount Objet by given word",
				movieCharacter.retrieveWord("hello"), CoreMatchers.instanceOf(WordCount.class));

		assertThat("Should retrieve a null object if the word is not in wordCountList",
				movieCharacter.retrieveWord("hi"), CoreMatchers.equalTo(null));

	}

	@Test
	public void characterWordCountCountTest(){
		MovieCharacter movieCharacter = new MovieCharacter("THREEPIO");
		movieCharacter.addWord("hello");
		movieCharacter.addWord("hello");

		assertThat("Should retrieve a wordCount Objet by given word with count 2 when add 2 times same word",
				movieCharacter.retrieveWord("hello").getCount(), CoreMatchers.equalTo(2L));

		movieCharacter.addWord("hello");
		movieCharacter.addWord("hello");
		movieCharacter.addWord("hello");
		assertThat("Should retrieve a wordCount Objet by given word with count 5 when add 5 times same word",
				movieCharacter.retrieveWord("hello").getCount(), CoreMatchers.equalTo(5L));
	}



}
