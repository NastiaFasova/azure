package com.epam.demo.models.mapper;

import com.epam.demo.models.Publication;
import com.epam.demo.models.dto.PublicationsDto;
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
