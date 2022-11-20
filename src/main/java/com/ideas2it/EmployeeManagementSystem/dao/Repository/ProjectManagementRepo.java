package com.ideas2it.EmployeeManagementSystem.dao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.EmployeeManagementSystem.model.Project;

@Repository
public interface ProjectManagementRepo extends JpaRepository<Project, Integer> {

	@Query("FROM Project e where e.projectName Like %:name%")
	public List<Project> findByName(@Param("name") String name);
}
