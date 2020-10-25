package com.lit.lms.services;

import com.lit.lms.dto.CourseDTO;
import com.lit.lms.dto.ModulesDTO;
import com.lit.lms.model.Course;
import com.lit.lms.model.Modules;
import com.lit.lms.model.Quiz;
import com.lit.lms.model.Reviews;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.ModulesRepository;
import com.lit.lms.repository.QuizRepository;
import com.lit.lms.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    ReviewsRepository reviewsRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ModulesRepository modulesRepository;


    private List<Course> courses = new ArrayList<>();
    private List<CourseDTO> coursesDTO = new ArrayList<>();
    private List<Modules> modules = new ArrayList<>();

    public List<Course> getAllCourses(){
        courses.clear();
        courseRepo.findAll().forEach(course->{

            courses.add(course);
        });
        System.out.print(courses);
        return  courses;

    }

    public List<Modules> getAllModules(){
        modules.clear();
        modulesRepository.findAll().forEach(module->{
            modules.add(module);
        });
        System.out.print(modules);
        return  modules;

    }
    public Reviews updateReviews(Reviews reviews) {

        Reviews reviews1 = reviewsRepository.findById(reviews.getMcId()).get();

        if (reviews1.getMcId() != null) {
            reviews1.setRating(reviews.getRating());
            reviews1.setAuthorimage(reviews.getAuthorimage());
            reviews.setComments(reviews.getComments());


        }

        reviewsRepository.save(reviews1);

        return reviews1;
    }

    public Modules updateModules(ModulesDTO modulesDTO){
        Modules updateModules = modulesRepository.findById(modulesDTO.getModulesid()).get();

        if (modulesDTO.getTitle() != null) {
            updateModules.setContenturl(modulesDTO.getContenturl());
            updateModules.setDescription(modulesDTO.getDescription());
            updateModules.setTitle(modulesDTO.getTitle());


        }

        modulesRepository.save(updateModules);

        return updateModules;
    }

    public Course updateCourse(CourseDTO courses) {
        Course updateCourse = courseRepo.findById(courses.getcId()).get();

        if (courses.getName() != null) {
            updateCourse.setTitle(courses.getTitle());
            updateCourse.setName(courses.getName());
            updateCourse.setPrice(courses.getPrice());
            updateCourse.setDescription(courses.getDescription());
            updateCourse.setCurl(courses.getCurl());
            updateCourse.setImgurl(courses.getImgurl());

        }

        courseRepo.save(updateCourse);

        return updateCourse;
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
            c.setPrice(course.getPrice());
            c.setRating(course.getRating());
            c.setStatus(course.getStatus());


            coursesDTO.add(c);
        });

        System.out.print(courses);
        return  coursesDTO;
    }

    public Optional<Course> getCourse(Long courseId){
        return courseRepo.findById(courseId);
    }
    public Optional<Modules> getModule(Long moduleId){
        return modulesRepository.findById(moduleId);
    }

    public List<Course> getaCourse(int dept){

        Long id = new Long(dept);

        return courseRepo.findByDepartmentDid(id);

    }
    public List<Modules> getaModules(int mods){

        Long id = new Long(mods);

        return modulesRepository.findByModulesMid(id);

    }
     /**
     * add a new course
     */

    public Course createCourse(Course course, Long tId){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        course.setTcreator(tId);
        course.setCreateDate(date);
        course.setStatus("Active");
        return courseRepo.save(course);
    }
    public Modules createModules(ModulesDTO mods, Long tId){

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        mods.setTcreator(tId);
        Modules modules = new Modules();
        modules.setTcreator(mods.getTcreator());
        modules.setTitle(mods.getTitle());
        modules.setDescription(mods.getDescription());
        modules.setContenturl(mods.getContenturl());
        modules.setCreateDate(date);
        modules.setStatus("Active");

        modules.setCourses(courseRepo.findById(mods.getcId()).get());
        return modulesRepository.save(modules);
    }
    public Reviews createReviews(Reviews reviews){


        return reviewsRepository.save(reviews);
    }
    /**
     * delete a course
     */
    public void deleteCourse(Long cId, Long id){

        Course course = courseRepo.findById(cId).get();
        if(course.getTcreator() == id) {
            course.setStatus("Deleted");
            courseRepo.save(course);
        }
    }
    public void deleteModules(Long mId, Long id){
        Modules modules = modulesRepository.findById(mId).get();
        if(modules.getTcreator() == id) {
            modules.setStatus("Deleted");

            modulesRepository.save(modules);
        }
    }
    public void deleteReviews(@Valid @RequestBody Long mId){

        reviewsRepository.deleteById(mId);
    }
    public List<Quiz> getQuizForCourse(Long courseId) {
        Optional<Course> course = courseRepo.findById(courseId);

        return quizRepository.findByCourse(course.get());
    }

}
