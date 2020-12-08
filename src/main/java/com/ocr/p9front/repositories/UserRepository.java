package com.ocr.p9front.repositories;

import com.ocr.p9front.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Class for Repository User
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    // Spring security a besoin de cette requête spécifique
    @Query(value = "select * from users where userName = :userName", nativeQuery = true)
    User findByUserName(@Param("userName") String userName);
}
