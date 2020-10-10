package com.lit.lms.repository;

import com.lit.lms.model.Course;
import com.lit.lms.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Handles the QuizDTO related CRUD operations
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
    List<Quiz> findByCourse(Course course);
}
