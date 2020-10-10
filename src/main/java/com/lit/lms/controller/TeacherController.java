package com.lit.lms.controller;

import com.lit.lms.dto.CourseDTO;
import com.lit.lms.dto.TeacherDTO;
import com.lit.lms.model.Course;
import com.lit.lms.model.Teacher;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.services.DeparmentTeacherService;
import com.lit.lms.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for teacher
 * Consists of endpoints to retrieve teacher related data
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@RestController
@RequestMapping(path = "/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DeparmentTeacherService deparmentTeacherService;


    @GetMapping("")
    @ResponseBody()
    public Iterable<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    /**
     *
     * @return
     */
    @GetMapping("/courses")
    @ResponseBody
    public List<Course> getCourse(){

        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);

        return courses;

    }

      /**
     * retrieve a single teacher
     */
    @RequestMapping(value = "/teacher/{teacherID}", method = RequestMethod.GET)
    @ResponseBody()
    public Teacher getTeacher (@PathVariable("teacherID") Long teacherID){
        return teacherService.getTeacher(teacherID);
    }

    /**
     * add a new teacher
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody()
    public Teacher createTeacher (@Valid @RequestBody TeacherDTO teacher){
        return teacherService.createTeacher(teacher);
    }

    /**
     * delete a teacher
     */
    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.DELETE)
    @ResponseBody()
    public void deleteTeacher(@Valid @RequestBody Long tId){
        teacherService.deleteTeacher(tId);
    }


    @RequestMapping(value = "/{id}/courses", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> getCoursesForTeacher(@PathVariable Long id){
        return teacherService.getCoursesForTeacher(id);
    }

}
