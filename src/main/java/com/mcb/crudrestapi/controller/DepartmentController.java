package com.mcb.crudrestapi.controller;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.DepartmentEntity;
import com.mcb.crudrestapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Method to add a new department
     *
     * @param pEntity
     * @return
     */
    @PostMapping("/addDepartment")
    public CommonResponseEntity addNewDepartment(@RequestBody DepartmentEntity pEntity) {
        CommonResponseEntity resp = departmentService.addNewDepartment(pEntity);
        return resp;
    }

    /**
     * Method to update department
     *
     * @param pEntity
     * @return
     */
    @PostMapping("/updateDepartment")
    public CommonResponseEntity updateDepartment(@RequestBody DepartmentEntity pEntity) {
        CommonResponseEntity resp = departmentService.updateDepartment(pEntity);
        return resp;
    }

    /**
     * Method to find department by id
     *
     * @param pDepartmentId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CommonResponseEntity findDepartmentById(@PathVariable("id") int pDepartmentId) {
        CommonResponseEntity resp = departmentService.findDepartmentById(pDepartmentId);
        return resp;
    }

    /**
     * Method to get all department records
     *
     * @return
     */
    @PostMapping("/getAllInstructors")
    public CommonResponseEntity getAllDepartmentsRecords() {
        CommonResponseEntity resp = departmentService.getAllDepartmentsRecords();
        return resp;
    }

    /**
     * Method to delete a department record
     *
     * @param pDepartmentId
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public CommonResponseEntity deleteDepartmentRecord(@PathVariable("id") int pDepartmentId) {
        CommonResponseEntity resp = departmentService.deleteDepartmentRecord(pDepartmentId);
        return  resp;
    }
}
