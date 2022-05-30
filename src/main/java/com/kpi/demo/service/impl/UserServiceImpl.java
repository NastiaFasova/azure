package com.kpi.demo.service.impl;

import com.kpi.demo.exception.NotFoundByIdException;
import com.kpi.demo.models.Publication;
import com.kpi.demo.models.User;
import com.kpi.demo.repository.PublicationRepository;
import com.kpi.demo.repository.UserRepository;
import com.kpi.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static final Integer PAGE_SIZE = 8;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PublicationRepository publicationRepository) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundByIdException("The user can't be found by id"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User addPublication(Long publicationId, Long userId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id"));
        LOGGER.info("Publication was successfully retrieved by id");
        User user = findById(userId);
        LOGGER.info("User was successfully retrieved by id");
        user.getPublications().add(publication);
        userRepository.save(user);
        LOGGER.info("Publication was successfully liked by user");
        return user;
    }

    @Override
    public boolean removePublication(Long publicationId, Long userId) {
        User user = findById(userId);
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id"));
        LOGGER.info("Publication was successfully retrieved by id");
        if (user.getPublications().remove(publication)) {
            userRepository.save(user);
            LOGGER.info("Publication was successfully removed from user's likes");
            return true;
        }
        return false;
    }

    @Override
    public User block(Long userId) {
        User user = findById(userId);
        LOGGER.info("User was successfully retrieved by id");
        user.setLocked(true);
        return userRepository.save(user);
    }

    @Override
    public User unblock(Long userId) {
        User user = findById(userId);
        LOGGER.info("User was successfully retrieved by id");
        user.setLocked(false);
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAllPaginated(String keyword, int page, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        if (keyword == null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findBySurnameContaining(keyword, pageable);
    }

    @Override
    public boolean removeLikesWithPublicationId(Long id) {
        List<User> users = userRepository.getLikesWithPublicationId(id);
        for (User u : users) {
            Iterator<Publication> publications = u.getPublications().iterator();
            while (publications.hasNext()) {
                Publication p = publications.next();
                if (p.getId().equals(id)) {
                    publications.remove();
                    LOGGER.info("Publications was removed from subscriptions");
                    return true;
                }
            }
        }
        return false;
    }

}
