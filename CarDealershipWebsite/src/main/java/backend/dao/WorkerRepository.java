package backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
	Worker findById(int id);
}
