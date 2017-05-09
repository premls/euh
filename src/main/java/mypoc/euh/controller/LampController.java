package mypoc.euh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import mypoc.euh.exception.BaseException;
import mypoc.euh.io.CreateLampDTO;
import mypoc.euh.service.LampService;

@RestController
@RequestMapping(path = "/api/lamp")
public class LampController {

	private @Autowired LampService lampManager;
	
	@ApiOperation(value = "Add new lamp")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create( @RequestBody CreateLampDTO lamp) throws BaseException {
		lampManager.create(lamp);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update lamp details")
	@RequestMapping(method = RequestMethod.PATCH)
	public void update() {
		
	}
	
	@ApiOperation(value = "Get all lamps")
	@RequestMapping(method = RequestMethod.GET)
	public void getAll() {
		lampManager.getAll();
	}
	
}
