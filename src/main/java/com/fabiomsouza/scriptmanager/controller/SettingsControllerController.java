package com.fabiomsouza.scriptmanager.controller;

import com.fabiomsouza.scriptmanager.DefaultResponse;
import com.fabiomsouza.scriptmanager.domain.Scene;
import com.fabiomsouza.scriptmanager.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/settings")
public class SettingsControllerController {

	@Autowired
	SettingsService service;



	@RequestMapping
	public List<Scene> getAll(){
		return service.getAllSettings();
	}


	@RequestMapping("/{id}")
	public Scene get(@PathVariable Long id){
		return service.getSetting(id);
	}

    @ExceptionHandler(Exception.class)
    public DefaultResponse except(Exception e){
        return new DefaultResponse("Unexpected error");
    }


}
