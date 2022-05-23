package com.epam.demo.service.impl;

import com.epam.demo.models.Bill;
import com.epam.demo.models.Publication;
import com.epam.demo.models.Subscription;
import com.epam.demo.models.User;
import com.epam.demo.repository.BillRepository;
import com.epam.demo.repository.SubscriptionRepository;
import com.epam.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceImplTest {
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private BillRepository billRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;
    private Subscription subscription;
    private User user;
    private Bill bill;
    private Publication publication;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        bill = new Bill();
        bill.setAmountOfMoney(250D);
        publication = new Publication();
        publication.setPrice(120D);
        user = new User();
        subscription = new Subscription();
        user.setBill(bill);
        bill.setId(ID);
        user.setId(ID);
        subscription.setId(ID);
        Set<Publication> publications = new HashSet<>();
        publications.add(publication);
        user.setPublications(publications);
    }

    @Test
    public void subscribe() {
//        when(subscriptionRepository.findById(ID)).thenReturn(Optional.ofNullable(subscription));
//        when(userRepository.save(user)).thenReturn(user);
//        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
//        Assert.assertEquals(subscription, subscriptionService.subscribe(user));
//        verify(subscriptionRepository).findById(ID);
//        verify(userRepository).save(user);
//        verify(subscriptionRepository).save(subscription);
    }

    @Test
    public void getSubscribedPublications() {
    }

    @Test
    public void deleteFromSubscriptions() {
    }
}