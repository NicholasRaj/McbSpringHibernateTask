package com.mcb.crudrestapi.repository;

import com.mcb.crudrestapi.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Integer> { }
