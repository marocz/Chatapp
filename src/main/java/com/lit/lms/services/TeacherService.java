package com.lit.lms.services;

import com.lit.lms.dto.CourseDTO;
import com.lit.lms.dto.TeacherDTO;
import com.lit.lms.model.Teacher;
import com.lit.lms.repository.DepartmentRepository;
import com.lit.lms.repository.TeacherRepository;
import com.lit.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all the teacher related operations
 * <p>
 * Created by dinukshakandasamanage on 9/23/17.
 */

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;


    /**
     * Retrieves all teachers and maps them to Teacher objects
     *
     * @return all teachers
     */
    public Iterable<Teacher> getAllTeachers() {

        return teacherRepository.findAll();
    }

    /***
     * get details of a teacher
     *
     * @return teacher
     */
    public Teacher getTeacher (Long teacherID) {

        return teacherRepository.findById(teacherID).get();
    }

    /**
     * create a new teacher
     */
    public Teacher createTeacher(TeacherDTO teacherDTO){

        Teacher teacher = new Teacher();

        teacher.setFirstname(teacherDTO.getFirstname());
        teacher.setSurname(teacherDTO.getSurname());


        teacher.setEmail(teacherDTO.getEmail());

        return teacherRepository.save(teacher);
    }
    /**
     * delete a new teacher
     */
    public void deleteTeacher(Long tId){
        Teacher teacher = teacherRepository.findById(tId).get();
        teacherRepository.delete(teacher);
    }

    public List<CourseDTO> getCoursesForTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        teacher.getCourses().forEach(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setName(course.getName());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setTitle(course.getTitle());
            courseDTO.setcId(course.getcId());
            courseDTOS.add(courseDTO);
        });

        return courseDTOS;
    }
}
