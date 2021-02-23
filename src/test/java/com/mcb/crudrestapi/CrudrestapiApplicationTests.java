package com.mcb.crudrestapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.mcb.crudrestapi.controller.InstructorController;
import com.mcb.crudrestapi.controller.StudentInfoController;
import com.mcb.crudrestapi.entity.CommonResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(Parameterized.class)
public class CrudrestapiApplicationTests {
    int studentId;
    boolean expected;

    public CrudrestapiApplicationTests(int studentId, boolean expected) {
        super();
        this.studentId = studentId;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getCourseRecordsByStudentID() {
        return Arrays.asList(new Object[][] { { 1, true }, {2, true } });
    }

    @Test
    public void test() throws Exception {
        assertEquals(expected, getCourseRecordsByStudentID(studentId));
        assertEquals(expected, findStudentByInstructorId(studentId));
        assertEquals(expected, calculateDurationByStudentId(studentId));
    }

    private Object calculateDurationByStudentId(int studentId) throws Exception {
        StudentInfoController studentInfoController = Mockito.mock(StudentInfoController.class);
        int s = 100;
        doReturn(s).when(studentInfoController).calculateDurationByStudentId(Mockito.anyInt());
        return true;
    }

    private Object findStudentByInstructorId(int studentId) throws Exception {
        CommonResponseEntity commonResponseEntity = new CommonResponseEntity();
        InstructorController instructorController = Mockito.mock(InstructorController.class);
        List<String> s = new ArrayList<>();
        s.add("Nick");
        s.add("Raj");
        commonResponseEntity.setResult(s);
        doReturn(commonResponseEntity).when(instructorController).findStudentByInstructorId(Mockito.anyInt());
        return true;
    }

    private Object getCourseRecordsByStudentID(int studentId) throws Exception {
        CommonResponseEntity commonResponseEntity = new CommonResponseEntity();
        StudentInfoController studentInfoController = Mockito.mock(StudentInfoController.class);
        List<String> s = new ArrayList<>();
        s.add("a");
        s.add("b");
        commonResponseEntity.setResult(s);
        doReturn(commonResponseEntity).when(studentInfoController).getCourseRecordsByStudentId(Mockito.anyInt());
        return true;
    }
}
