package com.lit.lms.services;

import com.lit.lms.dto.AssignmentDTO;
import com.lit.lms.dto.AssignmentUploadDTO;
import com.lit.lms.dto.StudentAssignmentDTO;
import com.lit.lms.model.Assignment;
import com.lit.lms.model.Course;
import com.lit.lms.model.Student;
import com.lit.lms.model.StudentAssignment;
import com.lit.lms.repository.AssignmentRepository;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.StudentAssignmentRepository;
import com.lit.lms.repository.StudentRepository;
import com.lit.lms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by dinukshakandasamanage on 10/22/17.
 */

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentAssignmentRepository studentAssignmentRepository;

    @Autowired
    ServletContext context;


    private final Path rootLocation = Paths.get("upload-dir");

    public List<Assignment> getAllAssignments(){

        List<Assignment> assignments = new ArrayList<>();
        assignmentRepository.findAll().forEach(assignment -> {
            assignments.add(assignment);
        });
        return assignments;
    }

    public Assignment addAssignment(AssignmentDTO assignmentDTO){
        Assignment newAssignment = new Assignment();
        newAssignment.setName(assignmentDTO.getName());
        newAssignment.setDescription(assignmentDTO.getDescription());
        newAssignment.setStartDate(assignmentDTO.getStartDate());
        newAssignment.setEndDate(assignmentDTO.getEndDate());

        Optional<Course> course = courseRepository.findById(assignmentDTO.getCourseId());
        newAssignment.setCourse(course.get());

        return assignmentRepository.save(newAssignment);
    }

    public List<Assignment> getAllAssignmentsForCourse(Long courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        List<Assignment> assignments = new ArrayList<>();
        course.get().getAssignments().forEach(assignment -> assignments.add(assignment));
        return assignments;
    }

    public ResponseEntity<String> uploadAssignmentDetails(AssignmentUploadDTO assignmentDTO){
        System.out.println("Assignment Service");
        System.out.println("Student ID : "+assignmentDTO.getsId());
        System.out.println("Assignment ID : "+assignmentDTO.getAssignId());

        Optional<Assignment> assignment = assignmentRepository.findById(assignmentDTO.getAssignId());
        Optional<Student> student = studentRepository.findById(assignmentDTO.getsId());
        System.out.println("Retrievd Student ID : "+student.get().getsId());

        StudentAssignment studentAssignment = new StudentAssignment();
        studentAssignment.setStudent(student.get());
        studentAssignment.setAssignment(assignment.get());
        studentAssignment.setFile(assignmentDTO.getFile());

        System.out.println("Student assign : ");
        System.out.println(studentAssignment.getStudent().getsId());

        studentAssignmentRepository.save(studentAssignment);
        student.get().getStudentAssignment().add(studentAssignment);
        studentRepository.save(student.get());


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public String store(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/java/lk/sliit/lms/api/assignments", file.getOriginalFilename());
            //  Path path = Paths.get(rootLocation+file.getOriginalFilename());
            // Path path = Paths.get(context.getRealPath("uploads") + file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (Exception e) {
            System.out.println(file);
            throw new RuntimeException("FAIL!", e);
        }
    }

    public ResponseEntity<String> uploadAssignmentMaterial(AssignmentUploadDTO assignmentDTO){

        Optional<Assignment> assignment = assignmentRepository.findById(assignmentDTO.getAssignId());
        assignment.get().setFile(assignmentDTO.getFile());
        assignmentRepository.save(assignment.get());


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public String storeAssignment(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/java/com/lit/lms/files", file.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (Exception e) {
            System.out.println(file);
            throw new RuntimeException("FAIL!", e);
        }
    }
      
    public Optional<Assignment> getAssignmentById(Long assignmentId){
        return assignmentRepository.findById(assignmentId);
    }

    public Assignment updateAssignment(Long assignId, AssignmentDTO assignmentDTO) {
        Optional<Assignment> assignments = assignmentRepository.findById(assignId);
        Assignment assignment = assignments.get();
        assignment.setName(assignmentDTO.getName());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setStartDate(assignmentDTO.getStartDate());
        assignment.setEndDate(assignmentDTO.getEndDate());
        return assignmentRepository.save(assignment);
    }

    public void assignGrade(StudentAssignmentDTO studentAssignmentDTO) {

        studentAssignmentRepository.findAll().forEach(assignment -> {
            if(assignment.getStudent().getsId().equals(studentAssignmentDTO.getStudentId())
                    && assignment.getAssignment().getAssignId().equals(studentAssignmentDTO.getAssignId())){
                assignment.setMarks(studentAssignmentDTO.getMarks());
                studentAssignmentRepository.save(assignment);
            }
        });
    }

    public void addFeedback(StudentAssignmentDTO studentAssignmentDTO) {
        studentAssignmentRepository.findAll().forEach(assignment -> {
            if(assignment.getStudent().getsId().equals(studentAssignmentDTO.getStudentId())
                    && assignment.getAssignment().getAssignId().equals(studentAssignmentDTO.getAssignId())){
                assignment.setFeedback(studentAssignmentDTO.getFeedback());
                studentAssignmentRepository.save(assignment);
            }
        });
    }
}
