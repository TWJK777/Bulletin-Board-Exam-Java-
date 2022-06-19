package com.john.belt_exam.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.john.belt_exam.models.Television;


@Repository
public interface TelevisionRepository extends CrudRepository<Television, Long> {
	List<Television> findAll();
}
