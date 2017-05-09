package mypoc.euh.io;

import org.springframework.util.StringUtils;

import mypoc.euh.exception.BaseException;

// TODO :Extending class needs to be revisited
public class CreateLampDTO extends LampDTO { 

	public CreateLampDTO() {
		super();
	}
	
	
	public CreateLampDTO(String code, String state, long gatewayId) {
		super(code, state, gatewayId);
	}
	
	public void validate() throws BaseException  {
		if(StringUtils.isEmpty(getCode())) {
			throw new BaseException("Invalid code");
		} else if(getCode().length() > 10) {
			throw new BaseException("Code length should be less than 10 chars");
		} else if(StringUtils.isEmpty(getState())) {
			throw new BaseException("Invalid state");
		} 
	}
	
}	
	