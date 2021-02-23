package com.mcb.crudrestapi.repository;

import com.mcb.crudrestapi.entity.InstructorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends CrudRepository<InstructorEntity, Integer> { }
