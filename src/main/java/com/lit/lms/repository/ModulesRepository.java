package com.lit.lms.repository;


import com.lit.lms.model.Course;
import com.lit.lms.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dinukshakandasamanage on 9/25/17.
 */

public interface ModulesRepository extends JpaRepository<Modules,Long> {
    @Query("Select x from Modules x inner join x.courses u where u.cId = ?1")
    List<Modules> findByModulesMid(Long id);

}
