package com.mcb.crudrestapi.repository;

import com.mcb.crudrestapi.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> { }
