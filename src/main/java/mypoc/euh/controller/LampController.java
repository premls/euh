package mypoc.euh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import mypoc.euh.entity.LampEntity;
import mypoc.euh.exception.BaseException;
import mypoc.euh.service.LampService;

@RestController
@RequestMapping(path = "/api/lamp")
public class LampController {

	private @Autowired LampService lampManager;
	
	@ApiOperation(value = "Add new lamp")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create( @RequestBody LampEntity lamp) throws BaseException {
		lampManager.create(lamp);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update complete lamp details")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody LampEntity lamp) throws BaseException {
		lampManager.update(lamp);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get all lamps")
	@RequestMapping(method = RequestMethod.GET)
	//TODO : Implement pagination
	public ResponseEntity<List<LampEntity>> getAll() {
		return new ResponseEntity<List<LampEntity>>(lampManager.getAll(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Get all lamps")
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	public ResponseEntity<LampEntity> getLampDetails(@PathVariable("id") Long id) throws BaseException {
		return new ResponseEntity<LampEntity>(lampManager.getLampDetails(id), HttpStatus.OK);
	}
}
