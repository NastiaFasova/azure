package com.epam.demo.models.dto;

import com.epam.demo.models.Publication;
import lombok.Data;
import java.util.Set;

@Data
public class PublicationsDto {
    private Set<Publication> publications;
}
