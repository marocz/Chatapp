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
    public String getCustomdata(String studentId){
        String customdata;
        long id = Long.parseLong(studentId);

        Student student = studentRepo.findById(id).get();
        customdata = student.getCustomdata();
        return customdata;
    }
    public Student setCustomdata(String customdata, String studentId){
        long id = Long.parseLong(studentId);
        Student student = studentRepo.findById(id).get();
        System.out.println(student.getFname());
        student.setCustomdata(customdata);
        System.out.println(student.getCustomdata());
        studentRepo.save(student);
        return student;
    }

    public Student getStudent(String studentId){
        long id = Long.parseLong(studentId);
        Student student = studentRepo.findById(id).get();
        return student;
    }
    public StudentDTO getStudentByEmail(String email){

        Student student = studentRepo.findByEmail(email);

                StudentDTO studentM = new StudentDTO();
                studentM.setStudentId(student.getsId());
        System.out.println("-------Student ID--------");
                studentM.setSname(student.getSname());
                studentM.setFname(student.getFname());
                studentM.setEmail(student.getEmail());
                studentM.setCountry(student.getCountry());
                studentM.setCity(student.getCity());

                try{

                    studentM.setCourses(student.getCourses());
                    studentM.setCustomdata(student.getCustomdata());

                    return studentM;

                }catch (NullPointerException e){
                    System.out.println("-------Course section-------");
                    List<Course> courses = new ArrayList<>();
                    String nullv = "NULL";

                    studentM.setCustomdata(nullv);

                    studentM.setCourses(courses);
                    return studentM;
                }





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
            updateStudent.setCity(student.getCity());
            updateStudent.setSname(student.getSname());
            updateStudent.setEmail(student.getEmail());
            updateStudent.setCustomdata(student.getCustomdata());
            updateStudent.setCountry(student.getCountry());

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
        s.setUsername(newStudent.getUsername());
        s.setStatus("Active");


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
            c.setDeptdesc(course.getDepartment().getDescription());
            c.setDeptname(course.getDepartment().getName());
            c.setImgurl(course.getImgurl());
            c.setRating(course.getRating());
            c.setCurl(course.getCurl());
            c.setPrice(course.getPrice());

try {
    List<TeacherDTO> teacherDTOS = new ArrayList<>();
    course.getTeachers().forEach(teacher -> {

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setFirstname(teacher.getFirstname());
        teacherDTO.setSurname(teacher.getSurname());
        teacherDTOS.add(teacherDTO);
    });
    c.setTeacher(teacherDTOS);
}catch (NullPointerException e){
    List<TeacherDTO> teacherDTOS = new ArrayList<>();
    c.setTeacher(teacherDTOS);
}
            c.setStudentcount(course.getStudentscount());


            courseDTO.add(c);
        });
        return courseDTO;

    }
}
