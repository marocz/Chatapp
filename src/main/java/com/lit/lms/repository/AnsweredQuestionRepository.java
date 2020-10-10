package com.lit.lms.repository;

import com.lit.lms.model.AnsweredQuestion;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kashifroshen on 10/23/17.
 */
public interface AnsweredQuestionRepository extends CrudRepository<AnsweredQuestion, Long> {
}
