package com.mcb.crudrestapi.service;

import com.mcb.crudrestapi.entity.CommonResponseEntity;
import com.mcb.crudrestapi.entity.CourseEntity;
import com.mcb.crudrestapi.entity.InstructorEntity;
import com.mcb.crudrestapi.entity.StudentEntity;
import com.mcb.crudrestapi.repository.InstructorRepository;
import com.mcb.crudrestapi.repository.StudentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    StudentInfoRepository studentInfoRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    /**
     * Method to add a new instructor
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity addNewInstructor(InstructorEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        if (instructorRepository.existsById(pEntity.getId())) {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("This id already has a record, please try with different id");
            return response;
        }
        InstructorEntity resp = instructorRepository.save(pEntity);
        if (resp.getId() > 0) {
            response.setResult(resp);
            response.setStatus(HttpStatus.OK);
            response.setMessage("");
        } else {
            response.setStatus(HttpStatus.NOT_IMPLEMENTED);
            response.setMessage("Failed to add a instructor");
        }
        return response;
    }

    /**
     * Method to update instructor
     *
     * @author Nick
     * @param pEntity
     * @return
     */
    public CommonResponseEntity updateInstructor(InstructorEntity pEntity) {
        CommonResponseEntity response = new CommonResponseEntity();
        CommonResponseEntity commonResp = findInstructorById(pEntity.getId());
        Optional<InstructorEntity> optionalEntity = (Optional<InstructorEntity>) commonResp.getResult();
        if (optionalEntity.isPresent()) {
            InstructorEntity resp = instructorRepository.save(pEntity);
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
     * Method to get instructor details by id
     *
     * @param pInstructorId
     * @return
     */
    public CommonResponseEntity findInstructorById(int pInstructorId) {
        CommonResponseEntity response = new CommonResponseEntity();
        Optional<InstructorEntity> resp = instructorRepository.findById(pInstructorId);
        if (resp.isPresent()) {
            response.setResult(resp.get());
            response.setMessage("");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("Instructor record not found for the given id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Method to get all instructor records
     *
     * @return
     */
    public CommonResponseEntity getAllInstructorsRecords() {
        CommonResponseEntity response = new CommonResponseEntity();
        List<InstructorEntity> resp = (List<InstructorEntity>) instructorRepository.findAll();
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
     * Method to delete a instructor record
     *
     * @param pInstructorId
     * @return
     */
    public CommonResponseEntity deleteInstructorRecord(int pInstructorId) {
        CommonResponseEntity response = new CommonResponseEntity();
        try {
            instructorRepository.deleteById(pInstructorId);
            if (pInstructorId > 0) {
                response.setMessage("Record(s) deleted successfully");
                response.setStatus(HttpStatus.OK);
            } else {
                response.setMessage("Instructor record not found for the given id");
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error occurred while deleting the record, please check the instructor id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    public CommonResponseEntity findStudentByInstructorId(int pInstructorId) {
        CommonResponseEntity response = new CommonResponseEntity();
        List<CourseEntity> courseEntities = new ArrayList<>();
        List<StudentEntity> studentEntities = new ArrayList<>();
        Set<StudentEntity> studentEntitySet = new HashSet<>();

        //getting instructors list by id
        InstructorEntity records = (InstructorEntity) findInstructorById(pInstructorId).getResult();
        if (records != null) {
            for (CourseEntity courseEntity: records.getCourses()) {
                courseEntities.add(courseEntity);
            }
            //Loop thru course entities to get students list
            for(CourseEntity courseEntity :courseEntities) {
                List<Integer> studentIds = studentInfoRepository.getStudentIdByCourseId(courseEntity.getId());
                for (int id: studentIds) {
                    Optional<StudentEntity> resp = studentInfoRepository.findById(id);
                    studentEntities.add(resp.get());
                }
            }
            studentEntitySet.addAll(studentEntities); //Removing duplicates
            studentEntities = new ArrayList<>();
            studentEntities.addAll(studentEntitySet); // Adding result to same List
            response.setResult(studentEntities);
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("Students records not found for the given instructor id");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
