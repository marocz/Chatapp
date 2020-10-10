package com.lit.lms.services;

import com.lit.lms.dto.*;
import com.lit.lms.model.*;
import com.lit.lms.entities.User;
import com.lit.lms.repository.*;
import com.lit.lms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 9/26/2017.
 */
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    QuizMarkRepository quizMarkRepository;

    @Autowired
    StudentAssignmentRepository studentAssignmentRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        students.clear();
        studentRepo.findAll().forEach(student->{
            students.add(student);
        });
        System.out.println(students);
        return students;
    }

    public Student getStudent(String studentId){
        long id = Long.parseLong(studentId);
        Student student = studentRepo.findById(id).get();
        return student;
    }

    public ResponseEntity<String> enroll(Enrollment enrollment){

        Student student = studentRepo.findById(enrollment.getsId()).get();
        Course course = courseRepo.findById(enrollment.getcId()).get();
        course.getStudents().add(student);
        courseRepo.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<String> unEnroll(String studentId, String courseId){

        long sId = Long.parseLong(studentId);
        long cId = Long.parseLong(courseId);

        Student student = studentRepo.findById(sId).get();
        Course course = courseRepo.findById(cId).get();
        course.getStudents().remove(student);
        courseRepo.save(course);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public Student updateStudent(Long studentId, StudentDTO student) {
        Student updateStudent = studentRepo.findById(studentId).get();

        if (student.getFname() != null) {
            updateStudent.setFname(student.getFname());
        } else if (student.getEmail() != null) {
            updateStudent.setEmail(student.getEmail());
        }

        studentRepo.save(updateStudent);

        return updateStudent;
    }

    public List<QuizM> getStudentQuizMarks(Long studentId) {
//        Student student = studentRepo.findOne(studentId);
        List<QuizM> quizMarkList = new ArrayList();

        quizMarkRepository.findAll().forEach(quizMark -> {
            if (quizMark.getStudent().getsId() == studentId) {
                QuizM quizM = new QuizM();
                quizM.setQuiz(quizMark.getQuiz());
                Student student = studentRepo.findById(quizMark.getStudent().getsId()).get();
                StudentDTO s = new StudentDTO();
                s.setEmail(student.getEmail());
                s.setFname(student.getFname());
                s.setStudentId(student.getsId());
                quizM.setStudent(s);
                quizM.setAnsweredQuestions(quizMark.getAnsweredQuestions());

                quizMarkList.add(quizM);
            }

        });
        return quizMarkList;
    }

    public Student createStudent(RegisterDTO newStudent){


        Student student = new Student();
        try{



        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        student.setEmail(newStudent.getEmail());
        student.setFname(newStudent.getFname());
        student.setSname(newStudent.getSname());
        student.setCity(newStudent.getCity());
        student.setCountry(newStudent.getCountry());

        User s = new User();
        s.setEmail(newStudent.getEmail());
        s.setPassword(newStudent.getPassword());
        s.setEmail(newStudent.getEmail());
        s.setUsername(newStudent.getUsername());

            userService.save(s);

        return studentRepo.save(student);
    }
        catch (Exception e) {
        e.printStackTrace();

        return student;
    }
    }
        
    public void deleteStudent(Long sId){
        
        Student student = studentRepo.findById(sId).get();
        studentRepo.delete(student);
    }

    public List<StudentAssignmentDTO> getAllAssignmentsForStudent(String studentId){
        List<StudentAssignmentDTO> studentAssignmentDTO = new ArrayList<>();
        studentAssignmentRepository.findAll().forEach(
            studentAssignment -> {
                 if(studentAssignment.getStudent().getsId()==Long.parseLong(studentId)){
                     StudentAssignmentDTO assign = new StudentAssignmentDTO();
                     assign.setAssignId(studentAssignment.getAssignment().getAssignId());
                     assign.setMarks(studentAssignment.getMarks());
                     assign.setName(studentAssignment.getAssignment().getName());
                     assign.setDescription(studentAssignment.getAssignment().getDescription());
                     assign.setCourseId(studentAssignment.getAssignment().getCourse().getcId());
                     studentAssignmentDTO.add(assign);
                 }
             }
        );
        return studentAssignmentDTO;
    }

    public List<SubmissionDTO> getAllStudentAssignments(Long assignmentId){
        List<SubmissionDTO> submissionDTOs = new ArrayList<>();
        studentAssignmentRepository.findAll().forEach(
                studentAssignment -> {
                    if(studentAssignment.getAssignment().getAssignId()==assignmentId) {
                        SubmissionDTO submissionDTO = new SubmissionDTO();
                        submissionDTO.setFile(studentAssignment.getFile());
                        submissionDTO.setStudentId(studentAssignment.getStudent().getsId());
                        submissionDTO.setStudentName(studentAssignment.getStudent().getFname());
                        submissionDTO.setMarks(studentAssignment.getMarks());
                        submissionDTO.setFeedback(studentAssignment.getFeedback());
                        submissionDTOs.add(submissionDTO);
                    }
                }
        );
        return submissionDTOs;
    }

    public List<CourseDTO> getAllCoursesForStudent(String studentId){
        Student student = studentRepo.findById(Long.parseLong(studentId)).get();
        List<CourseDTO> courseDTO = new ArrayList<>();
        student.getCourses().forEach(course -> {
            CourseDTO c = new CourseDTO();
            c.setcId(course.getcId());
            c.setTitle(course.getTitle());
            c.setDescription(course.getDescription());
            c.setName(course.getName());

            courseDTO.add(c);
        });
        return courseDTO;

    }
}
