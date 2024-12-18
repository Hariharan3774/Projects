package backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.addcar;

public interface car extends JpaRepository<addcar, Integer> {
	addcar findByCarid(int carid);
}
