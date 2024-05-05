package com.example.state_machine.domain.service.purchase;

public interface PurchaseService {

    boolean reserved(String userId, String productId);

    boolean cancelReserve(String userId);

    boolean buy(String userId);
}
