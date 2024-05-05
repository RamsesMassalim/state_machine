package com.example.state_machine.web.controller;

import com.example.state_machine.domain.service.purchase.PurchaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping(path = "/reserve")
    public boolean reserve(final String userId, final String productId) {
        return purchaseService.reserved(userId, productId);
    }

    @RequestMapping(path = "/cancel")
    public boolean cancelReserve(final String userId) {
        return purchaseService.cancelReserve(userId);
    }

    @RequestMapping(path = "/buy")
    public boolean buy(final String userId) {
        return purchaseService.buy(userId);
    }
}
