package ru.porodkin.assigments.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.porodkin.assigments.domain.SemesterAssignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<SemesterAssignment, Long>, JpaSpecificationExecutor<SemesterAssignment> {

    @EntityGraph(value = "Assignment.all")
    @Override
    List<SemesterAssignment> findAll();
}
