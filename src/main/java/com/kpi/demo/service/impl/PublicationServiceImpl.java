package com.epam.demo.service.impl;

import com.epam.demo.exception.NotFoundByIdException;
import com.epam.demo.models.Publication;
import com.epam.demo.repository.PublicationRepository;
import com.epam.demo.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public boolean delete(Long publicationId) {
        publicationRepository.deleteById(publicationId);
        return true;
    }

    @Override
    public Publication findById(Long publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id"));
    }

    @Override
    public Page<Publication> findAllPaginated(String keyword, int page, int size, String sortField,
                                              String sortDir) {
        Pageable pageable = getPageable(page, size, sortField, sortDir);
        if (keyword == null) {
            return publicationRepository.findAll(pageable);
        }
        return publicationRepository.findByTitleContaining(keyword, pageable);
    }

    @Override
    public Page<Publication> findAllPaginatedByTitle(Long id, String keyword, int page, int size, String sortField,
                                                     String sortDir) {
        Pageable pageable = getPageable(page, size, sortField, sortDir);
        if (keyword == null) {
            return publicationRepository.findAllByTopicId(id, pageable);
        }
        return publicationRepository.findByTitleContainingAndTopicId(id, keyword, pageable);
    }

    private Pageable getPageable(int page, int size, String sortField,
                                 String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        return PageRequest.of(page - 1, size, sort);
    }

}
