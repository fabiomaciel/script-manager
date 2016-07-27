package com.fabiomsouza.scriptmanager.controller;

import com.fabiomsouza.scriptmanager.DefaultResponse;
import com.fabiomsouza.scriptmanager.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/script")
public class ScriptController {

	@Autowired
	ScriptService service;


	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody DefaultResponse add(@RequestBody String script){
		if(service.add(script))
			return new DefaultResponse("Movie Script successfully received");

		return new DefaultResponse("Movie Script already received");
	}


	@ExceptionHandler(Exception.class)
	public DefaultResponse except(Exception e){
		return new DefaultResponse("Unexpected error");
	}

}
