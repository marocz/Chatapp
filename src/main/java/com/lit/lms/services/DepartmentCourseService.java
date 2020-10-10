package com.lit.lms.services;

import com.lit.lms.model.Course;
import com.lit.lms.model.Department;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by nuwani on 10/28/2017.
 */
@Service
public class DepartmentCourseService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CourseRepository courseRepository;

    public Course mapCoursesToDepartment(Long dId, Long cId){

        Optional<Department> department = departmentRepository.findById(dId);
        Optional<Course> course = courseRepository.findById(cId);

        course.get().setDepartment(department.get());
        return courseRepository.save(course.get());
    }
}
