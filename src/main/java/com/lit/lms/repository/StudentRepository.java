package com.lit.lms.repository;

import com.lit.lms.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Consists all the student related CRUD operations done to the DB
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByEmail(String email);

}
