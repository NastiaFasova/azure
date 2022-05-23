package com.epam.demo.service;

import com.epam.demo.models.Bill;
import java.util.List;

public interface BillService {
    Bill save(Bill bill);

    boolean delete(Long billId);

    Bill findById(Long billId);

    List<Bill> findAll();
}
