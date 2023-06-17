package com.example.hrApplication.payloads.response;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AlertResponse {

    @NotNull
    private Long id;

    @NotNull
    private Double targetPrice;

    @NotNull
    private Long currencyId;

    @NotEmpty
    private String status;

    @NotNull
    private LocalDateTime createDateTime;

    public AlertResponse(Long id, Long currencyId, Double targetPrice, String status, LocalDateTime creaDateTime) {
        this.id = id;
        this.currencyId = currencyId;
        this.targetPrice = targetPrice;
        this.status = status;
        this.createDateTime = creaDateTime;
    }
}
