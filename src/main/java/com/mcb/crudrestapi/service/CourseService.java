package com.mcb.crudrestapi.service;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.CourseEntity;
import com.mcb.crudrestapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Method to add a new course
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity addNewCourse(CourseEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        if (courseRepository.existsById(pEntity.getId())) {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("This id already has a record, please try with different id");
            return response;
        }
        CourseEntity resp = courseRepository.save(pEntity);
        if (resp.getId() > 0) {
            response.setResult(resp);
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
        } else {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("Failed to add a course");
        }
        return response;
    }

    /**
     * Method to update a course
     *
     * @param pEntity
     * @return
     */
    public CommonResponseEntity updateCourse(CourseEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        CommonResponseEntity commonResp = findCourseById(pEntity.getId());
        Optional<CourseEntity> optionalEntity = (Optional<CourseEntity>) commonResp.getResult();
        if (optionalEntity.isPresent()) {
            CourseEntity resp = courseRepository.save(pEntity);
            response.setResult(resp);
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
        } else {
            response.setStatus(HttpStatus.NOT_MODIFIED);
            response.setMessage(commonResp.getMessage());
        }
        return response;
    }

    /**
     * Method to get a course by id
     *
     * @param pCourseId
     * @return
     */
    public CommonResponseEntity findCourseById(int pCourseId) {
        CommonResponseEntity response = new CommonResponseEntity();
        Optional<CourseEntity> resp = courseRepository.findById(pCourseId);
        response.setResult(resp);
        if (resp.isPresent()) {
            response.setMessage("");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("Course record not found for the given id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Method to get all course records
     *
     * @return
     */
    public CommonResponseEntity getAllCourseRecords() {
        CommonResponseEntity response = new CommonResponseEntity();
        List<CourseEntity> resp = (List<CourseEntity>) courseRepository.findAll();
        if (resp.size() > 0) {
            response.setResult(resp);
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("No records found");
        }
        return response;
    }

    /**
     * Method to delete a course
     *
     * @param pCourseId
     * @return
     */
    public CommonResponseEntity deleteCourseRecord(int pCourseId) {
        CommonResponseEntity response = new CommonResponseEntity();
        try {
            courseRepository.deleteById(pCourseId);
            if (pCourseId > 0) {
                response.setMessage("Record(s) deleted successfully");
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage("Course record not found for the given id");
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error occurred while deleting the record, please check the course id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
