package com.ideas2it.EmployeeManagementSystem.dao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.EmployeeManagementSystem.model.Employee;


@Repository
public interface EmployeeManagementRepo extends JpaRepository<Employee, Integer> {
 
	@Query("FROM Employee e where e.firstName Like %:name%")
	public List<Employee> findByName(@Param("name") String name);
}
