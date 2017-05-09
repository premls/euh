package mypoc.euh.io;

import io.swagger.annotations.ApiParam;
import mypoc.euh.entity.BaseEntity;

public class LampDTO extends BaseEntity {

	private String code;
	@ApiParam(value="State of Lamp ON/OFF,UK" , allowableValues = "ON,OFF,UK")
	private String state;
	private Long gatewayId;
	
	public LampDTO(String code, String state, long gatewayId) {
		this.code = code;
		this.state = state;
		this.gatewayId = gatewayId;
	}
	
	public LampDTO() {

	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(Long gatewayId) {
		this.gatewayId = gatewayId;
	}
	

}
