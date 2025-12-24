package com.learning.spring.repository;

import com.learning.spring.entity.EmployeeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    boolean existsByEmail(String email);

    EmployeeEntity findByEmail(String email);

    EmployeeEntity getEmployeeByNameAndEmail(String name, String email);

    @Query("SELECT E FROM EmployeeEntity E WHERE E.name = :name OR E.age = :age")
    List<EmployeeEntity> getEmployeeByNameOrAge(String name, Integer age);

    /*List<EmployeeEntity> findByFirstNameContainingIgnoreCase(String firstName);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId AND e.status = :status")
    List<EmployeeEntity> findActiveEmployeesByDepartment(@Param("deptId") Long deptId
                                                   /*@Param("status") EmployeeStatus status);

    @Query(value = "SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :deptId",
            nativeQuery = true)
    BigDecimal findAverageSalaryByDepartment(@Param("deptId") Long deptId);

    @Query("SELECT e.department.name, COUNT(e) FROM Employee e GROUP BY e.department.name")
    List<Object[]> getEmployeeCountByDepartment();

    // Pagination & Sorting
    //Page<Employee> findByStatus(EmployeeStatus status, Pageable pageable);

    // Soft delete support
    @Modifying
    @Query("UPDATE Employee e SET e.deleted = true WHERE e.id = :id")
    void softDeleteById(@Param("id") Long id);

    */
}
