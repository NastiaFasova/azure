package com.epam.demo.service.impl;

import com.epam.demo.models.Bill;
import com.epam.demo.models.Publication;
import com.epam.demo.models.User;
import com.epam.demo.repository.PublicationRepository;
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
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PublicationRepository publicationRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private Bill bill;
    private Publication publication;
    private User user;
    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        bill = new Bill();
        publication = new Publication();
        user = new User();
        bill.setId(ID);
        publication.setId(ID);
        user.setId(ID);
        Set<Publication> publications = new HashSet<>();
        publications.add(publication);
        user.setPublications(publications);
    }

    @Test
    public void addPublication() {
        when(publicationRepository.findById(ID)).thenReturn(Optional.ofNullable(publication));
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.addPublication(publication.getId(), user.getId()));
        verify(publicationRepository).findById(ID);
        verify(userRepository).findById(ID);
        verify(userRepository).save(user);
    }

    @Test
    public void removePublication() {
        when(publicationRepository.findById(ID)).thenReturn(Optional.ofNullable(publication));
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        Assert.assertTrue(userService.removePublication(publication.getId(), user.getId()));
        verify(publicationRepository).findById(ID);
        verify(userRepository).findById(ID);
        verify(userRepository).save(user);
    }

    @Test
    public void block() {
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.block(user.getId()));
        Assert.assertTrue(user.isLocked());
        verify(userRepository).findById(ID);
        verify(userRepository).save(user);
    }

    @Test
    public void unblock() {
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.unblock(user.getId()));
        Assert.assertFalse(user.isLocked());
        verify(userRepository).findById(ID);
        verify(userRepository).save(user);
    }
}