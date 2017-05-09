package mypoc.euh.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypoc.euh.entity.LampEntity;
import mypoc.euh.exception.BaseException;
import mypoc.euh.io.CreateLampDTO;
import mypoc.euh.repository.LampRepository;

@Service
public class LampServiceImpl implements LampService {

	private @Autowired Logger logger;
	private @Autowired LampRepository lampRepository;
	
	@Override
	public void create(CreateLampDTO lamp) throws BaseException {
		lamp.validate();
		try{
			LampEntity lampEntity = new LampEntity();
			BeanUtils.copyProperties(lamp, lampEntity);
			lampRepository.save(lampEntity);
		} catch (Exception e) {
			logger.error(("Error while creating lamp - " + lamp.toString()),e);
			throw new BaseException("Unkown error");
		}
	}

	@Override
	public void getAll() {
		List<LampEntity> allLamps = lampRepository.findAll();
		
		System.out.println();
	}

}
