package com.lit.lms.repository;

import com.lit.lms.model.StudentAssignment;
import com.lit.lms.model.StudentAssignmentPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinukshakandasamanage on 10/23/17.
 */

@Repository
public interface StudentAssignmentRepository extends CrudRepository<StudentAssignment, StudentAssignmentPK>{

}
