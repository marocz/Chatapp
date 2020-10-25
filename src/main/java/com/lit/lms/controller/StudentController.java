package com.lit.lms.controller;

import com.lit.lms.dto.*;
import com.lit.lms.entities.User;
import com.lit.lms.model.Enrollment;
import com.lit.lms.model.Student;
import com.lit.lms.repository.UserRepository;
import com.lit.lms.services.DepartmentStudentService;
import com.lit.lms.services.QuizMarkService;
import com.lit.lms.services.StudentService;
import com.lit.lms.model.Customdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by Jonathan on 9/26/2017.
 */
@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

//	 @Autowired
//    private DepartmentStudentDTO departmentStudentDTO;

    @Autowired
    private QuizMarkService quizMarkService;

    @Autowired
    private DepartmentStudentService departmentStudentService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    @ResponseBody
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    @ResponseBody
    public Student getStudent(@PathVariable("studentId") String studentId){


        return studentService.getStudent(studentId);
    }
    @GetMapping("/loggedin")
    @ResponseBody
    public StudentDTO getloggedStudent(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

       // String user = auth.getPrincipal().toString();
        String logname = auth.getName();
        User user =  userRepository.findByUsername(logname);
        System.out.println(user.getEmail());
        return studentService.getStudentByEmail(user.getEmail());

    }

    @PostMapping("/courses")
    @ResponseBody
    public ResponseEntity<String> enrollStudent(@RequestBody Enrollment enrollment){
        return studentService.enroll(enrollment);
    }
    @GetMapping("/customdata/{studentId}")
    @ResponseBody
    public String customDatas(@PathVariable("studentId") String studentId){
        String customdata = studentService.getCustomdata(studentId);
        System.out.println(customdata);
        return customdata;
    }
    @PostMapping("/customdata")
    @ResponseBody
    public String customdata(@RequestBody Customdata custom){
        try {

            studentService.setCustomdata(custom.getCustomdata(), custom.getStudentId());
            return "data updated successfully";
        }
        catch (NullPointerException e){
            return "error occurred during the operations";

        }
    }

    @DeleteMapping("/{sId}/courses/{cId}")
    @ResponseBody
    public ResponseEntity<String> unEnrollStudent(@PathVariable("sId") String studentId,@PathVariable("cId") String courseId ){
        return studentService.unEnroll(studentId,courseId);
    }
	
	  /**
     * add a new student
     */
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseBody()
//    public Student createStudent(@Valid @RequestBody StudentDTO student){
//
//         return studentService.createStudent(student);
//    }

    @RequestMapping(value = "/{sId}", method = RequestMethod.POST)
    @ResponseBody
    public Student updateStudent(@PathVariable("sId") Long studentId, @RequestBody StudentDTO student) {
        return studentService.updateStudent(studentId,student);
    }

    @RequestMapping(value="/{sID}/quizzes", method= RequestMethod.GET)
    @ResponseBody
    public List<QuizM> getQuizMarks(@PathVariable("sID") Long studentId) {
        return studentService.getStudentQuizMarks(studentId);
    }


    //delete a department
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody()
    public void deleteStudent(@Valid @RequestBody Long sId){
        studentService.deleteStudent(sId);
    }

    /**
     * map students to departments
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/map", method = RequestMethod.POST)
    @ResponseBody()
    public Student mapStudentToDepartment(@Valid @RequestBody DepartmentStudentDTO departmentStudentDTO){
        return departmentStudentService.mapStudentToCourse(departmentStudentDTO.getdId(),departmentStudentDTO.getsId());
    }

    @GetMapping("/{studentId}/assignments")
    @ResponseBody
    public List<StudentAssignmentDTO> getAllAssignmentsForStudent(@PathVariable("studentId") String studentId){
        return studentService.getAllAssignmentsForStudent(studentId);
    }

    @GetMapping("/{studentId}/courses")
    @ResponseBody
    public List<CourseDTO> getAllCoursesForStudent(@PathVariable("studentId") String studentId){
        return studentService.getAllCoursesForStudent(studentId);
    }

}

