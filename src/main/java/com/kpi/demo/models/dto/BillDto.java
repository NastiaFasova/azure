package com.kpi.demo.models.dto;

import com.kpi.demo.models.User;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
public class BillDto {
    private Long id;
    @Positive
    @Min(0)
    private Double amountOfMoney;
    private User user;
}
