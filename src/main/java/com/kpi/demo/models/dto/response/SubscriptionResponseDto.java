package com.kpi.demo.models.dto.response;

import com.kpi.demo.models.Publication;
import com.kpi.demo.models.User;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class SubscriptionResponseDto {
    private Long id;
    private Set<Publication> publications = new HashSet<>();
    private User user;
}
