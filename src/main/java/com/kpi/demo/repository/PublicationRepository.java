package com.epam.demo.repository;

import com.epam.demo.models.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Page<Publication> findByTitleContaining(String keyword, Pageable pagingSort);

    Page<Publication> findAll(Pageable pagingSort);

    @Query("select p from Publication p where p.topic.id =:id")
    Page<Publication> findAllByTopicId(Long id, Pageable pageable);

    @Query("select p from Publication p where p.topic.id =:id and p.title like %:keyword%")
    Page<Publication> findByTitleContainingAndTopicId(Long id, String keyword, Pageable pageable);
}


