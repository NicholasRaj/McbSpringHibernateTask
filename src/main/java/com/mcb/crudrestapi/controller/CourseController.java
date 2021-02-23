package com.mcb.crudrestapi.controller;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.CourseEntity;
import com.mcb.crudrestapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Method to add a new course
     *
     * @param pEntity
     * @return
     */
    @PostMapping("/addCourse")
    public CommonResponseEntity addNewCourse(@RequestBody CourseEntity pEntity) {
        CommonResponseEntity resp = courseService.addNewCourse(pEntity);
        return resp;
    }

    /**
     * Method to update a course
     *
     * @param pEntity
     * @return
     */
    @PostMapping("/updateCourse")
    public CommonResponseEntity updateCourse(@RequestBody CourseEntity pEntity) {
        CommonResponseEntity resp = courseService.updateCourse(pEntity);
        return resp;
    }

    /**
     * Method to find a course by id
     *
     * @param pCourseId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CommonResponseEntity findCourseById(@PathVariable("id") int pCourseId) {
        CommonResponseEntity resp = courseService.findCourseById(pCourseId);
        return resp;
    }

    /**
     * Method to get all course records
     *
     * @return
     */
    @PostMapping("/getAllCourses")
    public CommonResponseEntity getAllCourseRecords() {
        CommonResponseEntity resp = courseService.getAllCourseRecords();
        return resp;
    }

    /**
     * Method to delete a course
     *
     * @param pCourseId
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public CommonResponseEntity deleteCourseRecord(@PathVariable("id") int pCourseId) {
        CommonResponseEntity resp = courseService.deleteCourseRecord(pCourseId);
        return  resp;
    }
}
