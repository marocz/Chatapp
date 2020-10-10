package com.lit.lms.services;

import com.lit.lms.dto.CourseDTO;
import com.lit.lms.model.Course;
import com.lit.lms.model.Quiz;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jonathan on 9/26/2017.
 */
@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    CourseService courseService;

    @Autowired
    QuizRepository quizRepository;

    private List<Course> courses = new ArrayList<>();
    private List<CourseDTO> coursesDTO = new ArrayList<>();

    public List<Course> getAllCourses(){
        courses.clear();
        courseRepo.findAll().forEach(course->{
            courses.add(course);
        });
        System.out.print(courses);
        return  courses;
    }



    public List<CourseDTO> getAllCoursesDTO(){
        coursesDTO.clear();
        courseRepo.findAll().forEach(course->{
            CourseDTO c = new CourseDTO();
            c.setcId(course.getcId());
            c.setTitle(course.getTitle());
            c.setDescription(course.getDescription());
            c.setName(course.getName());
            c.setImgurl(course.getImgurl());
            c.setCurl(course.getCurl());
            coursesDTO.add(c);
        });
        System.out.print(courses);
        return  coursesDTO;
    }

    public Optional<Course> getCourse(Long courseId){
        return courseRepo.findById(courseId);
    }

    public List<Course> getaCourse(int dept){

        Long id = new Long(dept);

        return courseRepo.findByDepartmentDid(id);

    }

     /**
     * add a new course
     */

    public Course createCourse(Course course){
        return courseRepo.save(course);
    }

    /**
     * delete a course
     */
    public void deleteCourse(@Valid @RequestBody Long cId){
        courseService.deleteCourse(cId);
    }

    public List<Quiz> getQuizForCourse(Long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);

        return quizRepository.findByCourse(course.get());
    }
}
