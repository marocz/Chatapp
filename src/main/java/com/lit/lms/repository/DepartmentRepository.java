package com.lit.lms.repository;

import com.lit.lms.model.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dinukshakandasamanage on 9/25/17.
 */
public interface DepartmentRepository extends CrudRepository<Department,Long> {
}
