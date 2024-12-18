package backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.userdata;

public interface user extends JpaRepository<userdata, String> {
	userdata findByEmail(String email);

}
