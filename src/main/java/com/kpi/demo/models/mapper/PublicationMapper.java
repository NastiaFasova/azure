package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Publication;
import com.kpi.demo.models.dto.request.PublicationRequestDto;
import com.kpi.demo.models.dto.response.PublicationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapper {
    public PublicationResponseDto getPublicationResponseDto(Publication publication) {
        PublicationResponseDto publicationResponseDto = new PublicationResponseDto();
        publicationResponseDto.setPrice(publication.getPrice());
        publicationResponseDto.setTitle(publication.getTitle());
        publicationResponseDto.setText(publication.getText());
        publicationResponseDto.setId(publication.getId());
        publicationResponseDto.setTopic(publication.getTopic());
        return publicationResponseDto;
    }

    public Publication getPublication(PublicationRequestDto publicationRequestDto) {
        Publication publication = new Publication();
        publication.setTitle(publicationRequestDto.getTitle());
        publication.setPrice(publicationRequestDto.getPrice());
        publication.setText(publicationRequestDto.getText());
        publication.setTopic(publicationRequestDto.getTopic());
        publication.setId(publicationRequestDto.getId());
        return publication;
    }
}
