package com.lit.lms.controller;


//import lk.sliit.lms.api.dto.DepartmentCourseDTO;

import com.lit.lms.dto.CourseDTO;
import com.lit.lms.dto.DepartmentCourseDTO;
import com.lit.lms.dto.IdDto;
import com.lit.lms.dto.ModulesDTO;
import com.lit.lms.entities.User;
import com.lit.lms.model.*;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.TeacherRepository;
import com.lit.lms.repository.UserRepository;
import com.lit.lms.services.AssignmentService;
import com.lit.lms.services.CourseService;
import com.lit.lms.services.DepartmentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private DepartmentCourseService departmentCourseService;
    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private DepartmentCourseDTO departmentCourseDTO;


    @GetMapping("/")
    @ResponseBody()
    public Iterable<Course> getAllQuizzes(){
        return courseService.getAllCourses();
    }


    @GetMapping("")
    @ResponseBody
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/modules")
    @ResponseBody
    public List<Modules> getAllModuless(){
        return courseService.getAllModules();
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        // String user = auth.getPrincipal().toString();
        String logname = auth.getName();
        User user =  userRepository.findByUsername(logname);
        Teacher teacher = teacherRepository.findByEmail(user.getEmail());

        return courseService.createCourse(course, teacher.getId());
    }
    @RequestMapping(value = "/modules/add", method = RequestMethod.POST)
    @ResponseBody()
    public Modules createModules (@Valid @RequestBody ModulesDTO modules){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        // String user = auth.getPrincipal().toString();
        String logname = auth.getName();
        User user =  userRepository.findByUsername(logname);
        Teacher teacher = teacherRepository.findByEmail(user.getEmail());
        return courseService.createModules(modules, teacher.getId());
    }
    @RequestMapping(value = "/reviews/add", method = RequestMethod.POST)
    @ResponseBody()
    public Reviews createReviews (@Valid @RequestBody Reviews reviews){

        return courseService.createReviews(reviews);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody()
    public Course updateCourses(@Valid @RequestBody CourseDTO courseDTO){

        return courseService.updateCourse(courseDTO);
    }
    @RequestMapping(value = "/modules/update", method = RequestMethod.POST)
    @ResponseBody()
    public Modules updateModules(@Valid @RequestBody ModulesDTO modulesDTO){

        return courseService.updateModules(modulesDTO);
    }
    @RequestMapping(value = "/reviews/update", method = RequestMethod.POST)
    @ResponseBody()
    public Reviews updateReviews(@Valid @RequestBody Reviews reviews){

        return courseService.updateReviews(reviews);
    }

    /**
     * delete a course
     */
    @RequestMapping(value = "/course/{courseId}", method = RequestMethod.DELETE)
    @ResponseBody()
    public void deleteCourse(@Valid @RequestBody IdDto cId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        // String user = auth.getPrincipal().toString();
        String logname = auth.getName();
        User user =  userRepository.findByUsername(logname);
        Teacher teacher = teacherRepository.findByEmail(user.getEmail());
        Long id = new Long(cId.getId());
        courseService.deleteCourse(id, teacher.getId());
    }

    @RequestMapping(value = "/modules/{mId}", method = RequestMethod.DELETE)
    @ResponseBody()
    public void deleteModules(@Valid @RequestBody IdDto mId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        // String user = auth.getPrincipal().toString();
        String logname = auth.getName();
        User user =  userRepository.findByUsername(logname);
        Teacher teacher = teacherRepository.findByEmail(user.getEmail());
        Long id = new Long(mId.getId());
        courseService.deleteModules(id, teacher.getId());
    }
    @RequestMapping(value = "/reviews/{rId}", method = RequestMethod.DELETE)
    @ResponseBody()
    public void deleteReviews(@Valid @RequestBody IdDto rId){
        Long id = new Long(rId.getId());
        courseService.deleteReviews(id);

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

    @RequestMapping(value = "/course/map", method = RequestMethod.POST)
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
