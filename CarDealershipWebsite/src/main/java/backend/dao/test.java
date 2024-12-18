package backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.testdriveEntity;

public interface test extends JpaRepository<testdriveEntity, String> {
	testdriveEntity findByEmail(String email);
}
