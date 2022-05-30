package com.kpi.demo.repository;

import com.kpi.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles where u.email =:email")
    User findByEmail(String email);

    Page<User> findBySurnameContaining(String keyword, Pageable pageable);

    @Query("select u from User u join u.publications p where (p.id =:publicationId)")
    List<User> getLikesWithPublicationId(Long publicationId);
}
