package com.kpi.demo.service.impl;

import com.kpi.demo.models.Bill;
import com.kpi.demo.models.User;
import com.kpi.demo.repository.BillRepository;
import com.kpi.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BillServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;
    private Bill bill;
    private User user;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        bill = new Bill();
        user = new User();
        user.setBill(bill);
        bill.setId(ID);
    }

    @Test
    public void save() {
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(bill, billService.save(bill));
        Assert.assertEquals(bill, user.getBill());
        verify(userRepository).findById(ID);
        verify(userRepository).save(user);
    }
}