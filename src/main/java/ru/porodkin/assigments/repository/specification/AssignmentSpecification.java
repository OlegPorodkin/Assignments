package ru.porodkin.assigments.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.porodkin.assigments.domain.SemesterAssignment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AssignmentSpecification implements Specification<SemesterAssignment> {

    @Override
    public Predicate toPredicate(Root<SemesterAssignment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
