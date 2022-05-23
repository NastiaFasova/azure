package com.epam.demo.service;

import com.epam.demo.models.Publication;
import com.epam.demo.models.Subscription;
import com.epam.demo.models.User;
import java.util.List;
import java.util.Set;

public interface SubscriptionService {
    Subscription save(Subscription subscription);

    boolean delete(Long subscriptionId);

    Subscription findById(Long subscriptionId);

    List<Subscription> findAll();

    Subscription subscribe(User user, Long publicationId);

    Set<Publication> getPublicationsByUserEmail(String email);

    boolean deleteFromSubscriptions(User user, Long id);

    boolean removeSubscriptionsWithPublicationId(Long id);
}

