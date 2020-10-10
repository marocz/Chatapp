package com.lit.lms.services;

import com.lit.lms.model.Department;
import com.lit.lms.model.Teacher;
import com.lit.lms.repository.DepartmentRepository;
import com.lit.lms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by nuwani on 10/28/2017.
 */
@Service
public class DeparmentTeacherService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public Teacher mapTeacherToDepartment(Long dId, Long tId){

        Optional<Department> department = departmentRepository.findById(dId);
        Optional<Teacher> teacher = teacherRepository.findById(tId);

        teacher.get().setDepartment(department.get());

        return teacherRepository.save(teacher.get());
    }
}
