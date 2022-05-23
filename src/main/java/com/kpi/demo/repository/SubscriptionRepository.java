package com.epam.demo.repository;

import com.epam.demo.models.Publication;
import com.epam.demo.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("select s.publications from Subscription s where s.user.email =:email")
    Set<Publication> getPublicationsByUserIdAndKeyword(String email);

    @Query("select s from Subscription s join s.publications p where (p.id =:publicationId)")
    List<Subscription> getSubscriptionsWithPublicationId(@Param("publicationId") Long publicationId);
}
