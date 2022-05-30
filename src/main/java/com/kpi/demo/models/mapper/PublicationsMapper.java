package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Publication;
import com.kpi.demo.models.dto.PublicationsDto;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class PublicationsMapper {
    public PublicationsDto getPublicationsDto(Set<Publication> publications) {
        PublicationsDto publicationsDto = new PublicationsDto();
        publicationsDto.setPublications(publications);
        return publicationsDto;
    }
}
