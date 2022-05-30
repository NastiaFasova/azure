package com.kpi.demo.models.dto.request;

import com.kpi.demo.models.Publication;
import com.kpi.demo.models.User;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class SubscriptionRequestDto {
    private Set<Publication> publications = new HashSet<>();
    private User user;
}
