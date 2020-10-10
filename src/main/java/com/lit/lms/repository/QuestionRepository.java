package com.lit.lms.repository;

import com.lit.lms.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Handles question related CRUD operations
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
