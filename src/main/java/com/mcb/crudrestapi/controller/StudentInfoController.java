package com.mcb.crudrestapi.controller;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.CourseEntity;
import com.mcb.crudrestapi.entity.StudentEntity;
import com.mcb.crudrestapi.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentInfoController {

    @Autowired
    StudentInfoService studentInfoService;

    public StudentInfoController(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    /**
     * Method to add student
     * @author Nick
     */
    @PostMapping("/addStudent")
    public CommonResponseEntity addNewStudent(@RequestBody StudentEntity pEntity) {
        CommonResponseEntity resp = studentInfoService.addNewStudent(pEntity);
        return resp;
    }

    /**
     * Method to update student
     * @author Nick
     */
    @PostMapping("/updateStudent")
    public CommonResponseEntity updateStudent(@RequestBody StudentEntity pEntity) {
        CommonResponseEntity resp = studentInfoService.updateStudent(pEntity);
        return resp;
    }

    /**
     * Method to find student by id
     *
     * @param pStudentId
     * @return
     */
    @RequestMapping(value = "/findStudent/{id}", method = RequestMethod.POST)
    public CommonResponseEntity findStudentById(@PathVariable("id") int pStudentId) {
        CommonResponseEntity resp = studentInfoService.findStudentById(pStudentId);
        return resp;
    }

    /**
     * Method to get all students records
     *
     * @return
     */
    @PostMapping("/getAllStudents")
    public CommonResponseEntity getAllStudentsRecords() {
        CommonResponseEntity resp = studentInfoService.getAllStudentsRecords();
        return resp;
    }

    /**
     * Method to delete a student record
     *
     * @param pStudentId
     * @return
     */
    @DeleteMapping(value = "deleteStudent/{studentId}")
    public CommonResponseEntity deleteStudentRecord(@PathVariable("studentId") int pStudentId) {
        CommonResponseEntity resp = studentInfoService.deleteStudentRecord(pStudentId);
        return  resp;
    }

    /**
     * get Course List By Student Id
     *
     * @param pStudentId
     * @return
     *
     */
    @RequestMapping(value = "/getCourseList/{studentId}", method = RequestMethod.POST)
    public CommonResponseEntity getCourseRecordsByStudentId(@PathVariable("studentId") int pStudentId) {
        CommonResponseEntity response = studentInfoService.getCourseRecordsByStudentID(pStudentId);
        return  response;
    }


    /**
     * Method to calculate total duration by student id
     *
     * @param pStudentId
     * @return
     */
    @RequestMapping(value = "/calculateDuration/{studentId}", method = RequestMethod.POST)
    public Object calculateDurationByStudentId(@PathVariable("studentId") int pStudentId) {
        return studentInfoService.calculateDurationByStudentId(pStudentId);
    }

}
