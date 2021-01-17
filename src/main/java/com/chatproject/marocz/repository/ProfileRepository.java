package com.chatproject.marocz.repository;

import com.chatproject.marocz.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Consists all the student related CRUD operations done to the DB
 *
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Profile findByEmail(String email);

}
