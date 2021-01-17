package com.chatproject.marocz.repository;

import com.chatproject.marocz.model.Profileadmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Consists all the student related CRUD operations done to the DB
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface ProfileadminRepository extends CrudRepository<Profileadmin, Long> {

    Profileadmin findByEmail(String email);

}
