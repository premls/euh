package mypoc.euh.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mypoc.euh.entity.LampEntity;
import mypoc.euh.exception.BaseException;
import mypoc.euh.repository.LampRepository;

@Service
public class LampServiceImpl implements LampService {

	private @Autowired LampRepository lampRepository;
	
	@Override
	public void create(LampEntity lamp) throws BaseException {
		validate(lamp);
		LampEntity lampEntity = new LampEntity();
		BeanUtils.copyProperties(lamp, lampEntity);
		lampRepository.save(lampEntity);
	}

	@Override
	public List<LampEntity> getAll() {
		return lampRepository.findAll();
	}

	private void validate(LampEntity lamp) throws BaseException  {
		if(StringUtils.isEmpty(lamp.getCode())) {
			throw new BaseException("Invalid code");
		} else if(lamp.getCode().length() > 10) {
			throw new BaseException("Code length should be less than 10 chars");
		}
	}

	@Override
	public void update(LampEntity lamp) throws BaseException {
		LampEntity dbLamp = getLampDetails(lamp.getId());
		lamp.setCreated(dbLamp.getCreated());
		lampRepository.save(lamp);
		
	}

	@Override
	public LampEntity getLampDetails(Long id) throws BaseException {
		LampEntity lamp = lampRepository.findOne(id);
		if(lamp == null) {
			throw new BaseException("Lamp does not exists id= "+id);
		}
		return lamp;
	}

	
}
