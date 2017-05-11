package mypoc.euh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mypoc.euh.entity.GatewayEntity;
import mypoc.euh.exception.BaseException;
import mypoc.euh.repository.GatewayRepository;

@Service
public class GatewayServiceImpl implements GatewayService {

	private GatewayRepository gatewayRepository ;
	
	@Override
	public void create(GatewayEntity gateway) throws BaseException {
		validate(gateway);
		gatewayRepository.save(gateway);
	}

	private void validate(GatewayEntity gateway) throws BaseException {
		if(gateway == null) {
			throw new BaseException("Invalid request");
		} else if(StringUtils.isEmpty(gateway.getCode())) {
			throw new BaseException("Invalid code");
		}
	}

	@Override
	public void update(GatewayEntity gateway) throws BaseException {
		GatewayEntity dbGateway = getLampDetails(gateway.getId());
		gateway.setCreated(dbGateway.getCreated());
		gatewayRepository.save(gateway);
	}

	@Override
	public List<GatewayEntity> getAll() {
		return gatewayRepository.findAll();
	}

	@Override
	public GatewayEntity getLampDetails(Long id) throws BaseException {
		GatewayEntity gateway = gatewayRepository.findOne(id);
		if(gateway == null) {
			throw new BaseException("Gateway not found");
		}
		return gateway;
	}

}
