package com.ocr.poseidon.repositories;

import com.ocr.poseidon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    // Spring security a besoin de cette requête spécifique
    @Query(value = "select * from users where userName = :userName", nativeQuery = true)
    User findByUserName(@Param("userName") String userName);

    // TODO A voir une autre méthode selon le nom par convention "findBy..." pour un seul critère => spring comprend et génère automatiquement.
    // Pour des requêtes plus complexes =>  voir une classe "criteria" pour spécifier les critères de recherche
}
