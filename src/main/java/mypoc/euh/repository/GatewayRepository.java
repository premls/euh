package mypoc.euh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypoc.euh.entity.GatewayEntity;

public interface GatewayRepository extends JpaRepository<GatewayEntity, Long> {
	

}
