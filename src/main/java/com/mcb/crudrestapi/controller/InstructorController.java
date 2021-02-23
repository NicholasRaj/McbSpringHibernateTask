package com.mcb.crudrestapi.controller;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.InstructorEntity;
import com.mcb.crudrestapi.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     * Method to add a instructor
     *
     * @param pEntity
     * @return
     */
    @PostMapping("/addInstructor")
    public CommonResponseEntity addNewInstructor(@RequestBody InstructorEntity pEntity) {
        CommonResponseEntity resp = instructorService.addNewInstructor(pEntity);
        return resp;
    }

    /**
     * Method to update instructor
     *
     * @param pEntity
     * @return
     */
    @PostMapping("/updateInstructor")
    public CommonResponseEntity updateInstructor(@RequestBody InstructorEntity pEntity) {
        CommonResponseEntity resp = instructorService.updateInstructor(pEntity);
        return resp;
    }

    /**
     * Method to find instructor by id
     *
     * @param pInstructorId
     * @return
     */
    @RequestMapping(value = "findInstructor/{id}", method = RequestMethod.POST)
    public CommonResponseEntity findInstructorById(@PathVariable("id") int pInstructorId) {
        CommonResponseEntity resp = instructorService.findInstructorById(pInstructorId);
        return resp;
    }

    /**
     * Method to get all instructor records
     *
     * @return
     */
    @PostMapping("/getAllInstructors")
    public CommonResponseEntity getAllInstructorsRecords() {
        CommonResponseEntity resp = instructorService.getAllInstructorsRecords();
        return resp;
    }

    /**
     * Method to delete a instructor record
     *
     * @param pInstructorId
     * @return
     */
    @DeleteMapping(value = "deleteInstructor/{id}")
    public CommonResponseEntity deleteInstructorRecord(@PathVariable("id") int pInstructorId) {
        CommonResponseEntity resp = instructorService.deleteInstructorRecord(pInstructorId);
        return  resp;
    }
    /**
     * Method to get list of student with instructor id
     *
     * @param pInstructorId
     * @return
     */
    @RequestMapping(value = "findStudents/{instructorId}", method = RequestMethod.POST)
    public CommonResponseEntity findStudentByInstructorId(@PathVariable("instructorId") int pInstructorId) {
        CommonResponseEntity resp = instructorService.findStudentByInstructorId(pInstructorId);
        return resp;
    }

}
