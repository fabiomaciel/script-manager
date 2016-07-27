package com.fabiomsouza.scriptmanager.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class WordCountTest {

	@Test
	public void newWordCountTest(){
		WordCount wordCount = new WordCount("hello");
		assertThat("The wordCount should have word and word should be a String",
				wordCount.getWord(), CoreMatchers.instanceOf(String.class));

		assertThat("The word should be hello",
				wordCount.getWord(), CoreMatchers.equalTo("hello"));

	}

	@Test
	public void WordCountParentTest(){
		WordCount wordCount = new WordCount("hello");
		wordCount.setCharacter(new MovieCharacter("THREEPIO"));
		assertThat("The wordCount should have character and character should be a Character",
				wordCount.getCharacter(), CoreMatchers.instanceOf(MovieCharacter.class));
	}

	@Test
	public void WordCountGetCountTest(){
		WordCount wordCount = new WordCount("hello");
		wordCount.addCount();
		assertThat("Parameter count should be 2",
				wordCount.getCount(), CoreMatchers.equalTo(2L));

		wordCount.addCount();
		wordCount.addCount();
		assertThat("Parameter count should be 4",
				wordCount.getCount(), CoreMatchers.equalTo(4L));

	}


}
