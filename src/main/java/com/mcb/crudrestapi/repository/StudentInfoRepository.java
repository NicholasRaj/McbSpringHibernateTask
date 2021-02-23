package com.mcb.crudrestapi.repository;

import com.mcb.crudrestapi.entity.CourseEntity;
import com.mcb.crudrestapi.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentInfoRepository extends CrudRepository<StudentEntity, Integer> {
    @Query( value = "select cs.student_id from tbl_course_student cs where cs.course_id = :courseId", nativeQuery = true)
    public List<Integer> getStudentIdByCourseId(@Param("courseId") int courseId);

}
