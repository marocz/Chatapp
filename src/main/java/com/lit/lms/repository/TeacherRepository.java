package com.lit.lms.repository;

import com.lit.lms.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is used to handle CRUD operations to the database
 * Auto implemented by Spring
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}
