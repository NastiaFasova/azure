package com.kpi.demo.service;

import com.kpi.demo.models.Publication;
import org.springframework.data.domain.Page;

public interface PublicationService {
    Publication save(Publication publication);

    boolean delete(Long publicationId);

    Publication findById(Long publicationId);

    Page<Publication> findAllPaginated(String keyword, int pageNo, int pageSize, String sortField, String sortDirection);

    Page<Publication> findAllPaginatedByTitle(Long id, String keyword, int page, int size, String sortField,
                                              String sortDir);
}
