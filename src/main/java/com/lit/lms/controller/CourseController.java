package com.lit.lms.controller;


//import lk.sliit.lms.api.dto.DepartmentCourseDTO;

import com.lit.lms.dto.DepartmentCourseDTO;
import com.lit.lms.dto.CourseDTO;
import com.lit.lms.model.Assignment;
import com.lit.lms.model.Course;
import com.lit.lms.model.Quiz;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.services.AssignmentService;
import com.lit.lms.services.DepartmentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lit.lms.services.CourseService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jonathan on 9/26/2017.
 */
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private DepartmentCourseService departmentCourseService;


//    @Autowired
//    private DepartmentCourseDTO departmentCourseDTO;


    @GetMapping("/")
    @ResponseBody()
    public Iterable<Course> getAllQuizzes(){
        return courseService.getAllCourses();
    }


    @GetMapping("")
    @ResponseBody
    public List<CourseDTO> getAllCourses(){
        return courseService.getAllCoursesDTO();
    }

    @GetMapping("/{courseId}")
    @ResponseBody
    public Course getCourse(@PathVariable("courseId") Long courseId){
        return courseService.getCourse(courseId).get();
    }

    @GetMapping("/{courseId}/assignments")
    @ResponseBody
    public List<Assignment> getCourseAssignments(@PathVariable("courseId") Long courseId){
        return assignmentService.getAllAssignmentsForCourse(courseId);
    }

    /**
     * add a new course
     */
    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    @ResponseBody()
    public Course createCourse (@Valid @RequestBody Course course){

        return courseService.createCourse(course);
    }

    /**
     * delete a course
     */
    @RequestMapping(value = "/course/{courseId}", method = RequestMethod.DELETE)
    @ResponseBody()
    public void deleteCourse(@Valid @RequestBody Long cId){
        courseService.deleteCourse(cId);
    }

    /**
     * map course to department
     *
     * @param departmentCourseDTO
     * @return
     */

//    @RequestMapping(value = "/course/map", method = RequestMethod.PUT)
//    @ResponseBody()
//    public Course mapCoursesToDepartment(@Valid @RequestBody DepartmentCourseDTO  departmentCourseDTO){
//
//        return departmentCourseService.mapCoursesToDepartment(departmentCourseDTO.getdId(), departmentCourseDTO.getcId());
//    }

    @RequestMapping(value = "/course/map", method = RequestMethod.PUT)
    @ResponseBody()
    public Course mapCoursesToDepartment(@Valid @RequestBody DepartmentCourseDTO  departmentCourseDTO){

        return departmentCourseService.mapCoursesToDepartment(departmentCourseDTO.getdId(), departmentCourseDTO.getcId());
    }

    @RequestMapping(value = "/{courseId}/quizzes", method = RequestMethod.GET)
    @ResponseBody()
    public List<Quiz> mapCoursesToDepartment(@PathVariable("courseId") Long courseId){
        return courseService.getQuizForCourse(courseId);
    }
}
