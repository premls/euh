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
import mypoc.euh.entity.GatewayEntity;
import mypoc.euh.exception.BaseException;
import mypoc.euh.service.GatewayService;

@RestController
@RequestMapping(path = "/api/gateway/")
public class GatewayController {



	private @Autowired GatewayService gatewayService;
	
	@ApiOperation(value = "Add new gateway")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create( @RequestBody GatewayEntity gateway) throws BaseException {
		gatewayService.create(gateway);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update gateway details")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody GatewayEntity lamp) throws BaseException {
		gatewayService.update(lamp);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get all lamps")
	@RequestMapping(method = RequestMethod.GET)
	//TODO : Implement pagination
	public ResponseEntity<List<GatewayEntity>> getAll() {
		return new ResponseEntity<List<GatewayEntity>>(gatewayService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get all lamps")
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	public ResponseEntity<GatewayEntity> getLampDetails(@PathVariable("id") Long id) throws BaseException {
		return new ResponseEntity<GatewayEntity>(gatewayService.getLampDetails(id), HttpStatus.OK);
	}

	
}
