package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Subscription;
import com.kpi.demo.models.dto.request.SubscriptionRequestDto;
import com.kpi.demo.models.dto.response.SubscriptionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
    public SubscriptionResponseDto getSubscriptionResponseDto(Subscription subscription) {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        subscriptionResponseDto.setPublications(subscription.getPublications());
        subscriptionResponseDto.setId(subscription.getId());
        subscriptionResponseDto.setUser(subscription.getUser());
        return subscriptionResponseDto;
    }

    public Subscription getSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = new Subscription();
        subscription.setUser(subscriptionRequestDto.getUser());
        subscription.setPublications(subscriptionRequestDto.getPublications());
        return subscription;
    }
}
