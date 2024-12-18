package backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.customcar;

public interface custom extends JpaRepository<customcar, String> {
	customcar findByName(String name);
	customcar findByEmail(String email);
}
