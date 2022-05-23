package com.epam.demo.models.dto.request;

import com.epam.demo.models.Publication;
import com.epam.demo.models.User;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class SubscriptionRequestDto {
    private Set<Publication> publications = new HashSet<>();
    private User user;
}
