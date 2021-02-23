package com.mcb.crudrestapi.service;

import com.mcb.crudrestapi.entity.*;
import com.mcb.crudrestapi.repository.StudentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentInfoService {
    @Autowired
    StudentInfoRepository studentInfoRepository;

    @Autowired
    CourseService courseService;

    public StudentInfoService(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    /**
     * Method to add a new student
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity addNewStudent(StudentEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        if (studentInfoRepository.existsById(pEntity.getId())) {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("This id already has a record, please try with different id");
            return response;
        }
        StudentEntity resp = studentInfoRepository.save(pEntity);
        if (resp.getId() > 0) {
            response.setResult(resp);
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
        } else {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("Failed to add user");
        }
        return response;
    }

    /**
     * Method to update student
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity updateStudent(StudentEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        CommonResponseEntity commonResp = findStudentById(pEntity.getId());
        Optional<StudentEntity> optionalEntity = (Optional<StudentEntity>) commonResp.getResult();
        if (optionalEntity.isPresent()) {
            StudentEntity resp = studentInfoRepository.save(pEntity);
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
     * Method to get student details by id
     *
     * @param pStudentId
     * @return
     */
    public CommonResponseEntity findStudentById(int pStudentId) {
        CommonResponseEntity response = new CommonResponseEntity();
        Optional<StudentEntity> resp = studentInfoRepository.findById(pStudentId);
        response.setResult(resp);
        if (resp.isPresent()) {
            response.setMessage("");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("Student record not found for the given id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Method to get all students records
     *
     * @return
     */
    public CommonResponseEntity getAllStudentsRecords() {
        CommonResponseEntity response = new CommonResponseEntity();
        List<StudentEntity> resp = (List<StudentEntity>) studentInfoRepository.findAll();
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
     * Method to delete a student record
     *
     * @param pStudentId
     * @return
     */
    public CommonResponseEntity deleteStudentRecord(int pStudentId) {
        CommonResponseEntity response = new CommonResponseEntity();
        try {
            studentInfoRepository.deleteById(pStudentId);
            if (pStudentId > 0) {
                response.setMessage("Record(s) deleted successfully");
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage("Student record not found for the given id");
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error occurred while deleting the record, please check the student id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }
    /**
     * get Course List By Student Id
     *
     * @param pStudentId
     * @return
     *
     */
    public CommonResponseEntity getCourseRecordsByStudentID(int pStudentId) {
        CommonResponseEntity response = new CommonResponseEntity();
        List<CourseEntity> courseList = new ArrayList<>();
        Optional<StudentEntity> resp = studentInfoRepository.findById(pStudentId);
        if (resp.isPresent()) {
            response.setResult(resp.get().getCourses());
            response.setMessage("");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Student record not found for the given id");
        }

        return response;
    }


    /**
     * Method to calculate total duration by student id
     *
     * @param pStudentId
     * @return
     */
    public Object calculateDurationByStudentId(int pStudentId) {
        CommonResponseEntity response = new CommonResponseEntity();
        List<CourseEntity> courseList = new ArrayList<>();
        Optional<StudentEntity> resp = studentInfoRepository.findById(pStudentId);
        if (resp.isPresent()) {
            if (resp.get() != null) {
                Set<CourseEntity> courseEntities = resp.get().getCourses();
                int duration = 0;
                for(CourseEntity course: courseEntities) {
                    duration += course.getDuration();
                }
                return duration;
            } else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Student record not found for the given id");
            }
        }

        return response;
    }
}
