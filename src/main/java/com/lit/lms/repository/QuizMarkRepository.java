package com.lit.lms.repository;

import com.lit.lms.model.Quiz;
import com.lit.lms.model.QuizMark;
import com.lit.lms.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kashifroshen on 10/21/17.
 */
public interface QuizMarkRepository extends CrudRepository<QuizMark, Long> {

    List<QuizMark> findByStudent(Student student);
    List<QuizMark> findByQuiz(Quiz quiz);

}
