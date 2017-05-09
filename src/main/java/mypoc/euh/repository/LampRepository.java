package mypoc.euh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypoc.euh.entity.LampEntity;

public interface LampRepository extends JpaRepository<LampEntity, Long> {

}
