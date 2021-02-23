package com.mcb.crudrestapi.service;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.DepartmentEntity;
import com.mcb.crudrestapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Method to add a new department
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity addNewDepartment(DepartmentEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        if (departmentRepository.existsById(pEntity.getId())) {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("This id already has a record, please try with different id");
            return response;
        }
        DepartmentEntity resp = departmentRepository.save(pEntity);
        if (resp.getId() > 0) {
            response.setResult(resp);
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
        } else {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("Failed to add a department");
        }
        return response;
    }

    /**
     * Method to update department
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity updateDepartment(DepartmentEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        CommonResponseEntity commonResp = findDepartmentById(pEntity.getId());
        Optional<DepartmentEntity> optionalEntity = (Optional<DepartmentEntity>) commonResp.getResult();
        if (optionalEntity.isPresent()) {
            DepartmentEntity resp = departmentRepository.save(pEntity);
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
     * Method to get department details by id
     *
     * @param pDepartmentId
     * @return
     */
    public CommonResponseEntity findDepartmentById(int pDepartmentId) {
        CommonResponseEntity response = new CommonResponseEntity();
        Optional<DepartmentEntity> resp = departmentRepository.findById(pDepartmentId);
        response.setResult(resp);
        if (resp.isPresent()) {
            response.setMessage("");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("Department record not found for the given id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Method to get all department records
     *
     * @return
     */
    public CommonResponseEntity getAllDepartmentsRecords() {
        CommonResponseEntity response = new CommonResponseEntity();
        List<DepartmentEntity> resp = (List<DepartmentEntity>) departmentRepository.findAll();
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
     * Method to delete a department record
     *
     * @param pDepartmentId
     * @return
     */
    public CommonResponseEntity deleteDepartmentRecord(int pDepartmentId) {
        CommonResponseEntity response = new CommonResponseEntity();
        try {
            departmentRepository.deleteById(pDepartmentId);
            if (pDepartmentId > 0) {
                response.setMessage("Record(s) deleted successfully");
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage("Department record not found for the given id");
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error occurred while deleting the record, please check the department id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
