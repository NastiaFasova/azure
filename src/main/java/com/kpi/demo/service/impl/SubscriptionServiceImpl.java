package com.epam.demo.service.impl;

import com.epam.demo.exception.NotEnoughMoneyException;
import com.epam.demo.exception.NotFoundByIdException;
import com.epam.demo.models.Bill;
import com.epam.demo.models.Publication;
import com.epam.demo.models.Subscription;
import com.epam.demo.models.User;
import com.epam.demo.repository.PublicationRepository;
import com.epam.demo.repository.SubscriptionRepository;
import com.epam.demo.repository.UserRepository;
import com.epam.demo.service.SubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private static final Logger LOGGER = Logger.getLogger(SubscriptionServiceImpl.class);
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository,
                                   PublicationRepository publicationRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean delete(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
        return true;
    }

    @Override
    public Subscription findById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new NotFoundByIdException("The subscription can't be found by id"));
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription subscribe(User user, Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id"));
        Bill bill = user.getBill();
        Double currentBill = bill.getAmountOfMoney();
        Double payment = publication.getPrice();
        if (payment > currentBill) {
            throw new NotEnoughMoneyException("Not enough money for subscription!");
        }
        bill.setAmountOfMoney(currentBill - payment);
        Subscription subscription = findById(user.getId());
        LOGGER.info("Subscription was successfully retrieved by userId");
        subscription.getPublications().add(publication);
        removePublicationFromLikes(user, publication);
        userRepository.save(user);
        LOGGER.info("User successfully subscribed");
        return subscription;
    }

    private boolean removePublicationFromLikes(User user, Publication publication) {
        Iterator<Publication> i = user.getPublications().iterator();
        while (i.hasNext()) {
            Publication p = i.next();
            if (p.equals(publication)) {
                i.remove();
                LOGGER.info("Publication was successfully removed from likes");
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Publication> getPublicationsByUserEmail(String email) {
        return subscriptionRepository.getPublicationsByUserIdAndKeyword(email);
    }

    @Override
    public boolean deleteFromSubscriptions(User user, Long id) {
        Subscription subscription = findById(user.getId());
        Publication publication = subscription.getPublications()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id" + id));
        LOGGER.info("Liked publications were successfully retrieved by userId");
        if (subscription.getPublications().remove(publication)) {
            userRepository.save(user);
            LOGGER.info("Liked publications was successfully removed");
            return true;
        }
        return false;
    }

    @Override
    public boolean removeSubscriptionsWithPublicationId(Long id) {
        List<Subscription> subscriptions = subscriptionRepository.getSubscriptionsWithPublicationId(id);
        for (Subscription s : subscriptions) {
            Iterator<Publication> publications = s.getPublications().iterator();
            while (publications.hasNext()) {
                Publication p = publications.next();
                if (p.getId().equals(id)) {
                    publications.remove();
                    LOGGER.info("Publications was removed from subscriptions");
                    return true;
                }
            }
        }
        return false;
    }
}
