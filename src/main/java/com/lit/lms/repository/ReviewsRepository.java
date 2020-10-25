package com.lit.lms.repository;


import com.lit.lms.model.Modules;
import com.lit.lms.model.Reviews;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dinukshakandasamanage on 9/25/17.
 */
public interface ReviewsRepository extends CrudRepository<Reviews,Long> {
}
