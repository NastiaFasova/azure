package com.kpi.demo.service;

import com.kpi.demo.models.User;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserService {
    User save(User user);

    boolean delete(Long userId);

    User findById(Long userId);

    List<User> findAll();

    User addPublication(Long publicationId, Long userId);

    boolean removePublication(Long publicationId, Long userId);

    User block(Long userId);

    User unblock(Long userId);

    User getByEmail(String email);

    Page<User> findAllPaginated(String keyword, int page, int size, String sortField, String sortDir);

    boolean removeLikesWithPublicationId(Long id);
}
