package com.fabiomsouza.scriptmanager.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class SceneTest {

	Scene scene;

	@Before
	public void createSceneInstance(){
		scene = new Scene();
	}

	@Test
	public void newSceneTest(){
		Scene scene = new Scene("LUKE'S SPEEDER");
		assertThat("The scene name should be a String",
				scene.getName(), CoreMatchers.instanceOf(String.class));
		assertThat("Scene should have a chracterList property",
				scene.getCharacters(), CoreMatchers.anything());
	}

	@Test
	public void SceneNameTest(){
		Scene scene1 = new Scene("LUKE'S SPEEDER");

		assertThat("The scene name should return LUKE'S SPEEDER.",
				scene1.getName(), CoreMatchers.equalTo("LUKE'S SPEEDER"));

		Scene scene2 = new Scene("LUKE'S X-WING FIGHTER");
		assertThat("The scene name should only consider the first setting name and ignore the prefix.",
				scene2.getName(), CoreMatchers.equalTo("LUKE'S X-WING FIGHTER"));


		Scene scene3 = new Scene("LUKE'S X-WING FIGHTER");
		assertThat("The scene name should consider the unique setting name and ignore prefix",
				scene3.getName(), CoreMatchers.equalTo("LUKE'S X-WING FIGHTER"));

	}

	@Test
	public void lastCharacterTest(){
		String name = "THREEPIO";
		scene.addCharacter(new MovieCharacter(name));
		assertThat("Should be able to get the last added character 1",
			scene.getLastCharacter().getName(), CoreMatchers.equalTo(name));

		String name2 = "LUKE";
		scene.addCharacter(new MovieCharacter(name2));
		assertThat("Should be able to get the last added character 2",
				scene.getLastCharacter().getName(), CoreMatchers.equalTo(name2));
	}

}
