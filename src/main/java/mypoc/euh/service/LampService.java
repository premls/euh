package mypoc.euh.service;

import java.util.List;

import mypoc.euh.entity.LampEntity;
import mypoc.euh.exception.BaseException;

public interface LampService {

	void create(LampEntity lamp) throws BaseException;

	List<LampEntity> getAll();

	void update(LampEntity lamp) throws BaseException;

	LampEntity getLampDetails(Long id) throws BaseException;
}
