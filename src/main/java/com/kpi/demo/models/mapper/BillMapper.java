package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Bill;
import com.kpi.demo.models.dto.BillDto;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {
    public BillDto getBillDto(Bill bill) {
        BillDto billDto = new BillDto();
        billDto.setUser(bill.getUser());
        billDto.setAmountOfMoney(bill.getAmountOfMoney());
        billDto.setId(bill.getId());
        return billDto;
    }

    public Bill getBill(BillDto billDto) {
        Bill bill = new Bill();
        bill.setUser(billDto.getUser());
        bill.setId(billDto.getId());
        bill.setAmountOfMoney(billDto.getAmountOfMoney());
        return bill;
    }
}
