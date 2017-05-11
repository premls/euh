package mypoc.euh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lamp")
public class LampEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5422186499157642733L;

	
	public LampEntity() {

	}
	
	public LampEntity(String code, long gatewayId) {
		this.code = code;
		this.gatewayId = gatewayId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//TODO Set sequence so that it doesn't start with "0"
	private Long Id;
	
	@Column(name = "code", nullable = false, length=10, unique=true)
	private String code;
	
	@Column(name = "gateway_id")
	private Long gatewayId;
	
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(Long gatewayId) {
		this.gatewayId = gatewayId;
	}

}
