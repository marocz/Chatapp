package com.lit.lms.repository;

import com.lit.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("Select x from Course x inner join x.department u where u.dId = ?1")
    List<Course> findByDepartmentDid(Long id);
}
