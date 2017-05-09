package mypoc.euh.service;

import mypoc.euh.exception.BaseException;
import mypoc.euh.io.CreateLampDTO;

public interface LampService {

	void create(CreateLampDTO lamp) throws BaseException;

	void getAll();
}
