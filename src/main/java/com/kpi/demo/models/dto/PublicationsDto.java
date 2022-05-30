package com.kpi.demo.models.dto;

import com.kpi.demo.models.Publication;
import lombok.Data;
import java.util.Set;

@Data
public class PublicationsDto {
    private Set<Publication> publications;
}
