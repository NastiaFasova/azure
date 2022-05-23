package com.epam.demo.service.impl;

import com.epam.demo.exception.NotFoundByIdException;
import com.epam.demo.models.Bill;
import com.epam.demo.models.User;
import com.epam.demo.repository.BillRepository;
import com.epam.demo.repository.UserRepository;
import com.epam.demo.service.BillService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER = Logger.getLogger(BillServiceImpl.class);
    private final BillRepository billRepository;
    private final UserRepository userRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bill save(Bill bill) {
        User user = userRepository.findById(bill.getId())
                .orElseThrow(() -> new NotFoundByIdException("The user can't be found by id"));
        LOGGER.info("User was successfully retrieved by id");
        user.setBill(bill);
        bill.setUser(user);
        userRepository.save(user);
        LOGGER.info("Bill was successfully saved");
        LOGGER.info("User was successfully saved");
        return bill;
    }

    @Override
    public boolean delete(Long billId) {
        billRepository.deleteById(billId);
        return true;
    }

    @Override
    public Bill findById(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new NotFoundByIdException("The bill can't be found by id"));
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

}
