package com.lit.lms.repository;

import com.lit.lms.model.FeedBackQuestion;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jonathan on 11/1/2017.
 */
public interface FeedBackQuestionRepository extends CrudRepository<FeedBackQuestion, Long> {
}
