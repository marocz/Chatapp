package com.lit.lms.services;

import com.lit.lms.model.Department;
import com.lit.lms.model.Student;
import com.lit.lms.repository.DepartmentRepository;
import com.lit.lms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by nuwani on 10/28/2017.
 */
@Service
public class DepartmentStudentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    StudentRepository studentRepository;

    public Student mapStudentToCourse(Long dId, Long sId){

        Optional<Department> department = departmentRepository.findById(dId);
        Optional<Student> student = studentRepository.findById(sId);

        student.get().setDepartment(department.get());
        return studentRepository.save(student.get());
    }
}
