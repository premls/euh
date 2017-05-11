package mypoc.euh.service;

import java.util.List;

import mypoc.euh.entity.GatewayEntity;
import mypoc.euh.exception.BaseException;

public interface GatewayService {

	void create(GatewayEntity gateway) throws BaseException;

	void update(GatewayEntity lamp) throws BaseException;

	List<GatewayEntity> getAll();

	GatewayEntity getLampDetails(Long id) throws BaseException;

}
