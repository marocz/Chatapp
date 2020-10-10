package com.lit.lms.services;

import com.lit.lms.dto.DepartmentDTO;
import com.lit.lms.model.Department;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.DepartmentRepository;
import com.lit.lms.repository.StudentRepository;
import com.lit.lms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by nuwani on 10/28/2017.
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeacherRepository teacherRepository;


    /**
     * Retrieves all departments and maps them to Department objects
     *
     * @return all teachers
     */

//	public Set<Department> getAllDepartments(){
//
//		Set<Department> departments = new HashSet<>();
//
//		departmentRepository.findAll().forEach(department -> {
//			Department retrievedDepartment = new Department();
//			retrievedDepartment.setName(department.getName());
//			retrievedDepartment.setdId(department.getdId());
//			retrievedDepartment.setCourses(department.getCourses());
//			departments.add(department);
//		});
//		return departments;
//  }

    public List<Department> departments = new ArrayList<>();

    public List<Department> getAllDepartments(){

        departments.clear();
        departmentRepository.findAll().forEach(department ->{
            departments.add(department);
        });
        System.out.println(departments);
        return departments;
    }

    public Department getCourse (String courseID){

        Long id = Long.parseLong(courseID);
        Optional<Department> department = departmentRepository.findById(id);
        return department.get();
    }

    public Department getStudents(String studentID){

        Long id = Long.parseLong(studentID);
        Optional<Department> department = departmentRepository.findById(id);
        return department.get();
    }

    public Set<Department> getAllDepartmentDetails(){

        Set<Department> departments = new HashSet<>();

        departmentRepository.findAll().forEach(department -> {
            Department retrievedDepartment = new Department();
            retrievedDepartment.setName(department.getName());
            retrievedDepartment.setdId(department.getdId());
            retrievedDepartment.setDescription(department.getDescription());
            retrievedDepartment.setCourses(department.getCourses());
            departments.add(department);
        });
        return departments;
    }

    public Department getDepartment (String departmentID){

        Long did = Long.parseLong(departmentID);
        Optional<Department> department = departmentRepository.findById(did);
        return department.get();
    }

    public Department createDepartment (DepartmentDTO departmentDTO){

        Set li = new HashSet();

        Department department = new Department(departmentDTO.getName(),departmentDTO.getDescription(),li,li,li);
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long dId){

        Optional<Department> department = departmentRepository.findById(dId);
        departmentRepository.delete(department.get());
    }
}
